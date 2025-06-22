package com.lehoaikhiem.dto.Orders;

import com.lehoaikhiem.entity.Orders;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping; // Import nếu cần tùy chỉnh ánh xạ

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrdersMapper {
    @Mapping(source = "user.id", target = "customerId")
    @Mapping(source = "user.username", target = "customerName")
    @Mapping(source = "notes", target = "note")
    @Mapping(source = "ordersDate", target = "createdDate")
    @Mapping(source = "totalMoney", target = "totalAmount")
    OrdersDTO toDto(Orders orders);

    @Mapping(source = "customerId", target = "user.id")
    @Mapping(source = "note", target = "notes")
    @Mapping(source = "createdDate", target = "ordersDate")
    @Mapping(source = "totalAmount", target = "totalMoney")
    Orders toEntity(OrdersDTO ordersDTO);

    List<OrdersDTO> toDtoList(List<Orders> list);
    List<Orders> toEntityList(List<OrdersDTO> dtoList);
}