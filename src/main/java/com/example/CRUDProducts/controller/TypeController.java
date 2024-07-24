package com.example.CRUDProducts.controller;

import com.example.CRUDProducts.dto.TypeDTO;
import com.example.CRUDProducts.mapper.TypeMapper;
import com.example.CRUDProducts.controller.response.TypeResponse;
import com.example.CRUDProducts.service.typeService.TypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/types")
public class TypeController {
    @Autowired
    private TypeService typeService;

    @Autowired
    private TypeMapper typeMapper;

    @GetMapping("/all")
    public List<TypeResponse> getAllTypes() {
        return typeService.getAllTypes()
                .stream()
                .map(typeMapper::toResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public TypeResponse getTypeById(@PathVariable Long id) {
        return typeMapper.toResponse(typeService.getTypeById(id));
    }

    @PostMapping("/create")
    public TypeResponse createType(@RequestBody @Valid TypeDTO typeDTO) {
        TypeDTO createdTypeDTO = typeService.createType(typeDTO);
        return typeMapper.toResponse(createdTypeDTO);
    }

    @PutMapping("/update/{id}")
    public TypeResponse updateType(@PathVariable Long id, @RequestBody @Valid TypeDTO typeDTO) {
        TypeDTO updatedTypeDTO = typeService.updateType(id, typeDTO);
        return typeMapper.toResponse(updatedTypeDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteType(@PathVariable Long id) {
        typeService.deleteType(id);
    }
}
