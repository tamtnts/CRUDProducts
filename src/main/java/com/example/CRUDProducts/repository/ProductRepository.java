package com.example.CRUDProducts.repository;

import com.example.CRUDProducts.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByTypeId(Long id);

    @Query("SELECT p FROM Product p LEFT JOIN FETCH p.subProducts")
    List<Product> getAllProducts();

    @Query("SELECT p FROM Product p LEFT JOIN FETCH p.subProducts WHERE p.id = :id")
    Product getProductById(Long id);
}
