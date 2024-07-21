package com.example.CRUDProducts.dto;

import com.example.CRUDProducts.entity.Type;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;


public class ProductDTO {
    private Long idDTO;
    @NotNull(message = "Product name cannot be null")
    @Size(min = 1, max = 100, message = "Product name must be between 1 and 100 characters")
    private String nameDTO;
    @NotNull(message = "Product type cannot be null")
    private String typeDTO;
    private List<SubProductDTO> listSubProductsDTO;

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

    public String getTypeDTO() {
        return typeDTO;
    }

    public void setTypeDTO(String typeDTO) {
        this.typeDTO = typeDTO;
    }

    public List<SubProductDTO> getListSubProductsDTO() {
        return listSubProductsDTO;
    }

    public void setListSubProductsDTO(List<SubProductDTO> listSubProductsDTO) {
        this.listSubProductsDTO = listSubProductsDTO;
    }
}
