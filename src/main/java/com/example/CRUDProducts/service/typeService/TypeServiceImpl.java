package com.example.CRUDProducts.service.typeService;

import com.example.CRUDProducts.dto.TypeDTO;
import com.example.CRUDProducts.mapper.ProductMapper;
import com.example.CRUDProducts.mapper.TypeMapper;
import com.example.CRUDProducts.dto.ProductDTO;
import com.example.CRUDProducts.entity.Type;
import com.example.CRUDProducts.repository.ProductRepository;
import com.example.CRUDProducts.repository.TypeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TypeMapper typeMapper;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<TypeDTO> getAllTypes() {
        List<Type> types = typeRepository.findAllTypes();
        types.forEach(type -> {
            type.getProducts().forEach(product -> product.getSubProducts().size());
        });
        return types.stream()
                .map(typeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TypeDTO getTypeById(Long id) {
        Type type = typeRepository.getTypeById(id);
        if (type != null) {
            type.getProducts().forEach(product -> product.getSubProducts().size());
            return typeMapper.toDTO(type);
        } else {
            throw new RuntimeException("Type not found");
        }
    }


    @Override
    public TypeDTO createType(@Valid TypeDTO typeDTO) {
        if (typeRepository.existsByName(typeDTO.getNameDTO())) {
            throw new RuntimeException("Type with name " + typeDTO.getNameDTO() + " already exists");
        }
        Type type = typeMapper.toEntity(typeDTO);
        Type savedType = typeRepository.save(type);
        TypeDTO savedTypeDTO = typeMapper.toDTO(savedType);
        List<ProductDTO> productDTOs = savedType.getProducts()
                .stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
        savedTypeDTO.setProductsDTO(productDTOs);
        return savedTypeDTO;
    }

    @Override
    public TypeDTO updateType(Long id, @Valid TypeDTO typeDTO) {
        if (!typeRepository.existsById(id)) {
            throw new RuntimeException("Type not found with id " + id);
        }

        Type existingType = typeRepository.findByName(typeDTO.getNameDTO())
                .orElseThrow(() -> new RuntimeException("Type not found"));
        if (existingType != null && !existingType.getId().equals(id)) {
            throw new RuntimeException("Type with name " + typeDTO.getNameDTO() + " already exists");
        }

        typeDTO.setIdDTO(id);
        Type type = typeMapper.toEntity(typeDTO);
        Type updatedType = typeRepository.save(type);
        TypeDTO updatedTypeDTO = typeMapper.toDTO(updatedType);
        List<ProductDTO> productDTOs = updatedType.getProducts()
                .stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
        updatedTypeDTO.setProductsDTO(productDTOs);
        return updatedTypeDTO;
    }

    @Override
    public void deleteType(Long id) {
        if (!typeRepository.existsById(id)) {
            throw new RuntimeException("Type not found with id " + id);
        }
        typeRepository.deleteById(id);
    }
}