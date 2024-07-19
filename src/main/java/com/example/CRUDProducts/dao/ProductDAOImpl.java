package com.example.CRUDProducts.dao;

import com.example.CRUDProducts.dao.ProductDAO;
import com.example.CRUDProducts.entity.Product;
import com.example.CRUDProducts.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findByTypeId(Long typeId) {
        return productRepository.findByTypeId(typeId);
    }
}
