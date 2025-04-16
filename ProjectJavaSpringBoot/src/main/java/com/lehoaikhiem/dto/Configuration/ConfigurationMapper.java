package com.lehoaikhiem.dto.Configuration;

import com.lehoaikhiem.entity.Configuration;

import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ConfigurationMapper {
    ConfigurationDTO toDto(Configuration configuration);
    Configuration toEntity(ConfigurationDTO configurationDTO);
    List<ConfigurationDTO> toDtoList(List<Configuration> entities);
}
