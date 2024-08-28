package com.capstone.product_service.controller;

import com.capstone.product_service.dto.ProductRequest;
import com.capstone.product_service.dto.ProductResponse;
import com.capstone.product_service.model.Product;
import com.capstone.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest){
        return productService.createProduct(productRequest);
    }

    @GetMapping
    public List<ProductResponse> getAllProducts(){
        return productService.getAllProducts();
    }
}
