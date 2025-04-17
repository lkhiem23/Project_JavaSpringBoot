package com.lehoaikhiem.service;

import com.lehoaikhiem.dto.Product.ProductImage.ProductImageDTO;
import com.lehoaikhiem.dto.Product.ProductImage.ProductImageMapper;
import com.lehoaikhiem.dto.Product.ProductMapper;
import com.lehoaikhiem.entity.Product;
import com.lehoaikhiem.entity.ProductConfig;
import com.lehoaikhiem.entity.ProductImage;
import com.lehoaikhiem.repository.ProductImageRepository;
import com.lehoaikhiem.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//findAll; findById; save; update; delete; findByName

@Service
public class ProductImageService {
    @Autowired
    private ProductImageRepository productImageRepository;

    @Autowired
    private ProductImageMapper productImageMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductRepository productRepository;

    public List<ProductImageDTO> findAll(){
        return productImageMapper.toDtoList(productImageRepository.findAll());
    }

    public List<ProductImageDTO> findByProductId(Long productId) {
        List<ProductImage> entities = productImageRepository.findByProductId(productId);  // Tìm kiếm theo productId
        if (entities.isEmpty()) {
            throw new EntityNotFoundException("No images found for product with id " + productId);
        }
        return productImageMapper.toDtoList(entities);  // Chuyển đổi sang DTO
    }

    public List<ProductImageDTO> findByName(String name) {
        List<ProductImage> entities = productImageRepository.findByName(name);
        if (entities.isEmpty()) {
            throw new EntityNotFoundException("No ProductImage found with name: " + name);
        }
        return productImageMapper.toDtoList(entities);
    }

    public ProductImageDTO save(ProductImageDTO dto) {
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        ProductImage entity;

        if (dto.getId() != null) {
            // Update
            entity = productImageRepository.findById(dto.getId())
                    .orElseThrow(() -> new EntityNotFoundException("ProductImage not found"));

            entity = entity.toBuilder()
                    .name(dto.getName())
                    .urlImg(dto.getUrlImg())
                    .product(product)
                    .build();
        } else {
            // Create
            entity = ProductImage.builder()
                    .name(dto.getName())
                    .urlImg(dto.getUrlImg())
                    .product(product)
                    .build();
        }

        return productImageMapper.toDto(productImageRepository.save(entity));
    }


}
