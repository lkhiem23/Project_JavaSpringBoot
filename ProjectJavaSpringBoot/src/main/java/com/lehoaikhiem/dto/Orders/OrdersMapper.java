package com.lehoaikhiem.dto.Orders;

import com.lehoaikhiem.entity.Orders;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrdersMapper {
    OrdersDTO toDto(Orders orders);
    Orders toEntity(OrdersDTO ordersDTO);
    List<OrdersDTO> toDtoList(List<Orders> list);
}
