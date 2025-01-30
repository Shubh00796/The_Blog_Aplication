package com.Personal.blogapplication.Service;

import com.Personal.blogapplication.Dtos.CartDTO;
import com.Personal.blogapplication.Dtos.CartItemDTO;
import com.Personal.blogapplication.Entity.Cart;
import com.Personal.blogapplication.Mappers.CartMapper;
import com.Personal.blogapplication.Repo.CartRepository;
import com.Personal.blogapplication.Repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartMapper cartMapper;

    @Autowired
    public CartService(CartRepository cartRepository, ProductRepository productRepository, CartMapper cartMapper) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.cartMapper = cartMapper;
    }

    public void addProductToCart(Long userId, Long productId, int quantity) {
        if (!productRepository.existsById(productId)) {
            throw new IllegalArgumentException("Product not found");
        }

        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> new Cart(null, userId, new ArrayList<>()));

        cart.getItems().add(new CartItemDTO(productId, quantity));

        cartRepository.save(cart);
    }

    public CartDTO viewCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found"));

        return cartMapper.toDTO(cart);
    }
}
