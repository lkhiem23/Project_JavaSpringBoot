package com.lehoaikhiem.dto.Customer;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CustomerDTO {
    private Long id;

    @NotBlank(message = "Tên không được để trống")
    private String name;

    @Email(message = "Email không hợp lệ")
    @NotBlank(message = "Email không được để trống")
    private String email;

    // Validation: Số điện thoại không được để trống và có độ dài tối thiểu 10 ký tự
    @NotBlank(message = "Số điện thoại không được để trống")
    @Size(min = 10, max = 11, message = "Số điện thoại phải có độ dài từ 10 đến 11 ký tự")
    @Pattern(regexp = "^0\\d{9,10}$", message = "Số điện thoại phải bắt đầu bằng số 0 và chỉ chứa chữ số")
    private String phone;
    private String address;

    //Admin
    private Boolean isDelete; // Chỉ Admin mới có thể thay đổi
    private Boolean isActive; // Chỉ Admin mới có thể thay đổi
}
