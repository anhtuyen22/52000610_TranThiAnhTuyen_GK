package com.trantuyen.springecommerce.models;

import com.trantuyen.springecommerce.entity.Customer;
import com.trantuyen.springecommerce.entity.OrderDetail;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderModel {
    private Long id;
    private String status;
    private BigDecimal price;
    private String firstName;
    private String lastName;
    private String companyName;
    private String country;
    private String address;
    private String phone;
    private String email;
    private Integer quantity;
    private Date createdAt;

    private Set<OrderDetailModel> orderDetails = new HashSet<>();
}
