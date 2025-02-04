package com.Personal.blogapplication.Service;

import com.Personal.blogapplication.Dtos.ProductEcomRequestDTO;
import com.Personal.blogapplication.Dtos.ProductEcomResponseDTO;

import java.util.List;

public interface ProductEcomService {
    List<ProductEcomResponseDTO> getAllProducts();
    ProductEcomResponseDTO getProductById(String id);
    ProductEcomResponseDTO createProduct(ProductEcomRequestDTO productEcomRequestDTO);
    ProductEcomResponseDTO updateProduct(String id, ProductEcomRequestDTO request);
    void deleteProduct(String id);
}