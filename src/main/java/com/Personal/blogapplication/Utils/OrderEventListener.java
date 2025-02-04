//package com.Personal.blogapplication.Utils;
//
//import com.Personal.blogapplication.Entity.Order;
//import com.Personal.blogapplication.Repo.OrderRepository;
//import com.Personal.blogapplication.enums.OrderStatus; // Import the enum
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
//
//@Component
//@Slf4j
//public class OrderEventListener {
//
//    @Autowired
//    private OrderRepository orderRepository;
//
//    @KafkaListener(topics = "payment-failed", groupId = "order-service-group")
//    public void handlePaymentFailedEvent(Order order) {
//        log.info("Payment failed for order ID: {}", order.getId());
//        order.setStatus(OrderStatus.CANCELLED);  // Use OrderStatus directly
//        orderRepository.save(order);
//    }
//
//    @KafkaListener(topics = "inventory-insufficient", groupId = "order-service-group")
//    public void handleInventoryInsufficientEvent(Order order) {
//        log.info("Inventory insufficient for order ID: {}", order.getId());
//        order.setStatus(OrderStatus.CANCELLED);  // Use OrderStatus directly
//        orderRepository.save(order);
//    }
//}
