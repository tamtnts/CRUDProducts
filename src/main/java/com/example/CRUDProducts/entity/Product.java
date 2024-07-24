package com.example.CRUDProducts.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private Type type;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<SubProduct> subProducts = new ArrayList<>();
}
