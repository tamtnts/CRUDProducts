package com.example.CRUDProducts.mapper;

import com.example.CRUDProducts.dto.ProductDTO;
import com.example.CRUDProducts.entity.Product;
import com.example.CRUDProducts.entity.Type;
import com.example.CRUDProducts.controller.response.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;



@Mapper(componentModel = "spring", uses = {SubProductMapper.class})
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "idDTO", source = "id")
    @Mapping(target = "nameDTO", source = "name")
    @Mapping(target = "typeDTO", source = "type.name")
    @Mapping(target = "listSubProductsDTO", source = "subProducts")
    ProductDTO toDTO(Product product);

    @Mapping(target = "id", source = "idDTO")
    @Mapping(target = "name", source = "nameDTO")
    @Mapping(target = "type", source = "typeDTO", qualifiedByName = "mapStringToType")
    @Mapping(target = "subProducts", source = "listSubProductsDTO")
    Product toEntity(ProductDTO productDTO);

    @Mapping(target = "idRes", source = "idDTO")
    @Mapping(target = "nameRes", source = "nameDTO")
    @Mapping(target = "typeRes", source = "typeDTO")
    @Mapping(target = "subProductsRes", source = "listSubProductsDTO")
    ProductResponse toResponse(ProductDTO productDTO);

    @Named("mapStringToType")
    default Type mapStringToType(String typeName) {
        Type type = new Type();
        type.setName(typeName);
        return type;
    }
}