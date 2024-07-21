package com.example.CRUDProducts.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class SubProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
