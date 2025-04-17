package com.lehoaikhiem.dto.News;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class NewsDTO {
    private Long id;

    @NotBlank(message = "Tên tin tức không được để trống")
    @Size(max = 500, message = "Tên tin tức không vượt quá 500 ký tự")
    private String name;

    @NotBlank(message = "Mô tả không được để trống")
    private String description;

    @Size(max = 550, message = "Đường dẫn ảnh không vượt quá 550 ký tự")
    private String image;

    @NotNull(message = "Danh mục tin tức không được để trống")
    private Long newsCategoryId;

    @NotBlank(message = "Nội dung tin tức không được để trống")
    private String contents;

    @Size(max = 160, message = "Slug không vượt quá 160 ký tự")
    private String slug;

    @Size(max = 100, message = "Meta title không vượt quá 100 ký tự")
    private String metaTitle;

    @Size(max = 500, message = "Meta keyword không vượt quá 500 ký tự")
    private String metaKeyword;

    @Size(max = 500, message = "Meta description không vượt quá 500 ký tự")
    private String metaDescription;

    private Date createdDate;

    private Date updatedDate;

    private Long createdBy;

    private Long updatedBy;

    private Boolean isDelete;

    private Boolean isActive;
}
