package com.example.CRUDProducts.repository;

import com.example.CRUDProducts.entity.SubProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubProductRepository extends JpaRepository<SubProduct, Long> {
}
