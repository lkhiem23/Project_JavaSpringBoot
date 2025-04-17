package com.lehoaikhiem.dto.Product.ProductImage;

import com.lehoaikhiem.entity.ProductImage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductImageMapper {

    // Chuyển đổi từ ProductImage entity sang ProductImageDTO
    ProductImageDTO toDto(ProductImage productImage);

    // Chuyển đổi danh sách ProductImage entity sang danh sách ProductImageDTO
    List<ProductImageDTO> toDtoList(List<ProductImage> productImages);
}

