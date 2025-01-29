package com.Personal.blogapplication.Mappers;

import com.Personal.blogapplication.Dtos.CartDTO;
import com.Personal.blogapplication.Dtos.ProductDTO;
import com.Personal.blogapplication.Entity.Cart;
import com.Personal.blogapplication.Entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper {
    Cart toEntity(CartDTO cartDTO);
    CartDTO toDTO(Cart cart);
        // Important! Tells MapStruct this is an update method
}
