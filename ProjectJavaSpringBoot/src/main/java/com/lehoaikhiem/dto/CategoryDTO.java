package com.lehoaikhiem.dto;

import com.lehoaikhiem.entity.Category;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.mapstruct.Mapper;

import java.util.List;

@Data
public class CategoryDTO {
    private Long id;

    @NotBlank(message = "Tên danh mục không được để trống")
    private String name;
    private String notes;
    // ADMIN
    private Boolean isDelete;
    private Boolean isActive;
}

@Mapper(componentModel = "spring")
 interface CategoryMapper {
    CategoryDTO toDto(Category category);
    Category toEntity(CategoryDTO dto);
    List<CategoryDTO> toDtoList(List<Category> list);
}