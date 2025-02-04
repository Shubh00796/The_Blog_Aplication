package com.Personal.blogapplication.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartEcomDTO {

    private String userId;
    private String productId;
    private String productName;
    private int quantity;
    private double pricePerUnit;
}
