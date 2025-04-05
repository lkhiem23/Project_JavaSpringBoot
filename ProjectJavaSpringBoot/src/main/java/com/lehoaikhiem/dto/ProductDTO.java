package com.lehoaikhiem.dto;

import com.lehoaikhiem.entity.Product;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.mapstruct.Mapper;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
    private Integer quatity;
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

@Mapper(componentModel = "spring")
interface ProductMapper {
    ProductDTO toDto(Product product);
    Product toEntity(ProductDTO dto);
    List<ProductDTO> toDtoList(List<Product> list);
}
