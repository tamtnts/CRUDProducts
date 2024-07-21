package com.example.CRUDProducts.dao.product;

import com.example.CRUDProducts.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class ProductDAOImpl implements ProductDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Product> getAllProducts() {
        String sql = "SELECT p FROM Product p LEFT JOIN FETCH p.subProducts";
        Query query = entityManager.createQuery(sql, Product.class);
        return query.getResultList();
    }

    @Override
    public Product getProductById(Long id) {
        String sql = "SELECT p FROM Product p LEFT JOIN FETCH p.subProducts WHERE p.id = :id";
        Query query = entityManager.createQuery(sql, Product.class);
        query.setParameter("id", id);
        return (Product) query.getSingleResult();
    }
}
