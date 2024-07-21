package com.example.CRUDProducts.service.productService;

import com.example.CRUDProducts.dao.product.ProductDAO;
import com.example.CRUDProducts.dto.ProductDTO;
import com.example.CRUDProducts.dto.SubProductDTO;
import com.example.CRUDProducts.entity.Product;
import com.example.CRUDProducts.entity.Type;
import com.example.CRUDProducts.mapper.ProductMapper;
import com.example.CRUDProducts.mapper.SubProductMapper;
import com.example.CRUDProducts.repository.ProductRepository;
import com.example.CRUDProducts.repository.TypeRepository;
import com.example.CRUDProducts.response.ProductResponse;
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
    private ProductDAO productDAO;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private SubProductMapper subProductMapper;

    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> list = productDAO.getAllProducts();
        List<ProductDTO> listDTO = list.stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
        return listDTO.stream()
                .map(productMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse getProductById(Long id) {
        Product product = productDAO.getProductById(id);
        if (product == null) {
            throw new RuntimeException("Product not found");
        }
        ProductDTO productDTO = productMapper.toDTO(product);
        return productMapper.toResponse(productDTO);
    }

    @Override
    public ProductResponse createProduct(@Valid ProductDTO productDTO) {
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
        return productMapper.toResponse(savedProductDTO);
    }

    @Override
    public ProductResponse updateProduct(Long id,@Valid ProductDTO productDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
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
        return productMapper.toResponse(updatedProductDTO);
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found with id " + id);
        }
        productRepository.deleteById(id);
    }
}
