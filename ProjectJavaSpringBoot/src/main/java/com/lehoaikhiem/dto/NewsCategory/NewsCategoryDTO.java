package com.lehoaikhiem.dto.NewsCategory;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewsCategoryDTO {

    private Long id;

    @NotBlank(message = "Tên danh mục không được để trống")
    @Size(max = 500, message = "Tên danh mục tối đa 500 ký tự")
    private String name;

    @Size(max = 1000, message = "Ghi chú tối đa 1000 ký tự")
    private String notes;

    @Size(max = 250, message = "Icon tối đa 250 ký tự")
    private String icon;

    @Size(max = 160, message = "Slug tối đa 160 ký tự")
    private String slug;

    @Size(max = 100, message = "Meta title tối đa 100 ký tự")
    private String metaTitle;

    @Size(max = 500, message = "Meta keyword tối đa 500 ký tự")
    private String metaKeyword;

    @Size(max = 500, message = "Meta description tối đa 500 ký tự")
    private String metaDescription;

    private Boolean isDelete = false;

    private Boolean isActive = true;

    private Long parentCategoryId;

    private Long createdBy;

    private Long updatedBy;
}
