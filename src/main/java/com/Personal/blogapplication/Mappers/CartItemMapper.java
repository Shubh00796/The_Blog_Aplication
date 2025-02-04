package com.Personal.blogapplication.Mappers;

import com.Personal.blogapplication.Dtos.CartDTO;
import com.Personal.blogapplication.Dtos.CartItemDTO;
import com.Personal.blogapplication.Entity.Cart;
import com.Personal.blogapplication.Entity.CartItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartItemMapper {
    CartItem toEntity(CartItemDTO cartItemDTO);
    CartItemDTO toDTO(CartItem cartItem);
        // Important! Tells MapStruct this is an update method
}
