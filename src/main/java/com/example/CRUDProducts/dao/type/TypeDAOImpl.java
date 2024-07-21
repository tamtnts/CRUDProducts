package com.example.CRUDProducts.dao.type;

import com.example.CRUDProducts.entity.Product;
import com.example.CRUDProducts.entity.SubProduct;
import com.example.CRUDProducts.entity.Type;
import com.example.CRUDProducts.response.ProductResponse;
import com.example.CRUDProducts.response.SubProductResponse;
import com.example.CRUDProducts.response.TypeResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class TypeDAOImpl implements TypeDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //cach 1: em lấy hết bằng query xong e xài cái TypeResponseRowMapper() để chuyển qua Response
    @Override
    public List<TypeResponse> getAllTypes() {
        String sql = "SELECT " +
                "t.id AS type_id, " +
                "t.name AS type_name, " +
                "p.id AS product_id, " +
                "p.name AS product_name, " +
                "sp.id AS subproduct_id, " +
                "sp.name AS subproduct_name " +
                "FROM [Type] t " +
                "LEFT JOIN [Product] p ON t.id = p.type_id " +
                "LEFT JOIN [SubProduct] sp ON p.id = sp.product_id";

        return jdbcTemplate.query(sql, new TypeResponseRowMapper());
    }

    //cách 2: em xài query từng table xong em set từng list con vô list cha -> trả ra entity
    @Override
    public Optional<Type> getTypeById(Long id) {
        Type type = entityManager.find(Type.class, id);
        if (type == null) {
            return Optional.empty();
        }

        Query productQuery = entityManager.createQuery("SELECT p FROM Product p WHERE p.type.id = :typeId", Product.class);
        productQuery.setParameter("typeId", id);
        List<Product> products = productQuery.getResultList();

        for (Product product : products) {
            Query subProductQuery = entityManager.createQuery("SELECT sp FROM SubProduct sp WHERE sp.product.id = :productId", SubProduct.class);
            subProductQuery.setParameter("productId", product.getId());
            List<SubProduct> subProducts = subProductQuery.getResultList();
            product.setSubProducts(subProducts);
        }
        type.setProducts(products);
        return Optional.of(type);
    }

    private static class TypeResponseRowMapper implements RowMapper<TypeResponse> {
        public TypeResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
            Map<Long, TypeResponse> typeMap = new HashMap<>();
            Map<Long, ProductResponse> productMap = new HashMap<>();
            TypeResponse typeResponse = null;

            do {
                Long typeId = rs.getLong("type_id");
                if (typeId != null) {
                    typeResponse = typeMap.get(typeId);
                    if (typeResponse == null) {
                        typeResponse = new TypeResponse();
                        typeResponse.setIdRes(typeId);
                        typeResponse.setNameRes(rs.getString("type_name"));
                        typeResponse.setProductsRes(new ArrayList<>());
                        typeMap.put(typeId, typeResponse);
                    }

                    Long productId = rs.getLong("product_id");
                    if (productId != null) {
                        ProductResponse productResponse = productMap.get(productId);
                        if (productResponse == null) {
                            productResponse = new ProductResponse();
                            productResponse.setIdRes(productId);
                            productResponse.setNameRes(rs.getString("product_name"));
                            productResponse.setSubProductsRes(new ArrayList<>());
                            productMap.put(productId, productResponse);
                            typeResponse.getProductsRes().add(productResponse);
                        }

                        Long subProductId = rs.getLong("subproduct_id");
                        if (subProductId != null) {
                            SubProductResponse subProductResponse = new SubProductResponse();
                            subProductResponse.setIdRes(subProductId);
                            subProductResponse.setNameRes(rs.getString("subproduct_name"));
                            productResponse.getSubProductsRes().add(subProductResponse);
                        }
                    }
                }
            } while (rs.next());

            return typeResponse;
        }
    }
}
