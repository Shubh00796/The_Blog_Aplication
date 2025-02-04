package com.Personal.blogapplication.Service;

import com.Personal.blogapplication.Dtos.*;

public interface CartEcomService {
    CartEcomDTO addItemToCarts(String userId, AddCartItemRequestDTO request);

    CartResponseDTO removeItemFromCart(Long id);

    CartEcomDTO updateCartItemQuantity(Long cartItemId, UpdateCartItemRequestDTO request);



}
