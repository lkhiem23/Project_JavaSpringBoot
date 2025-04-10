package com.lehoaikhiem.dto.OrdersDetail;

import com.lehoaikhiem.entity.OrdersDetail;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface OrdersDetailMapper {
    OrdersDetailDTO toDto(OrdersDetail entity);
    OrdersDetail toEntity(OrdersDetailDTO dto);

    List<OrdersDetailDTO> toDtoList(List<OrdersDetail> entityList);
    List<OrdersDetail> toEntityList(List<OrdersDetailDTO> dtoList);
}
