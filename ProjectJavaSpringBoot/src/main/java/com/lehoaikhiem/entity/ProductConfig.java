package com.lehoaikhiem.entity;


import jakarta.persistence.*;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PRODUCT_CONFIG")
@Getter
@Setter
public class ProductConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto Increment ID
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "IDPRODUCT", referencedColumnName = "ID", nullable = false)
    private Product product;  // Giả sử bạn đã có một entity Product tương ứng với bảng PRODUCT

    @ManyToOne
    @JoinColumn(name = "IDCONFIG", referencedColumnName = "ID", nullable = false)
    private Configuration config;  // Giả sử bạn đã có một entity Config tương ứng với bảng CONFIG

    @Column(name = "VALUE", columnDefinition = "TEXT")
    private String value;

    public Long getId() {
        return id;
    }
}
