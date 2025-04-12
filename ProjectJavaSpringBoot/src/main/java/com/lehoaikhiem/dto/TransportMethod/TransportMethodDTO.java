package com.lehoaikhiem.dto.TransportMethod;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransportMethodDTO {
    private Long id;

    @NotBlank(message = "Tên phương thức vận chuyển không được để trống")
    @Size(max = 250, message = "Tên không được vượt quá 250 ký tự")
    private String name;

    private String notes;

    private Date createdDate;

    private Date updatedDate;

    private Boolean isDelete;

    private Boolean isActive;
}
