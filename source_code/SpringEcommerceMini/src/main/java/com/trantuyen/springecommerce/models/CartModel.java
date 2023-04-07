package com.trantuyen.springecommerce.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class CartModel {
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class AddProductToCart{
        private Long productId;
        private Integer amount;
    }
}
