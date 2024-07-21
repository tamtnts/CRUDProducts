package com.example.CRUDProducts.service.productService;

import com.example.CRUDProducts.dto.ProductDTO;
import com.example.CRUDProducts.response.ProductResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface ProductService {

     List<ProductResponse> getAllProducts();

    ProductResponse getProductById(Long id);

    ProductResponse createProduct(@Valid ProductDTO productDTO);

    ProductResponse updateProduct(Long id,@Valid ProductDTO productDTO);

    void deleteProduct(Long id);

}
