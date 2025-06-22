package com.lehoaikhiem.dto.OrdersDetail;

import com.lehoaikhiem.entity.OrdersDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
@Mapper(componentModel = "spring")
public interface OrdersDetailMapper {

    @Mapping(source = "order.id", target = "orderId")
    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.name", target = "productName") // Map tên sản phẩm
    @Mapping(source = "qty", target = "quantity") // Đổi qty thành quantity
    @Mapping(source = "price", target = "unitPrice") // Đổi price thành unitPrice
    @Mapping(source = "total", target = "totalPrice") // Đổi total thành totalPrice
    OrdersDetailDTO toDto(OrdersDetail entity);

    @Mapping(source = "orderId", target = "order.id")
    @Mapping(source = "productId", target = "product.id")
    @Mapping(source = "quantity", target = "qty")
    @Mapping(source = "unitPrice", target = "price")
    @Mapping(source = "totalPrice", target = "total")
    OrdersDetail toEntity(OrdersDetailDTO dto);

    List<OrdersDetailDTO> toDtoList(List<OrdersDetail> entityList);
    List<OrdersDetail> toEntityList(List<OrdersDetailDTO> dtoList);
}
