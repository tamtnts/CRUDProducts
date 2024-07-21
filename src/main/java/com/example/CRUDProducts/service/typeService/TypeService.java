package com.example.CRUDProducts.service.typeService;

import com.example.CRUDProducts.dto.TypeDTO;
import com.example.CRUDProducts.entity.Type;
import com.example.CRUDProducts.response.TypeResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface TypeService {
    List<TypeResponse> getAllTypes();
    TypeResponse getType(Long typeId);
    TypeResponse createType(@Valid TypeDTO typeDTO);
    TypeResponse updateType(Long id,@Valid TypeDTO typeDTO);
    void deleteType(Long id);
}
