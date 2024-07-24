package com.example.CRUDProducts.controller.response;

import java.util.List;

public class ProductResponse {
    private Long idRes;
    private String nameRes;
    private String typeRes;
    private List<SubProductResponse> subProductsRes;

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

    public String getTypeRes() {
        return typeRes;
    }

    public void setTypeRes(String typeRes) {
        this.typeRes = typeRes;
    }

    public List<SubProductResponse> getSubProductsRes() {
        return subProductsRes;
    }

    public void setSubProductsRes(List<SubProductResponse> subProductsRes) {
        this.subProductsRes = subProductsRes;
    }
}
