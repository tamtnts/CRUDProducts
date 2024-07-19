package com.example.CRUDProducts.service.typeService;

import com.example.CRUDProducts.dao.ProductDAO;
import com.example.CRUDProducts.dto.TypeDTO;
import com.example.CRUDProducts.exception.ResourceNotFoundException;
import com.example.CRUDProducts.mapper.ProductMapper;
import com.example.CRUDProducts.mapper.TypeMapper;
import com.example.CRUDProducts.dto.ProductDTO;
import com.example.CRUDProducts.entity.Type;
import com.example.CRUDProducts.repository.ProductRepository;
import com.example.CRUDProducts.repository.TypeRepository;
import com.example.CRUDProducts.response.TypeResponse;
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
    private ProductDAO productDAO;

    @Autowired
    private TypeMapper typeMapper;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<TypeResponse> getAllTypes() {
        List<Type> types = typeRepository.findAll();
        return types.stream()
                .map(type -> {
                    TypeDTO typeDTO = typeMapper.toDTO(type);
                    TypeResponse typeResponse = typeMapper.toResponse(
                            typeDTO,
                            productDAO.findByTypeId(type.getId()).stream()
                                    .map(productMapper::toDTO)
                                    .collect(Collectors.toList())
                    );
                    return typeResponse;
                })
                .collect(Collectors.toList());
    }

    @Override
    public TypeResponse getType(Long typeId) {
        Type type = typeRepository.findById(typeId)
                .orElseThrow(() -> new RuntimeException("Type not found"));
        TypeDTO typeDTO = typeMapper.toDTO(type);

        List<ProductDTO> products = productRepository.findByTypeId(typeId)
                .stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());

        return typeMapper.toResponse(typeDTO, products);
    }

    @Override
    public TypeResponse createType(Type type) {
        Type createdType = typeRepository.save(type);
        TypeDTO typeDTO = typeMapper.toDTO(createdType);

        List<ProductDTO> products = productRepository.findByTypeId(createdType.getId())
                .stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());

        return typeMapper.toResponse(typeDTO, products);
    }

    @Override
    public TypeResponse updateType(Long id, Type type) {
        if (!typeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Type not found with id " + id);
        }
        type.setId(id);
        Type updatedType = typeRepository.save(type);
        TypeDTO typeDTO = typeMapper.toDTO(updatedType);

        List<ProductDTO> products = productRepository.findByTypeId(updatedType.getId())
                .stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());

        return typeMapper.toResponse(typeDTO, products);
    }

    @Override
    public void deleteType(Long id) {
        if (!typeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Type not found with id " + id);
        }
        typeRepository.deleteById(id);
    }
}
