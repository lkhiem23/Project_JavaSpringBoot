package com.lehoaikhiem.dto.Product.ProductConfig;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
public class ProductConfigDTO {
    private Long id;

    @NotNull(message = "Product ID must not be null")
    private Long productId;

    @NotNull(message = "Configuration ID must not be null")
    private Long configId;

    @NotNull(message = "Value must not be null")
    @Size(min = 1, max = 1000, message = "Value must be between 1 and 1000 characters")
    private String value;
}
