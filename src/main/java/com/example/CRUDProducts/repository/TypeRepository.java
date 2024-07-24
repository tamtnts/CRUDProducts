package com.example.CRUDProducts.repository;

import com.example.CRUDProducts.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {

    @Query("SELECT DISTINCT t FROM Type t LEFT JOIN FETCH t.products p LEFT JOIN FETCH p.subProducts")
    List<Type> getAllTypes();

    @Query("SELECT DISTINCT t FROM Type t LEFT JOIN FETCH t.products p LEFT JOIN FETCH p.subProducts WHERE t.id = :id")
    Optional<Type> getTypeById(Long id);


    boolean existsById(Long id);

    boolean existsByName(String name);

    Optional<Type> findByName(String name);

    void deleteById(Long id);
}
