package com.lehoaikhiem.dto.Configuration;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ConfigurationDTO {
    private Long id;

    @NotBlank(message = "Name must not be blank")
    @Size(max = 500, message = "Name must be less than or equal to 500 characters")
    private String name;

    private String notes;

    private Boolean isDelete;

    private Boolean isActive;
}
