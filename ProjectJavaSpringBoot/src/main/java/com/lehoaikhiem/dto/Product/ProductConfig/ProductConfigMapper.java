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
    ProductConfigDTO toDto(ProductConfig productConfig);
    List<ProductConfigDTO> toDtoList(List<ProductConfig> productConfigList);
    ProductConfig toEntity(ProductConfigDTO productConfigDTO);
}
