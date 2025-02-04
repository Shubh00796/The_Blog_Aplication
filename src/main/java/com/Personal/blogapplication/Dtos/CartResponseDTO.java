package com.Personal.blogapplication.Dtos;

import com.Personal.blogapplication.Entity.CartItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartResponseDTO {
    private List<CartItem> items;
    private double totalAmount;
}
