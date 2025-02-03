package com.Personal.blogapplication.Service;

import com.Personal.blogapplication.Dtos.ProductForCatlogDTO;
import com.Personal.blogapplication.Entity.ProductCatlog;
import com.Personal.blogapplication.Mappers.ProductCatlogMapper;
import com.Personal.blogapplication.Repo.ProductCatlogRepository;
import com.Personal.blogapplication.enums.Category;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class ProductCatlogServiceImpl implements ProductCatlogService {

    private final ProductCatlogRepository productCatlogRepository;
    private final ProductCatlogMapper productCatlogMapper;

    @Override
    @Transactional
    public ProductForCatlogDTO createProduct(ProductForCatlogDTO productForCatlogDTO) {
        log.debug("Creating product: {}", productForCatlogDTO);
        ProductCatlog productEntity = productCatlogMapper.toEntity(productForCatlogDTO);
        ProductCatlog savedProduct = productCatlogRepository.save(productEntity);
        log.info("Product created with ID: {}", savedProduct.getId());
        return productCatlogMapper.toDTO(savedProduct);
    }

    @Override
    public Page<ProductForCatlogDTO> getAllProducts(Pageable pageable) {
        log.debug("Fetching products with pagination: {}", pageable);
        return productCatlogRepository.findAll(pageable)
                .map(productCatlogMapper::toDTO);
    }

    @Override
    public ProductForCatlogDTO getProductById(Long id) {
        log.debug("Fetching product with ID: {}", id);
        return productCatlogRepository.findById(id)
                .map(productCatlogMapper::toDTO)
                .orElseThrow(() -> {
                    log.error("Product not found with ID: {}", id);
                    return new RuntimeException("Product not found with ID: " + id);
                });
    }

    @Override
    @Transactional
    public ProductForCatlogDTO updateProduct(Long id, ProductForCatlogDTO productDTO) {
        log.debug("Updating product with ID: {} using data: {}", id, productDTO);
        ProductCatlog existingProduct = productCatlogRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Product not found with ID: {}", id);
                    return new RuntimeException("Product not found with ID: " + id);
                });

        existingProduct.setName(productDTO.getName());
        existingProduct.setDescription(productDTO.getDescription());

        try {
            existingProduct.setCategory(Category.valueOf(productDTO.getCategory().toUpperCase()));
        } catch (IllegalArgumentException e) {
            log.error("Invalid category provided: {}", productDTO.getCategory());
            throw new IllegalArgumentException("Invalid category: " + productDTO.getCategory(), e);
        }

        existingProduct.setPrice(productDTO.getPrice());
        existingProduct.setStockQuantity(productDTO.getStockQuantity());
        existingProduct.setRating(productDTO.getRating());

        ProductCatlog updatedProduct = productCatlogRepository.save(existingProduct);
        log.info("Product updated with ID: {}", updatedProduct.getId());
        return productCatlogMapper.toDTO(updatedProduct);
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        log.debug("Deleting product with ID: {}", id);
        if (!productCatlogRepository.existsById(id)) {
            log.error("Product not found with ID: {}", id);
            throw new RuntimeException("Product not found with ID: " + id);
        }
        productCatlogRepository.deleteById(id);
        log.info("Product deleted with ID: {}", id);
    }

    @Override
    public List<ProductForCatlogDTO> filterProductsByCategory(String category) {
        log.debug("Filtering products by category: {}", category);
        Category categoryEnum;
        try {
            categoryEnum = Category.valueOf(category.toUpperCase());
        } catch (IllegalArgumentException e) {
            log.error("Invalid category provided: {}", category);
            throw new IllegalArgumentException("Invalid category: " + category, e);
        }

        return productCatlogRepository.findByCategory(categoryEnum)
                .stream()
                .map(productCatlogMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductForCatlogDTO> filterProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        log.debug("Filtering products by price range: {} - {}", minPrice, maxPrice);
        return productCatlogRepository.findByPriceBetween(minPrice, maxPrice)
                .stream()
                .map(productCatlogMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductForCatlogDTO> filterProductsByRating(Double rating) {
        log.debug("Filtering products by rating greater than or equal to: {}", rating);
        return productCatlogRepository.findByRatingGreaterThanEqual(rating)
                .stream()
                .map(productCatlogMapper::toDTO)
                .collect(Collectors.toList());
    }


}
