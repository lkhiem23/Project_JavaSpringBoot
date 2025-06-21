package com.lehoaikhiem.payload.response;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal; // Đảm bảo import BigDecimal

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartResponse {
    private Long id; // ID của CartItem
    private Long productId;
    private String productName;
    private String productDescription;
    private BigDecimal productPrice;
    private Integer quantity; // Số lượng sản phẩm này trong giỏ hàng
    private BigDecimal subtotal; // Tổng tiền cho sản phẩm này (price * quantity)
}
