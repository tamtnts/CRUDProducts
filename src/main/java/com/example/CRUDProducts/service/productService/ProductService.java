package com.example.CRUDProducts.service.productService;

import com.example.CRUDProducts.dto.ProductDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface ProductService {

    List<ProductDTO> getAllProducts();
    ProductDTO getProductById(Long id);
    ProductDTO createProduct(@Valid ProductDTO productDTO);
    ProductDTO updateProduct(Long id, @Valid ProductDTO productDTO);
    void deleteProduct(Long id);

}
