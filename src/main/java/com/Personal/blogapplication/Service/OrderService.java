package com.Personal.blogapplication.Service;

import com.Personal.blogapplication.Dtos.OrderDTO;

import java.util.List;

public interface OrderService {
    OrderDTO createOrder(OrderDTO orderDTO);
    void cancelOrder(Long orderId);
    OrderDTO updateOrder(Long orderId,OrderDTO orderDTO);
    List<OrderDTO> getAllOrders();
    List<OrderDTO> getOrdersByCustomerId(Long customerId);


}
