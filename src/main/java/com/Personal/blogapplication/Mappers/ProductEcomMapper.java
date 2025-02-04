package com.Personal.blogapplication.Mappers;

import com.Personal.blogapplication.Dtos.ProductEcomRequestDTO;
import com.Personal.blogapplication.Dtos.ProductEcomResponseDTO;
import com.Personal.blogapplication.Dtos.ProductForCatlogDTO;
import com.Personal.blogapplication.Entity.ProductCatlog;
import com.Personal.blogapplication.Entity.ProductForEcom;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductEcomMapper {
    ProductForEcom toEntityRequest(ProductEcomRequestDTO productEcomRequestDTO);
//    ProductForEcom toEntityResponce(ProductEcomResponseDTO productEcomResponseDTO);
    ProductEcomResponseDTO toDTO(ProductForEcom productForEcom);
        // Important! Tells MapStruct this is an update method
}
