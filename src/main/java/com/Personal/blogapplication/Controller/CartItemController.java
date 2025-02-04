package com.Personal.blogapplication.Controller;

import com.Personal.blogapplication.Dtos.*;
import com.Personal.blogapplication.Service.CartEcomServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart/items")
@RequiredArgsConstructor
public class CartItemController {

    private final CartEcomServiceImpl cartEcomService;

    @PostMapping("/add/{userId}")
    public ResponseEntity<CartEcomDTO> addItemToCart(@PathVariable String userId,
                                                     @RequestBody AddCartItemRequestDTO request) {
        try {
            CartEcomDTO cartItemDTO = cartEcomService.addItemToCarts(userId, request);
            return new ResponseEntity<>(cartItemDTO, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);  // Return 404 if product is not found
        }
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<CartResponseDTO> removeItemFromCart(@PathVariable Long id) {
        try {
            CartResponseDTO response = cartEcomService.removeItemFromCart(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);  // Return 404 if cart item is not found
        }
    }

    @PutMapping("/update/{cartItemId}")
    public ResponseEntity<CartEcomDTO> updateCartItemQuantity(@PathVariable Long cartItemId,
                                                              @RequestBody UpdateCartItemRequestDTO request) {
        try {
            CartEcomDTO updatedItem = cartEcomService.updateCartItemQuantity(cartItemId, request);
            return new ResponseEntity<>(updatedItem, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);  // Return 400 if invalid quantity
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);  // Return 404 if cart item is not found
        }
    }
}
