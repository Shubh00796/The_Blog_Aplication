package com.Personal.blogapplication.Service;

import com.Personal.blogapplication.Dtos.ProductDTO;
import com.Personal.blogapplication.Entity.Product;
import com.Personal.blogapplication.Mappers.ProductMapper;
import com.Personal.blogapplication.Repo.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductDTO createProduct(ProductDTO productDTO){
        Product entity = productMapper.toEntity(productDTO);
        Product savedProduct = productRepository.save(entity);
        return productMapper.toDTO(savedProduct);
    }
    public ProductDTO updateProduct(ProductDTO productDTO){
        Product product = productRepository.findById(productDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Product with given id not found"));

        if(product.getName() != null){
            product.setName(productDTO.getName());
        }
        if(product.getDescription() != null){
            product.setDescription(product.getDescription());
        }
        if(product.getStockQuantity() != 0){
            product.setStockQuantity(product.getStockQuantity());
        }
       if(product.getPrice() != 0){
           product.setPrice(product.getPrice());

       }

        Product entity = productMapper.toEntity(productDTO);
        Product save = productRepository.save(entity);
        return productMapper.toDTO(save);


    }

    public ProductDTO getProductById(Long id){
        Product productIdNotPresent = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product id not present"));
                return productMapper.toDTO(productIdNotPresent);
    }
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }
    public List<ProductDTO> searchProducts(String name){
        return productRepository.findByNameContainingIgnoreCase(name).stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        productRepository.delete(product);
    }
}
