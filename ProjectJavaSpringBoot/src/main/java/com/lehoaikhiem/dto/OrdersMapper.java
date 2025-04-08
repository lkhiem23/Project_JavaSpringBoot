package com.lehoaikhiem.dto;

import com.lehoaikhiem.entity.Orders;

import java.util.List;

public interface OrdersMapper {
    OrdersDTO toDto(Orders orders);
    Orders toEntity(OrdersDTO ordersDTO);
    List<OrdersDTO> toDtoList(List<Orders> list);
}
