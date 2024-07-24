package com.example.CRUDProducts.mapper;

import com.example.CRUDProducts.dto.SubProductDTO;
import com.example.CRUDProducts.entity.SubProduct;
import com.example.CRUDProducts.controller.response.SubProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SubProductMapper {
    SubProductMapper INSTANCE = Mappers.getMapper(SubProductMapper.class);

    @Mapping(target = "idDTO", source = "id")
    @Mapping(target = "nameDTO", source = "name")
    SubProductDTO toDTO(SubProduct subProduct);

    @Mapping(target = "id", source = "idDTO")
    @Mapping(target = "name", source = "nameDTO")
    SubProduct toEntity(SubProductDTO subProductDTO);

    @Mapping(target = "idRes", source = "idDTO")
    @Mapping(target = "nameRes", source = "nameDTO")
    SubProductResponse toResponse(SubProductDTO subProductDTO);
}
