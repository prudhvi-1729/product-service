package com.capstone.product_service.service;

import com.capstone.product_service.dto.ProductRequest;
import com.capstone.product_service.dto.ProductResponse;
import com.capstone.product_service.model.Product;
import com.capstone.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public ProductResponse createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .build();

        productRepository.save(product);
        return new ProductResponse(product.getId(),product.getName(),product.getDescription(),product.getPrice());
    }

    public List<ProductResponse> getAllProducts(){
        return productRepository.findAll()
                .stream().map(product -> new ProductResponse(product.getId(),product.getName(),product.getDescription(),product.getPrice())).toList();
    }

}
