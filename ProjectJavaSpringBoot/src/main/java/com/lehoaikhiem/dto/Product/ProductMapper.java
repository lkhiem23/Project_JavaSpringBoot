package com.lehoaikhiem.dto.Product;

import com.lehoaikhiem.entity.Product;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO toDto(Product product);
    Product toEntity(ProductDTO dto);
    List<ProductDTO> toDtoList(List<Product> list);
}
