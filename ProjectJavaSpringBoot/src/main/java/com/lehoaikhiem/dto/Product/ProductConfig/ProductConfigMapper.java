package com.lehoaikhiem.dto.Product.ProductConfig;
import com.lehoaikhiem.entity.Configuration;
import com.lehoaikhiem.entity.Product;
import com.lehoaikhiem.entity.ProductConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductConfigMapper {
    @Mappings({
            @Mapping(source = "product.id", target = "productId"),
            @Mapping(source = "config.id", target = "configId")
    })
    ProductConfigDTO toDto(ProductConfig entity);

    @Mappings({
            @Mapping(source = "productId", target = "product.id"),
            @Mapping(source = "configId", target = "config.id")
    })
    ProductConfig toEntity(ProductConfigDTO dto);

    List<ProductConfigDTO> toDtoList(List<ProductConfig> entityList);
}
