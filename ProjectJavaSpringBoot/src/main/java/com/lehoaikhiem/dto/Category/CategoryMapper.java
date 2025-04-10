package com.lehoaikhiem.dto.Category;

import com.lehoaikhiem.entity.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDTO toDto(Category category);
    Category toEntity(CategoryDTO dto);
    List<CategoryDTO> toDtoList(List<Category> list);
}
