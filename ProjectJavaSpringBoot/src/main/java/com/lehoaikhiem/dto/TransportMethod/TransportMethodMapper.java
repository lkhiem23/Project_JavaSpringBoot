package com.lehoaikhiem.dto.TransportMethod;

import com.lehoaikhiem.entity.TransportMethod;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TransportMethodMapper {
    TransportMethodDTO toDto(TransportMethod transportMethod);
    TransportMethod toEntity(TransportMethodDTO transportMethodDTO);
    List<TransportMethodDTO> toDtoList(List<TransportMethod> list);
}
