package com.lehoaikhiem.dto.OrdersDetail;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrdersDetailDTO {
    private Long id;

    @NotNull(message = "ID đơn hàng không được để trống")
    private Long orderId;

    @NotNull(message = "ID sản phẩm không được để trống")
    private Long productId;

    private String productName;  // Hiển thị tên sản phẩm (nếu cần)

    @NotNull(message = "Số lượng không được để trống")
    private Integer quantity;

    private BigDecimal unitPrice;
    private BigDecimal totalPrice;

    // Có thể thêm image hoặc slug nếu muốn hiển thị trên trang "Lịch sử đơn hàng"
}
