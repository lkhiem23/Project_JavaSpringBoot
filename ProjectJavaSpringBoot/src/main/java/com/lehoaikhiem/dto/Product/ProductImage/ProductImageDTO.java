package com.lehoaikhiem.dto.Product.ProductImage;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
public class ProductImageDTO {

    private Long id;

    @NotNull(message = "Name must not be null")
    @Size(min = 1, max = 250, message = "Name must be between 1 and 250 characters")
    private String name;

    @NotNull(message = "Image URL must not be null")
    @Size(min = 1, max = 250, message = "Image URL must be between 1 and 250 characters")
    private String urlImg;

    @NotNull(message = "Product ID must not be null")
    private Long productId;
}
