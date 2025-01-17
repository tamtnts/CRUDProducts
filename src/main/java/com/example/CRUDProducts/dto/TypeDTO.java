package com.example.CRUDProducts.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public class TypeDTO {
    private Long idDTO;
    @NotNull(message = "Type name cannot be null")
    @Size(min = 1, max = 100, message = "Type name must be between 1 and 100 characters")
    private String nameDTO;
    private List<ProductDTO> productsDTO;

    public Long getIdDTO() {
        return idDTO;
    }

    public void setIdDTO(Long idDTO) {
        this.idDTO = idDTO;
    }

    public String getNameDTO() {
        return nameDTO;
    }

    public void setNameDTO(String nameDTO) {
        this.nameDTO = nameDTO;
    }

    public List<ProductDTO> getProductsDTO() {
        return productsDTO;
    }

    public void setProductsDTO(List<ProductDTO> productsDTO) {
        this.productsDTO = productsDTO;
    }
}
