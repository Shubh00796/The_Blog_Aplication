package com.Personal.blogapplication.Mappers;

import com.Personal.blogapplication.Dtos.AppointmentDTO;
import com.Personal.blogapplication.Dtos.ProductDTO;
import com.Personal.blogapplication.Entity.Appointment;
import com.Personal.blogapplication.Entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toEntity(ProductDTO productDTO);
    ProductDTO toDTO(Product product);
        // Important! Tells MapStruct this is an update method
}
