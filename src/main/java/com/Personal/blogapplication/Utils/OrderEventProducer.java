//package com.Personal.blogapplication.Utils;
//
//import com.Personal.blogapplication.Entity.Order;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Component;
//
//@Component
//@Slf4j
//@RequiredArgsConstructor
//public class OrderEventProducer {
//
//    private final KafkaTemplate<String, String> kafkaTemplate;
//    private final ObjectMapper objectMapper;
//
//    public void publishOrderCreatedEvent(Order order) {
//        try {
//            String orderJson = objectMapper.writeValueAsString(order);
//            kafkaTemplate.send("order-created", orderJson);
//        } catch (JsonProcessingException e) {
//            log.error("Error serializing order: {}", e.getMessage());
//        }
//    }
//
//    public void publishOrderCancelledEvent(Order order) {
//        try {
//            String orderJson = objectMapper.writeValueAsString(order);
//            kafkaTemplate.send("order-cancelled", orderJson);
//        } catch (JsonProcessingException e) {
//            log.error("Error serializing order: {}", e.getMessage());
//        }
//    }
//
//    public void publishOrderUpdatedEvent(Order order) {
//        try {
//            String orderJson = objectMapper.writeValueAsString(order);
//            kafkaTemplate.send("order-updated", orderJson);
//        } catch (JsonProcessingException e) {
//            log.error("Error serializing order (updated): {}", e.getMessage());
//        }
//    }
//}
