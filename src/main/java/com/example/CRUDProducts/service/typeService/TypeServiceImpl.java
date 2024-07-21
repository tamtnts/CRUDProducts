package com.example.CRUDProducts.service.typeService;

import com.example.CRUDProducts.dao.type.TypeDAO;
import com.example.CRUDProducts.dto.TypeDTO;
import com.example.CRUDProducts.mapper.ProductMapper;
import com.example.CRUDProducts.mapper.TypeMapper;
import com.example.CRUDProducts.dto.ProductDTO;
import com.example.CRUDProducts.entity.Type;
import com.example.CRUDProducts.repository.TypeRepository;
import com.example.CRUDProducts.response.TypeResponse;
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
    private TypeDAO typeDAO;


    @Autowired
    private TypeMapper typeMapper;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<TypeResponse> getAllTypes() {
        return typeDAO.getAllTypes();
    }

    @Override
    public TypeResponse getType(Long id) {
        Type type = typeDAO.getTypeById(id).orElseThrow(() -> new RuntimeException("Type not found"));
        TypeDTO typeDTO = typeMapper.toDTO(type);
        return typeMapper.toResponse(typeDTO);
    }

    @Override
    public TypeResponse createType(@Valid TypeDTO typeDTO) {
        Type type = typeMapper.toEntity(typeDTO);
        Type savedType = typeRepository.save(type);
        TypeDTO savedTypeDTO = typeMapper.toDTO(savedType);
        List<ProductDTO> productDTOs = savedType.getProducts()
                .stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
        savedTypeDTO.setProductsDTO(productDTOs);
        return typeMapper.toResponse(savedTypeDTO);
    }

    @Override
    public TypeResponse updateType(Long id,@Valid TypeDTO typeDTO) {
        if (!typeRepository.existsById(id)) {
            throw new RuntimeException("Type not found with id " + id);
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
        return typeMapper.toResponse(updatedTypeDTO);
    }

    @Override
    public void deleteType(Long id) {
        if (!typeRepository.existsById(id)) {
            throw new RuntimeException("Type not found with id " + id);
        }
        typeRepository.deleteById(id);
    }
}