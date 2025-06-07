package com.lehoaikhiem.service;

import com.lehoaikhiem.dto.Category.CategoryDTO;
import com.lehoaikhiem.dto.Product.ProductDTO;
import com.lehoaikhiem.dto.Product.ProductMapper;
import com.lehoaikhiem.entity.Category;
import com.lehoaikhiem.entity.Product;
import com.lehoaikhiem.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    public List<ProductDTO> findAllWithoutPaging() {
        return productMapper.toDtoList(productRepository.findAll());
    }

    public ProductDTO findById(Long id) {
        Product entity = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product method not found with ID: " + id));
        return productMapper.toDto(entity);
    }

    //Phan trang
    public Page<ProductDTO> findAll(Pageable pageable) {
        return productRepository.findAll(pageable).map(productMapper::toDto);
    }

    public ProductDTO save(ProductDTO productDTO) {
        Product product = productMapper.toEntity(productDTO);
        return productMapper.toDto(productRepository.save(product));
    }

    public ProductDTO update(Long id, ProductDTO dto) {
        Optional<Product> optional = productRepository.findById(id);
        if (optional.isPresent()) {
            Product entity = productMapper.toEntity(dto);
            entity.setId(id);
            return productMapper.toDto(productRepository.save(entity));
        }
        return null;
    }

    public void deleteById(Long id) {
        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundException("Product method not found with ID: " + id);
        }
        productRepository.deleteById(id);
    }
    public long countAllProducts() {
        return productRepository.count();
    }


}
