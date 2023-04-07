package com.trantuyen.springecommerce.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetailModel {
    private Long productId;
    private Integer quantity;
}
