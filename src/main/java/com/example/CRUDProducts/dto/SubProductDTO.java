package com.example.CRUDProducts.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SubProductDTO {
    private Long idDTO;

    @NotNull(message = "SubProduct name cannot be null")
    @Size(min = 1, max = 100, message = "SubProduct name must be between 1 and 100 characters")
    private String nameDTO;

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
}
