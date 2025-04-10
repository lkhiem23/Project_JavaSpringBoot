package com.lehoaikhiem.service;

import com.lehoaikhiem.dto.NewsCategoryDTO;
import com.lehoaikhiem.dto.NewsCategoryMapper;
import com.lehoaikhiem.entity.NewsCategory;
import com.lehoaikhiem.repository.NewsCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewsCategoryService {
    @Autowired
    NewsCategoryRepository newCategoryRepository;

    @Autowired
    NewsCategoryMapper newCategoryMapper;

    public List<NewsCategoryDTO> findAll() {
        return newCategoryMapper.toDtoList(newCategoryRepository.findAll());
    }

    public NewsCategoryDTO findById(Long id) {
        Optional<NewsCategory> result = newCategoryRepository.findById(id);
        return result.map(newCategoryMapper::toNewsCategoryDTO).orElse(null);
    }

    public NewsCategoryDTO save(NewsCategoryDTO dto) {
        NewsCategory entity = newCategoryMapper.toNewsCategory(dto);
        return newCategoryMapper.toNewsCategoryDTO(newCategoryRepository.save(entity));
    }

    public void delete(Long id) {
        newCategoryRepository.deleteById(id);
    }

    public List<NewsCategoryDTO> findByName(String name) {
        return newCategoryMapper.toDtoList(newCategoryRepository.findByNameContainingIgnoreCase(name));
    }

}



