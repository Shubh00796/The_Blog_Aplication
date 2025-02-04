package com.Personal.blogapplication.Service;

import com.Personal.blogapplication.Dtos.ProductEcomRequestDTO;
import com.Personal.blogapplication.Dtos.ProductEcomResponseDTO;
import com.Personal.blogapplication.Entity.ProductForEcom;
import com.Personal.blogapplication.Exceptions.CustomException;
import com.Personal.blogapplication.Mappers.ProductEcomMapper;
import com.Personal.blogapplication.Repo.ProductForEcomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductForEcomServiceImpl implements ProductEcomService {
    private final ProductForEcomRepository productForEcomRepository;
    private final ProductEcomMapper productEcomMapper;

    @Override
    public List<ProductEcomResponseDTO> getAllProducts() {
        log.info("Fetching all products");
        List<ProductEcomResponseDTO> products = productForEcomRepository.findAll()
                .stream()
                .map(productEcomMapper::toDTO)
                .collect(Collectors.toList());
        log.info("Fetched {} products", products.size());
        return products;
    }

    @Override
    public ProductEcomResponseDTO getProductById(String id) {
        log.info("Fetching product with id {}", id);
        ProductEcomResponseDTO product = productForEcomRepository.findById(id)
                .map(productEcomMapper::toDTO)
                .orElseThrow(() -> {
                    log.error("Product with id {} not found", id);
                    return new CustomException("Product not found");
                });
        log.info("Fetched product with id {}", id);
        return product;
    }

    @Override
    public ProductEcomResponseDTO createProduct(ProductEcomRequestDTO productEcomRequestDTO) {
        log.info("Creating new product: {}", productEcomRequestDTO.getName());
        ProductForEcom entity = productEcomMapper.toEntityRequest(productEcomRequestDTO);
        ProductForEcom savedProduct = productForEcomRepository.save(entity);
        log.info("Product created with id {}", savedProduct.getId());
        return productEcomMapper.toDTO(savedProduct);
    }

    @Override
    public ProductEcomResponseDTO updateProduct(String id, ProductEcomRequestDTO request) {
        log.info("Updating product with id {}", id);
        ProductForEcom existingProduct = productForEcomRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Product with id {} not found", id);
                    return new RuntimeException("Product not found");
                });

        updateProductFields(existingProduct, request);

        ProductForEcom savedProduct = productForEcomRepository.save(existingProduct);
        log.info("Product with id {} updated", id);

        return productEcomMapper.toDTO(savedProduct);
    }

    @Override
    public void deleteProduct(String id) {
        log.info("Deleting product with id {}", id);
        productForEcomRepository.deleteById(id);
        log.info("Product with id {} deleted", id);
    }

    private void updateProductFields(ProductForEcom existingProduct, ProductEcomRequestDTO request) {
        if (request.getName() != null) {
            existingProduct.setName(request.getName());
        }
        if (request.getDescription() != null) {
            existingProduct.setDescription(request.getDescription());
        }
        if (request.getPrice() != 0) {
            existingProduct.setPrice(request.getPrice());
        }
        if (request.getStock() != 0) {
            existingProduct.setStock(request.getStock());
        }
    }
}
