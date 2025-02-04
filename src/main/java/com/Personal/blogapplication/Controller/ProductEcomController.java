package com.Personal.blogapplication.Controller;

import com.Personal.blogapplication.Dtos.ProductEcomRequestDTO;
import com.Personal.blogapplication.Dtos.ProductEcomResponseDTO;
import com.Personal.blogapplication.Service.ProductEcomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products/ecom")
@RequiredArgsConstructor
public class ProductEcomController {

    private final ProductEcomService productEcomService;

    @GetMapping
    public ResponseEntity<List<ProductEcomResponseDTO>> getAllProducts() {
        return ResponseEntity.ok(productEcomService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductEcomResponseDTO> getProductById(@PathVariable String id) {
        return ResponseEntity.ok(productEcomService.getProductById(id));
    }

    @PostMapping
    public ResponseEntity<ProductEcomResponseDTO> createProduct(@RequestBody ProductEcomRequestDTO productEcomRequestDTO) {
        return ResponseEntity.ok(productEcomService.createProduct(productEcomRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductEcomResponseDTO> updateProduct(@PathVariable String id, @RequestBody ProductEcomRequestDTO productEcomRequestDTO) {
        return ResponseEntity.ok(productEcomService.updateProduct(id, productEcomRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        productEcomService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
