package com.Personal.blogapplication.Controller;

import com.Personal.blogapplication.Dtos.CartDTO;
import com.Personal.blogapplication.Service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    // DTO for request body
    public static class AddToCartRequest {
        public Long userId;
        public Long productId;
        public int quantity;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addProductToCart(@RequestBody AddToCartRequest request) {
        try {
            cartService.addProductToCart(request.userId, request.productId, request.quantity);
            return ResponseEntity.ok("Product added to cart successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("An error occurred: " + e.getMessage());
        }
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> removeProductFromCart(@RequestParam Long userId,
                                                        @RequestParam Long productId) {
        try {
            cartService.removeProductFromCart(userId, productId);
            return ResponseEntity.ok("Product removed from cart successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/view")
    public ResponseEntity<CartDTO> viewCart(@RequestParam Long userId) {
        try {
            CartDTO cartDTO = cartService.viewCart(userId);
            return ResponseEntity.ok(cartDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
