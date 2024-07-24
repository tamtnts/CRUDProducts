package com.example.CRUDProducts.repository;

import com.example.CRUDProducts.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {

    @Query("SELECT t FROM Type t")
    List<Type> findAllTypes();

    @Query("SELECT DISTINCT t FROM Type t WHERE t.id = :id")
    Type getTypeById(Long id);


    boolean existsById(Long id);

    boolean existsByName(String name);

    Optional<Type> findByName(String name);

    void deleteById(Long id);


}
