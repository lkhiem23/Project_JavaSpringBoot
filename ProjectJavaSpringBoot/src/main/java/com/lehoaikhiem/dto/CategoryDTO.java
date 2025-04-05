package com.lehoaikhiem.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

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

