package com.example.CRUDProducts.controller;

import com.example.CRUDProducts.entity.Type;
import com.example.CRUDProducts.response.TypeResponse;
import com.example.CRUDProducts.service.typeService.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/types")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping
    public List<TypeResponse> getAllTypes() {
        return typeService.getAllTypes();
    }

    @GetMapping("/{id}")
    public TypeResponse getTypeWithProducts(@PathVariable Long id) {
        return typeService.getType(id);
    }

    @PostMapping
    public ResponseEntity<TypeResponse> createType(@RequestBody Type type) {
        TypeResponse typeResponse = typeService.createType(type);
        return ResponseEntity.status(HttpStatus.CREATED).body(typeResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TypeResponse> updateType(@PathVariable Long id, @RequestBody Type type) {
        TypeResponse typeResponse = typeService.updateType(id, type);
        return ResponseEntity.ok(typeResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteType(@PathVariable Long id) {
        typeService.deleteType(id);
        return ResponseEntity.noContent().build();
    }
}
