package com.lehoaikhiem.service;

import com.lehoaikhiem.dto.Product.ProductImage.ProductImageDTO;
import com.lehoaikhiem.dto.Product.ProductImage.ProductImageMapper;
import com.lehoaikhiem.entity.ProductConfig;
import com.lehoaikhiem.entity.ProductImage;
import com.lehoaikhiem.repository.ProductImageRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImageService {
    @Autowired
    private ProductImageRepository productImageRepository;

    @Autowired
    private ProductImageMapper productImageMapper;

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

}
