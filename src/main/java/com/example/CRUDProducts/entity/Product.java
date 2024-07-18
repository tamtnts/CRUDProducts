package com.example.CRUDProducts.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Product {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
}
