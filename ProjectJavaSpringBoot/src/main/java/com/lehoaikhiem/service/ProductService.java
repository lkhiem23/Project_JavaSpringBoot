package com.lehoaikhiem.service;

import com.lehoaikhiem.dto.Product.ProductDTO;
import com.lehoaikhiem.dto.Product.ProductMapper;
import com.lehoaikhiem.entity.Product;
import com.lehoaikhiem.entity.TransportMethod;
import com.lehoaikhiem.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    public List<ProductDTO> findAll() {
        return productMapper.toDtoList(productRepository.findAll());
    }

    public ProductDTO findById(Long id) {
        Product entity = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product method not found with ID: " + id));
        return productMapper.toDto(entity);
    }

    public ProductDTO save(ProductDTO productDTO) {
        Product product = productMapper.toEntity(productDTO);
        return productMapper.toDto(productRepository.save(product));
    }

    public void deleteById(Long id) {
        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundException("Product method not found with ID: " + id);
        }
        productRepository.deleteById(id);
    }


}
