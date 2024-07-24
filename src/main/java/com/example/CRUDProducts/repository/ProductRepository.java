package com.example.CRUDProducts.repository;

import com.example.CRUDProducts.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p LEFT JOIN FETCH p.subProducts")
    List<Product> getAllProducts();

    @Query("SELECT p FROM Product p LEFT JOIN FETCH p.subProducts WHERE p.id = :id")
    Product getProductById(Long id);

    Optional<Product> findByName(String name);

    boolean existsByNameAndIdNot(String name, Long id);
}
