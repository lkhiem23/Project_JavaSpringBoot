package com.lehoaikhiem.dto;

import com.lehoaikhiem.entity.Order;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderDTO {
    private Long id;

    @NotNull(message = "ID khách hàng không được để trống")
    private Long customerId;

    private String customerName;     // Hiển thị tên người nhận
    private String phone;
    private String address;
    private String note;

    private BigDecimal totalAmount;  // Tổng tiền

    private String paymentMethod;    // Tên phương thức thanh toán (ví dụ: VNPay, Momo, COD,...)
    private String transportMethod;  // Phương thức vận chuyển (GHN, GHTK...)

    private String status;           // Trạng thái đơn hàng (Đang xử lý, Đã giao, Hủy,...)

    private Date createdDate;

    // Danh sách chi tiết đơn hàng
    private List<OrderDetailsDTO> orderDetails;

    // Dành cho admin/quản lý đơn hàng
    private Boolean isDelete;  // Đánh dấu mềm đơn hàng
}
interface OrderMapper{
    OrderDTO toDto(Order order);
    Order toEntity(OrderDTO orderDTO);
    List<OrderDTO> toDtoList(List<Order> list);
}