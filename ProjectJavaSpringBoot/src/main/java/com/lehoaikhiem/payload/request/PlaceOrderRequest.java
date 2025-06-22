package com.lehoaikhiem.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlaceOrderRequest {
    @NotBlank(message = "Tên người nhận không được để trống")
    private String nameReceiver;

    @NotBlank(message = "Địa chỉ giao hàng không được để trống")
    private String shippingAddress;

    @NotBlank(message = "Phone Number NOT NULL")
    @Pattern(regexp = "^\\d{10,15}$", message = "Số điện thoại phải từ 10 đến 15 chữ số")
    private String phoneNumber;

    @Email(message = "Địa chỉ email không hợp lệ")
    @NotBlank(message = "Email không được để trống")
    private String email;

    private String notes;
}
