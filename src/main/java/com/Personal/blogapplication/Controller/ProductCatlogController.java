package com.Personal.blogapplication.Controller;

import com.Personal.blogapplication.Dtos.ProductForCatlogDTO;
import com.Personal.blogapplication.Service.ProductCatlogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@Slf4j
@RequiredArgsConstructor
public class ProductCatlogController {

    private final ProductCatlogService productCatlogService;

    @PostMapping
    public ResponseEntity<ProductForCatlogDTO> createProduct(@RequestBody ProductForCatlogDTO productDTO) {
        log.debug("REST request to create product: {}", productDTO);
        ProductForCatlogDTO createdProduct = productCatlogService.createProduct(productDTO);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<ProductForCatlogDTO>> getAllProducts(Pageable pageable) {
        log.debug("REST request to get all products with pagination: {}", pageable);
        Page<ProductForCatlogDTO> productsPage = productCatlogService.getAllProducts(pageable);
        return ResponseEntity.ok(productsPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductForCatlogDTO> getProductById(@PathVariable Long id) {
        log.debug("REST request to get product with ID: {}", id);
        ProductForCatlogDTO productDTO = productCatlogService.getProductById(id);
        return ResponseEntity.ok(productDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductForCatlogDTO> updateProduct(@PathVariable Long id,
                                                             @RequestBody ProductForCatlogDTO productDTO) {
        log.debug("REST request to update product with ID: {} using data: {}", id, productDTO);
        ProductForCatlogDTO updatedProduct = productCatlogService.updateProduct(id, productDTO);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        log.debug("REST request to delete product with ID: {}", id);
        productCatlogService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filter/category")
    public ResponseEntity<List<ProductForCatlogDTO>> filterProductsByCategory(@RequestParam String category) {
        log.debug("REST request to filter products by category: {}", category);
        List<ProductForCatlogDTO> products = productCatlogService.filterProductsByCategory(category);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/filter/price")
    public ResponseEntity<List<ProductForCatlogDTO>> filterProductsByPriceRange(@RequestParam BigDecimal minPrice,
                                                                                @RequestParam BigDecimal maxPrice) {
        log.debug("REST request to filter products by price range: {} - {}", minPrice, maxPrice);
        List<ProductForCatlogDTO> products = productCatlogService.filterProductsByPriceRange(minPrice, maxPrice);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/filter/rating")
    public ResponseEntity<List<ProductForCatlogDTO>> filterProductsByRating(@RequestParam Double rating) {
        log.debug("REST request to filter products by rating greater than or equal to: {}", rating);
        List<ProductForCatlogDTO> products = productCatlogService.filterProductsByRating(rating);
        return ResponseEntity.ok(products);
    }
}
