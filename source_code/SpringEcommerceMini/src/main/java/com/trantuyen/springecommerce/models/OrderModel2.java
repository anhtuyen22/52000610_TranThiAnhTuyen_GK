package com.trantuyen.springecommerce.models;

import com.trantuyen.springecommerce.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderModel2 {
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
    private List<Product> products;
}
