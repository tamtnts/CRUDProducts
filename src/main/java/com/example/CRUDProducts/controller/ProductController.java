package com.example.CRUDProducts.controller;

import com.example.CRUDProducts.dto.ProductDTO;
import com.example.CRUDProducts.mapper.ProductMapper;
import com.example.CRUDProducts.controller.response.ProductResponse;
import com.example.CRUDProducts.service.productService.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductMapper productMapper;

    @GetMapping("/all")
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts()
                .stream()
                .map(productMapper::toResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable Long id) {
        ProductDTO productDTO = productService.getProductById(id);
        return productMapper.toResponse(productDTO);
    }

    @PostMapping("/create")
    public ProductResponse createProduct(@RequestBody @Valid ProductDTO productDTO) {
        ProductDTO createdProductDTO = productService.createProduct(productDTO);
        return productMapper.toResponse(createdProductDTO);
    }

    @PutMapping("/update/{id}")
    public ProductResponse updateProduct(@PathVariable Long id, @RequestBody @Valid ProductDTO productDTO) {
        ProductDTO updatedProductDTO = productService.updateProduct(id, productDTO);
        return productMapper.toResponse(updatedProductDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}