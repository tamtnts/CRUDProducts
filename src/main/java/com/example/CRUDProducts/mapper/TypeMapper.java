package com.example.CRUDProducts.mapper;

import com.example.CRUDProducts.dto.TypeDTO;
import com.example.CRUDProducts.entity.Type;
import com.example.CRUDProducts.controller.response.TypeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface TypeMapper {
    TypeMapper INSTANCE = Mappers.getMapper(TypeMapper.class);

    @Mapping(target = "idDTO", source = "id")
    @Mapping(target = "nameDTO", source = "name")
    @Mapping(target = "productsDTO", source = "products")
    TypeDTO toDTO(Type type);

    @Mapping(target = "id", source = "idDTO")
    @Mapping(target = "name", source = "nameDTO")
    @Mapping(target = "products", source = "productsDTO")
    Type toEntity(TypeDTO typeDTO);

    @Mapping(target = "idRes", source = "idDTO")
    @Mapping(target = "nameRes", source = "nameDTO")
    @Mapping(target = "productsRes", source = "productsDTO")
    TypeResponse toResponse(TypeDTO typeDTO);
}
