package com.lehoaikhiem.service;

import com.lehoaikhiem.dto.Category.CategoryDTO;
import com.lehoaikhiem.dto.Category.CategoryMapper;
import com.lehoaikhiem.entity.Category;
import com.lehoaikhiem.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    public List<CategoryDTO> findAll() {
        return categoryMapper.toDtoList(categoryRepository.findAll());
    }

    public CategoryDTO findById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.map(categoryMapper::toDto).orElse(null);
    }

    public CategoryDTO save(CategoryDTO dto) {
        Category entity = categoryMapper.toEntity(dto);
        return categoryMapper.toDto(categoryRepository.save(entity));
    }

    public CategoryDTO update(Long id, CategoryDTO dto) {
        Optional<Category> optional = categoryRepository.findById(id);
        if (optional.isPresent()) {
            Category entity = categoryMapper.toEntity(dto);
            entity.setId(id);
            return categoryMapper.toDto(categoryRepository.save(entity));
        }
        return null;
    }

    public void deleteById(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new EntityNotFoundException("Product method not found with ID: " + id);
        }
        categoryRepository.deleteById(id);
    }

    public List<CategoryDTO> findByName(String name) {
        return categoryMapper.toDtoList(categoryRepository.findByNameContainingIgnoreCase(name));
    }

}
