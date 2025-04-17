package com.lehoaikhiem.dto.Product.ProductImage;

import com.lehoaikhiem.entity.ProductImage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductImageMapper {
    @Mappings({
            @Mapping(source = "product.id", target = "productId") // Map nested product to productId
    })
    ProductImageDTO toDto(ProductImage entity);

    @Mappings({
            @Mapping(source = "productId", target = "product.id") // Map productId to product object
    })
    ProductImage toEntity(ProductImageDTO dto);

    List<ProductImageDTO> toDtoList(List<ProductImage> list);
}
