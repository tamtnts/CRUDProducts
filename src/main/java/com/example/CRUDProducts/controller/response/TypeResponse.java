package com.example.CRUDProducts.controller.response;

import java.util.List;

public class TypeResponse {
    private Long idRes;
    private String nameRes;
    private List<ProductResponse> productsRes;

    public Long getIdRes() {
        return idRes;
    }

    public void setIdRes(Long idRes) {
        this.idRes = idRes;
    }

    public String getNameRes() {
        return nameRes;
    }

    public void setNameRes(String nameRes) {
        this.nameRes = nameRes;
    }

    public List<ProductResponse> getProductsRes() {
        return productsRes;
    }

    public void setProductsRes(List<ProductResponse> productsRes) {
        this.productsRes = productsRes;
    }
}
