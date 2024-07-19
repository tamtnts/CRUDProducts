package com.example.CRUDProducts.service.productService;

import com.example.CRUDProducts.dto.ProductDTO;
import com.example.CRUDProducts.response.ProductResponse;

import java.util.List;

public interface ProductService {

     List<ProductResponse> getAllProducts();

    ProductResponse getProductById(Long id);

    ProductResponse createProduct(ProductDTO productDTO);

    ProductResponse updateProduct(Long id, ProductDTO productDTO);

    void deleteProduct(Long id);

}
