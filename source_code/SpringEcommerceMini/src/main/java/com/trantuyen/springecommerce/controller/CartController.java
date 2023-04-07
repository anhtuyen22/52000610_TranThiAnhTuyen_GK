package com.trantuyen.springecommerce.controller;

import com.trantuyen.springecommerce.entity.Cart;
import com.trantuyen.springecommerce.entity.Customer;
import com.trantuyen.springecommerce.entity.Product;
import com.trantuyen.springecommerce.models.CartModel;
import com.trantuyen.springecommerce.repo.CartRepo;
import com.trantuyen.springecommerce.repo.CustomerRepo;
import com.trantuyen.springecommerce.repo.ProductRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CartController {

    private final CartRepo cartRepo;
    private final ProductRepo productRepo;
    private final CustomerRepo customerRepo;

    public CartController(CartRepo cartRepo, ProductRepo productRepo, CustomerRepo customerRepo) {
        this.cartRepo = cartRepo;
        this.productRepo = productRepo;
        this.customerRepo = customerRepo;
    }

    @RequestMapping(value = "/carts/update", method = RequestMethod.POST)
    public void updateProductToCart(@RequestBody CartModel.AddProductToCart addProductToCart, @AuthenticationPrincipal Customer customer) {
        List<Cart> carts = cartRepo.findCartsByCustomerByCustomerId(customer.getId());
        Optional<Cart> optCart = carts.stream().filter(c -> c.getProductByProductId().getId() == addProductToCart.getProductId())
                .findFirst();
        optCart.ifPresent(cart -> {
            cart.setQuantity(addProductToCart.getAmount());
            cartRepo.save(cart);
        });
    }

    @RequestMapping(value = "/carts", method = RequestMethod.POST)
    public void addProductToCart(@RequestBody CartModel.AddProductToCart addProductToCart, @AuthenticationPrincipal Customer customer) {
        List<Cart> carts = cartRepo.findCartsByCustomerByCustomerId(customer.getId());
        Optional<Cart> optCart = carts.stream().filter(c -> c.getProductByProductId().getId() == addProductToCart.getProductId())
                .findFirst();
        optCart.ifPresentOrElse(cart -> {
                    cart.setQuantity(cart.getQuantity() + addProductToCart.getAmount());
                    cartRepo.save(cart);
                }
                , () -> {
                    Cart cart = new Cart();
                    Product product = new Product();
                    product.setId(addProductToCart.getProductId());
                    cart.setQuantity(addProductToCart.getAmount());
                    cart.setProductByProductId(product);
                    cart.setCustomerByCustomerId(customer);
                    cartRepo.save(cart);
                });
    }

    @RequestMapping(value = "/carts", method = RequestMethod.GET)
    public ResponseEntity<?> getCarts(@AuthenticationPrincipal Customer customer) {
        if (customer == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        List<Cart> carts = cartRepo.findCartsByCustomerByCustomerId(customer.getId());
        return ResponseEntity.ok(
                carts
        );
    }
}
