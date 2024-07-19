package com.example.CRUDProducts.service.productService;

import com.example.CRUDProducts.dto.ProductDTO;
import com.example.CRUDProducts.dto.TypeDTO;
import com.example.CRUDProducts.entity.Product;
import com.example.CRUDProducts.entity.Type;
import com.example.CRUDProducts.mapper.ProductMapper;
import com.example.CRUDProducts.repository.ProductRepository;
import com.example.CRUDProducts.repository.TypeRepository;
import com.example.CRUDProducts.response.ProductResponse;
import com.example.CRUDProducts.response.TypeResponse;
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

    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> list = productRepository.findAll();
        return list.stream()
                .map(product -> {
                    ProductDTO productDTO = productMapper.toDTO(product);
                    ProductResponse productResponse = productMapper.toResponse(productDTO);
                    return productResponse;
                })
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse getProductById(Long id) {
        ProductDTO productDTO = productRepository.findById(id)
                .map(productMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return  productMapper.toResponse(productDTO);
    }

    @Override
    public ProductResponse createProduct(ProductDTO productDTO) {
        Product product = productMapper.toEntity(productDTO);
        Type type = typeRepository.findByName(productDTO.getType())
                .orElseThrow(() -> new RuntimeException("Type not found"));
        product.setType(type);
        Product savedProduct = productRepository.save(product);
        ProductDTO saveProductDTO = productMapper.toDTO(savedProduct);
        return productMapper.toResponse(saveProductDTO);
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setName(productDTO.getName());
        Type type = typeRepository.findByName(productDTO.getType())
                .orElseThrow(() -> new RuntimeException("Type not found"));
        product.setType(type);
        Product updatedProduct = productRepository.save(product);
        ProductDTO updatedProductDTO = productMapper.toDTO(updatedProduct);
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
