package com.example.CRUDProducts.repository;

import com.example.CRUDProducts.dto.ProductDTO;
import com.example.CRUDProducts.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByTypeId(Long id);
}
