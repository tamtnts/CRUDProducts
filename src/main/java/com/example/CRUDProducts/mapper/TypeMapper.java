package com.example.CRUDProducts.mapper;

import com.example.CRUDProducts.dto.ProductDTO;
import com.example.CRUDProducts.dto.TypeDTO;
import com.example.CRUDProducts.entity.Type;
import com.example.CRUDProducts.response.TypeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TypeMapper {
    TypeMapper INSTANCE = Mappers.getMapper(TypeMapper.class);

    @Mapping(target = "products", ignore = true)
    TypeDTO toDTO(Type type);

    @Mapping(target = "products", source = "products")
    TypeResponse toResponse(TypeDTO typeDTO, List<ProductDTO> products);
}
