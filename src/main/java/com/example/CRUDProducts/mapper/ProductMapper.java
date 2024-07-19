package com.example.CRUDProducts.mapper;

import com.example.CRUDProducts.dto.ProductDTO;
import com.example.CRUDProducts.entity.Product;
import com.example.CRUDProducts.entity.Type;
import com.example.CRUDProducts.response.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "type", source = "type.name")
    ProductDTO toDTO(Product product);

    @Mapping(target = "type.name", source = "type")
    Product toEntity(ProductDTO productDTO);

    @Mapping(target = "type", source = "type")
    ProductResponse toResponse(ProductDTO productDTO);
}
