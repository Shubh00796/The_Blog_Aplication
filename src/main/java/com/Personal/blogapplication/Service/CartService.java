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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductService productService;
    private final CartMapper cartMapper; // Injecting the mapper
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    public CartService(CartRepository cartRepository, ProductService productService, CartMapper cartMapper) {
        this.cartRepository = cartRepository;
        this.productService = productService;
        this.cartMapper = cartMapper;
    }

    public void addProductToCart(Long userId, Long productId, int quantity) throws IllegalAccessException {
        if (!productRepository.existsById(productId)) {
            throw new IllegalAccessException("Product not found");
        }

        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> cartRepository.save(new Cart(null, userId, new ArrayList<>())));

        List<CartItemDTO> items = cart.getItems();
        Optional<CartItemDTO> existingItem = items.stream()
                .filter(item -> item.getProductId().equals(productId))
                .findFirst();

        if (existingItem.isPresent()) {
            existingItem.get().setQuantity(existingItem.get().getQuantity() + quantity);
        } else {
            items.add(new CartItemDTO(productId, quantity));
        }

        cart.setItems(items);
        cartRepository.save(cart);
    }

    public void removeProductFromCart(Long userId, Long productId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found"));

        cart.setItems(cart.getItems().stream()
                .filter(item -> !item.getProductId().equals(productId))
                .collect(Collectors.toList()));

        cartRepository.save(cart);
    }

    public CartDTO viewCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found"));

        return cartMapper.toDTO(cart); // Using the mapper to convert entity to DTO
    }
}
