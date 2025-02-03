package com.Personal.blogapplication.Service;

import com.Personal.blogapplication.Dtos.ProductForCatlogDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface ProductCatlogService {
    ProductForCatlogDTO createProduct(ProductForCatlogDTO productForCatlogDTO);

    Page<ProductForCatlogDTO> getAllProducts(Pageable pageable);

    ProductForCatlogDTO getProductById(Long id);

    ProductForCatlogDTO updateProduct(Long id, ProductForCatlogDTO productForCatlogDTO);

    void deleteProduct(Long id);

    List<ProductForCatlogDTO> filterProductsByCategory(String category);

    List<ProductForCatlogDTO> filterProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice);

    List<ProductForCatlogDTO> filterProductsByRating(Double rating);







}
