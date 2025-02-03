package com.Personal.blogapplication.Mappers;

import com.Personal.blogapplication.Dtos.CartDTO;
import com.Personal.blogapplication.Dtos.ProductForCatlogDTO;
import com.Personal.blogapplication.Entity.Cart;
import com.Personal.blogapplication.Entity.ProductCatlog;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductCatlogMapper {
    ProductCatlog toEntity(ProductForCatlogDTO productForCatlogDTO);
    ProductForCatlogDTO toDTO(ProductCatlog productCatlog);
        // Important! Tells MapStruct this is an update method
}
