package com.lehoaikhiem.dto.Product;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ProductDTO {
    private Long id;

    @NotBlank(message = "Tên sản phẩm không được để trống")
    private String name;

    private String description;
    private String notes;
    private String image;

    @NotNull(message = "ID danh mục không được để trống")
    private Long idCategory;

    private String contents;
    private BigDecimal price;
    private Integer quantity;
    private String slug;
    private String metaTitle;
    private String metaKeyword;
    private String metaDescription;
    private Date createdDate;
    private Date updatedDate;
    private Long createdBy;
    private Long updatedBy;

    // Admin có thể thay đổi isDelete và isActive
    private Boolean isDelete;
    private Boolean isActive;
}
