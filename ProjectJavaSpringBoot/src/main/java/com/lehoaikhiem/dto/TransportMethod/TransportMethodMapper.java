package com.lehoaikhiem.dto.TransportMethod;

import com.lehoaikhiem.entity.TransportMethod;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TransportMethodMapper {

    TransportMethodMapper INSTANCE = Mappers.getMapper(TransportMethodMapper.class);

    TransportMethodDTO toDto(TransportMethod entity);

    TransportMethod toEntity(TransportMethodDTO dto);
}
