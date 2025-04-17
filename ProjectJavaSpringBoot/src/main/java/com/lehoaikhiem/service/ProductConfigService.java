package com.lehoaikhiem.service;

import com.lehoaikhiem.dto.Product.ProductConfig.ProductConfigDTO;
import com.lehoaikhiem.dto.Product.ProductConfig.ProductConfigMapper;
import com.lehoaikhiem.entity.ProductConfig;
import com.lehoaikhiem.repository.ProductConfigRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductConfigService {
    @Autowired
    private ProductConfigRepository productConfigRepository;

    @Autowired
    private ProductConfigMapper productConfigMapper;

    public List<ProductConfigDTO> findAll() {
        return productConfigMapper.toDtoList(productConfigRepository.findAll());
    }

    public ProductConfigDTO findByConfigId(Long configId) {
        ProductConfig entity = productConfigRepository.findById(configId)
                .orElseThrow(() -> new EntityNotFoundException("Configuration not found with id " + configId));
        return productConfigMapper.toDto(entity);
    }

    public ProductConfigDTO save(ProductConfigDTO productConfigDTO) {
        ProductConfig entity = productConfigMapper.toEntity(productConfigDTO);
        return productConfigMapper.toDto(productConfigRepository.save(entity));
    }

    public void deleteByConfigId(Long configId) {
        List<ProductConfig> list = productConfigRepository.findByConfigId(configId); // Lấy danh sách bản ghi theo configId
        if (!list.isEmpty()) {
            for (ProductConfig productConfig : list) {
                productConfigRepository.deleteById(productConfig.getId()); // Xóa từng bản ghi theo ID
            }
        }
    }

}
