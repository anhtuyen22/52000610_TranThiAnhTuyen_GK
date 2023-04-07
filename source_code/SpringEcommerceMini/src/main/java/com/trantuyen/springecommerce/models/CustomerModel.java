package com.trantuyen.springecommerce.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerModel {
    private final Integer __v = 0;
    private Long id;
    private String accessToken;
    private Boolean admin;
    private String avatar;
    private String username;
}
