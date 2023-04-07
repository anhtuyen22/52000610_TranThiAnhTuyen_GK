package com.trantuyen.springecommerce.controller;

import com.trantuyen.springecommerce.entity.*;
import com.trantuyen.springecommerce.models.OrderModel;
import com.trantuyen.springecommerce.models.OrderModel2;
import com.trantuyen.springecommerce.repo.CartRepo;
import com.trantuyen.springecommerce.repo.OrderDetailRepo;
import com.trantuyen.springecommerce.repo.OrderRepo;
import com.trantuyen.springecommerce.repo.ProductRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class OrderController {
    private final OrderRepo orderRepo;
    private final CartRepo cartRepo;
    private final OrderDetailRepo orderDetailRepo;
    private final ProductRepo productRepo;

    public OrderController(OrderRepo orderRepo, CartRepo cartRepo, OrderDetailRepo orderDetailRepo, ProductRepo productRepo) {
        this.orderRepo = orderRepo;
        this.cartRepo = cartRepo;
        this.orderDetailRepo = orderDetailRepo;
        this.productRepo = productRepo;
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public void updateProductToCart(@RequestBody OrderModel orderModel, @AuthenticationPrincipal Customer customer) {
        Customer customer1 = new Customer();
        customer1.setId(customer.getId());
        Order order = Order.builder().address(orderModel.getAddress())
                .companyName(orderModel.getCountry())
                .createdAt(new Date())
                .email(orderModel.getEmail())
                .firstName(orderModel.getFirstName())
                .lastName(orderModel.getLastName())
                .phone(orderModel.getPhone())
                .status(orderModel.getStatus())
                .quantity(orderModel.getQuantity())
                .customerByCustomerId(customer)
                .price(orderModel.getPrice()).build();
        Order orderSaved = orderRepo.save(order);
        if (orderModel.getOrderDetails() != null) {
            orderModel.getOrderDetails().forEach(orderDetail -> {
                OrderDetail orderDetail1 = new OrderDetail();
                Order order1 = new Order();
                order1.setId(orderSaved.getId());
                orderDetail1.setOrderByOrderId(order1);
                Product product = new Product();
                product.setId(orderDetail.getProductId());
                orderDetail1.setProductByProductId(product);
                orderDetail1.setQuantity(orderDetail.getQuantity());
                orderDetailRepo.save(orderDetail1);
            });
        }
        List<Cart> carts = cartRepo.findCartsByCustomerByCustomerId(customer.getId());
        if (carts != null) {
            carts.forEach(cart -> cartRepo.deleteById(cart.getId()));
        }
    }

    @RequestMapping(value = "/orders/user", method = RequestMethod.GET)
    public ResponseEntity<?> getCarts(@AuthenticationPrincipal Customer customer) {
        if (customer == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        List<Order> orders = orderRepo.findOrderByCustomerByCustomerId(customer.getId());
        List<OrderModel2> orderModel2s = new ArrayList<>();
        orders.forEach(o -> {
            OrderModel2 orderModel2 = OrderModel2.builder().
                    id(o.getId()).country(o.getCountry())
                    .status(o.getStatus()).phone(o.getPhone()).lastName(o.getLastName())
                    .companyName(o.getCompanyName()).firstName(o.getFirstName())
                    .quantity(o.getQuantity()).email(o.getEmail()).address(o.getAddress())
                    .price(o.getPrice()).build();
            List<OrderDetail> orderDetails = orderDetailRepo.findOrderDetailByOrderByOrderId(o.getId());
            if (orderDetails != null && orderDetails.size() != 0) {
                List<Product> products = new ArrayList<>();
                orderDetails.forEach(od -> {
                    Product product = productRepo.findById(od.getProductByProductId().getId()).get();
                    products.add(product);
                });
                orderModel2.setProducts(products);
            }
            orderModel2s.add(orderModel2);
        });
        return ResponseEntity.ok(
                orderModel2s
        );
    }
}
