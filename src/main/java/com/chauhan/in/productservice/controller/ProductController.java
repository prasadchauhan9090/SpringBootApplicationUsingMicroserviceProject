package com.chauhan.in.productservice.controller;

import com.chauhan.in.productservice.dto.ProductRequest;
import com.chauhan.in.productservice.dto.ProductResponse;
import com.chauhan.in.productservice.service.ProductService;
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
    public void createProduct(@RequestBody ProductRequest productRequest)
    {
    productService.createProduct(productRequest);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public List<ProductResponse> getAllProducts()
    {

        return productService.getAllProduct();

    }




}
