package com.Personal.blogapplication.Service;

import com.Personal.blogapplication.Dtos.*;
import com.Personal.blogapplication.Entity.CartItem;
import com.Personal.blogapplication.Mappers.CartItemMapper;
import com.Personal.blogapplication.Repo.CartItemRepository;
import com.Personal.blogapplication.Repo.ProductForEcomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CartEcomServiceImpl implements CartEcomService {

    private final ProductForEcomRepository productForEcomRepository;
    private final CartItemRepository cartItemRepository;
    private final CartItemMapper cartItemMapper;

    @Override
    public CartEcomDTO addItemToCarts(String userId, AddCartItemRequestDTO request) {
        productForEcomRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("The product for the given ID is not present"));

        CartItem entity = cartItemMapper.toEntityCart(request);
        entity.setUserId(userId); // Ensure user association
        CartItem savedEntity = cartItemRepository.save(entity);

       return cartItemMapper.toDTO(savedEntity);
    }


    @Override
    public CartResponseDTO removeItemFromCart(Long id) {
        CartItem cartItem = cartItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        cartItemRepository.delete(cartItem); // Remove item from cart

        return new CartResponseDTO();
    }

    @Override
    public CartEcomDTO updateCartItemQuantity(Long cartItemId, UpdateCartItemRequestDTO request) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("The given item is not present"));

        if (request.getQuantity() > 0) {
            cartItem.setQuantity(request.getQuantity());
        } else {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }

        CartItem savedCartItem = cartItemRepository.save(cartItem);

        return cartItemMapper.toDTO(savedCartItem);
    }



}
