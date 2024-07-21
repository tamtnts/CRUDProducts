package com.example.CRUDProducts.controller;

import com.example.CRUDProducts.dto.TypeDTO;
import com.example.CRUDProducts.exception.ResourceNotFoundException;
import com.example.CRUDProducts.response.TypeResponse;
import com.example.CRUDProducts.service.typeService.TypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/types")
public class TypeController {
    @Autowired
    private TypeService typeService;

    @GetMapping
    public List<TypeResponse> getAllTypes()throws ResourceNotFoundException {
        return typeService.getAllTypes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeResponse> getTypeById(@PathVariable Long id)throws ResourceNotFoundException {
        TypeResponse typeResponse = typeService.getType(id);
        return ResponseEntity.ok(typeResponse);
    }

    @PostMapping
    public ResponseEntity<TypeResponse> createType(@Valid @RequestBody TypeDTO typeDTO)throws ResourceNotFoundException {
        TypeResponse typeResponse = typeService.createType(typeDTO);
        return ResponseEntity.ok(typeResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TypeResponse> updateType(@PathVariable Long id, @Valid @RequestBody TypeDTO typeDTO)throws ResourceNotFoundException {
        TypeResponse typeResponse = typeService.updateType(id, typeDTO);
        return ResponseEntity.ok(typeResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteType(@PathVariable Long id)throws ResourceNotFoundException {
        typeService.deleteType(id);
        return ResponseEntity.noContent().build();
    }
}
