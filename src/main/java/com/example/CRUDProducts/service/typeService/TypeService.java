package com.example.CRUDProducts.service.typeService;

import com.example.CRUDProducts.entity.Type;
import com.example.CRUDProducts.response.TypeResponse;

import java.util.List;

public interface TypeService {
    List<TypeResponse> getAllTypes();
    TypeResponse getType(Long typeId);
    TypeResponse createType(Type type);
    TypeResponse updateType(Long id, Type type);
    void deleteType(Long id);
}
