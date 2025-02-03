package com.Personal.blogapplication.Service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class RatingUpdateService {

    @Async
    public void updateProductRating(Long productId, Double newRating) {
        try {
            Thread.sleep(2000); // Simulate a 2-second delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Updated rating for product ID: " + productId + " to " + newRating);
    }
}
