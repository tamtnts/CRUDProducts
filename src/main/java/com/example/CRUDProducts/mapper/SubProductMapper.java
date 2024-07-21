package com.example.CRUDProducts.mapper;

import com.example.CRUDProducts.dto.SubProductDTO;
import com.example.CRUDProducts.entity.SubProduct;
import com.example.CRUDProducts.response.SubProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SubProductMapper {
    SubProductMapper INSTANCE = Mappers.getMapper(SubProductMapper.class);

    SubProductDTO toDTO(SubProduct subProduct);

    SubProduct toEntity(SubProductDTO subProductDTO);

    SubProductResponse toResponse(SubProductDTO subProductDTO);
}
