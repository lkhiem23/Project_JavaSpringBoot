package com.lehoaikhiem.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class NewsCategoryDTO {
    private Long id;

    @NotBlank(message = "Name is not blank!")
    private String name;
    private String notes;

    //admin
    private Boolean isDelete;
    private Boolean isActive;
}
