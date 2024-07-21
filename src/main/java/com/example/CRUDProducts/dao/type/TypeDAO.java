package com.example.CRUDProducts.dao.type;

import com.example.CRUDProducts.entity.Type;
import com.example.CRUDProducts.response.TypeResponse;

import java.util.List;
import java.util.Optional;

public interface TypeDAO {
    List<TypeResponse> getAllTypes();
    Optional<Type> getTypeById(Long id);
}
