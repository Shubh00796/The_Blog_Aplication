package com.Personal.blogapplication.Mappers;

import com.Personal.blogapplication.Dtos.OrderDTO;
import com.Personal.blogapplication.Entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderMapper {
    OrderDTO orderToOrderDTO(Order order);
    Order orderDTOToOrder(OrderDTO orderDTO);

//    void updateOrderFromDto(OrderDTO orderDTO, @MappingTarget Order order);
}
