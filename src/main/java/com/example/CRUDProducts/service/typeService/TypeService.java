package com.example.CRUDProducts.service.typeService;

import com.example.CRUDProducts.dto.TypeDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface TypeService {
    List<TypeDTO> getAllTypes();
    TypeDTO getTypeById(Long id);
    TypeDTO createType(@Valid TypeDTO typeDTO);
    TypeDTO updateType(Long id,@Valid TypeDTO typeDTO);
    void deleteType(Long id);
}
