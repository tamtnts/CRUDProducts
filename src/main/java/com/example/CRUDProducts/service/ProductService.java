package com.example.CRUDProducts.service;

import com.example.CRUDProducts.dto.ProductDTO;

import java.util.List;

public interface ProductService {

     List<ProductDTO> getAllProducts();

    ProductDTO getProductById(Long id);

    ProductDTO createProduct(ProductDTO productDTO);

    ProductDTO updateProduct(Long id, ProductDTO productDTO);

    void deleteProduct(Long id);

}
