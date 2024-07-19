package com.example.CRUDProducts.dao;

import com.example.CRUDProducts.entity.Product;
import java.util.List;

public interface ProductDAO {
    List<Product> findByTypeId(Long typeId);
}
