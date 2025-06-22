package com.lehoaikhiem.dto.Orders;

import com.lehoaikhiem.dto.OrdersDetail.OrdersDetailDTO;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrdersDTO {
    private Long id;

    @NotNull(message = "ID khách hàng không được để trống")
    private Long customerId;

    private String customerName;     // Hiển thị tên người nhận
    private String phone;
    private String address;
    private String note;

    private BigDecimal totalAmount;  // Tổng tiền

    private String paymentMethod;
    private String transportMethod;
    private String status;           // Trạng thái đơn hàng (Đang xử lý, Đã giao, Hủy,...)

    private Date createdDate;

    // Danh sách chi tiết đơn hàng
    private List<OrdersDetailDTO> orderDetails;

    // Dành cho admin/quản lý đơn hàng
    private Boolean isDelete;  // Đánh dấu mềm đơn hàng
}