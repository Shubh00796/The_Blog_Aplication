package com.Personal.blogapplication.Service;

import com.Personal.blogapplication.Dtos.OrderDTO;
import com.Personal.blogapplication.Entity.Order;
import com.Personal.blogapplication.Mappers.OrderMapper;
import com.Personal.blogapplication.Repo.OrderRepository;
import com.Personal.blogapplication.enums.OrderStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = orderMapper.orderDTOToOrder(orderDTO);
        order.setStatus(OrderStatus.PENDING);

        Order savedOrder = orderRepository.save(order);



        return orderMapper.orderToOrderDTO(savedOrder);
    }

    @Override
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(OrderStatus.CANCELLED);

        Order cancelledOrder = orderRepository.save(order);


    }

    @Override
    public OrderDTO updateOrder(Long orderId, OrderDTO orderDTO) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order with the given id not found"));

        if (orderDTO.getCustomerId() != null) {
            order.setCustomerId(orderDTO.getCustomerId());
        }
        if (orderDTO.getProductIds() != null) {
            order.setProductIds(orderDTO.getProductIds());
        }
        if (orderDTO.getQuantities() != null) {
            order.setQuantities(orderDTO.getQuantities());
        }
        if (orderDTO.getTotalPrice() != null) {
            order.setTotalPrice(orderDTO.getTotalPrice());
        }
        if (orderDTO.getStatus() != null) {
            order.setStatus(OrderStatus.valueOf(orderDTO.getStatus()));
        }

        Order updatedOrder = orderRepository.save(order);



        return orderMapper.orderToOrderDTO(updatedOrder);
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(orderMapper::orderToOrderDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getOrdersByCustomerId(Long customerId) {
        return orderRepository.findByCustomerId(customerId).stream()
                .map(orderMapper::orderToOrderDTO)
                .collect(Collectors.toList());
    }
}
