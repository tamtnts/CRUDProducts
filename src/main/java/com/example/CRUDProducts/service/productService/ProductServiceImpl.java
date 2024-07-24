package com.example.CRUDProducts.service.productService;

import com.example.CRUDProducts.dto.ProductDTO;
import com.example.CRUDProducts.dto.SubProductDTO;
import com.example.CRUDProducts.entity.Product;
import com.example.CRUDProducts.entity.Type;
import com.example.CRUDProducts.mapper.ProductMapper;
import com.example.CRUDProducts.mapper.SubProductMapper;
import com.example.CRUDProducts.repository.ProductRepository;
import com.example.CRUDProducts.repository.TypeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private SubProductMapper subProductMapper;

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> list = productRepository.getAllProducts();
        return list.stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(Long id) {
        Product product = productRepository.getProductById(id);
        if (product == null) {
            throw new RuntimeException("Product not found");
        }
        return productMapper.toDTO(product);
    }

    @Override
    public ProductDTO createProduct(@Valid ProductDTO productDTO) {
        if (productRepository.findByName(productDTO.getNameDTO()).isPresent()) {
            throw new RuntimeException("Product with name " + productDTO.getNameDTO() + " already exists");
        }
        Product product = productMapper.toEntity(productDTO);
        Type type = typeRepository.findByName(productDTO.getTypeDTO())
                .orElseThrow(() -> new RuntimeException("Type not found"));
        product.setType(type);
        Product savedProduct = productRepository.save(product);
        ProductDTO savedProductDTO = productMapper.toDTO(savedProduct);

        List<SubProductDTO> subProductDTOs = savedProduct.getSubProducts()
                .stream()
                .map(subProductMapper::toDTO)
                .collect(Collectors.toList());
        savedProductDTO.setListSubProductsDTO(subProductDTOs);

        return savedProductDTO;
    }

    @Override
    public ProductDTO updateProduct(Long id, @Valid ProductDTO productDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (productRepository.existsByNameAndIdNot(productDTO.getNameDTO(), id)) {
            throw new RuntimeException("Product with name " + productDTO.getNameDTO() + " already exists");
        }
        product.setName(productDTO.getNameDTO());
        Type type = typeRepository.findByName(productDTO.getTypeDTO())
                .orElseThrow(() -> new RuntimeException("Type not found"));
        product.setType(type);
        Product updatedProduct = productRepository.save(product);
        ProductDTO updatedProductDTO = productMapper.toDTO(updatedProduct);

        List<SubProductDTO> subProductDTOs = updatedProduct.getSubProducts()
                .stream()
                .map(subProductMapper::toDTO)
                .collect(Collectors.toList());
        updatedProductDTO.setListSubProductsDTO(subProductDTOs);

        return updatedProductDTO;
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found with id " + id);
        }
        productRepository.deleteById(id);
    }
}
