package com.lehoaikhiem.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PRODUCT_IMAGES")
@Data
@Builder(toBuilder = true)
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto Increment ID
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", length = 250)
    private String name;

    @Column(name = "URLIMG", length = 250)
    private String urlImg;

    @ManyToOne
    @JoinColumn(name = "IDPRODUCT", referencedColumnName = "ID", nullable = false)
    private Product product;  // Giả sử bạn đã có một entity Product tương ứng với bảng PRODUCT
}
