package com.Personal.blogapplication.Mappers;

import com.Personal.blogapplication.Dtos.*;
import com.Personal.blogapplication.Entity.Cart;
import com.Personal.blogapplication.Entity.CartItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartItemMapper {
    CartItem toEntity(CartEcomDTO cartEcomDTO);
    CartItem toEntityCart(AddCartItemRequestDTO addCartItemRequestDTO);
    CartEcomDTO toDTO(CartItem cartItem);

        // Important! Tells MapStruct this is an update method
}
