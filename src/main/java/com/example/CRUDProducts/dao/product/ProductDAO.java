package com.example.CRUDProducts.dao.product;

import com.example.CRUDProducts.entity.Product;
import java.util.List;

public interface ProductDAO {
    List<Product> getAllProducts();
    Product getProductById(Long id);
}
