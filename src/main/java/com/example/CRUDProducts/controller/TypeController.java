package com.example.CRUDProducts.controller;

import com.example.CRUDProducts.dto.TypeDTO;
import com.example.CRUDProducts.mapper.TypeMapper;
import com.example.CRUDProducts.controller.response.TypeResponse;
import com.example.CRUDProducts.service.typeService.TypeService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    private static final Logger logger = LoggerFactory.getLogger(TypeController.class);

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
        return typeMapper.toResponse(typeService.createType(typeDTO));
    }

    @PutMapping("/update/{id}")
    public TypeResponse updateType(@PathVariable Long id, @RequestBody @Valid TypeDTO typeDTO) {
        return typeMapper.toResponse(typeService.updateType(id, typeDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteType(@PathVariable Long id) {
        typeService.deleteType(id);
        return ResponseEntity.noContent().build();
    }
}
