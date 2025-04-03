package com.lehoaikhiem.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ORDERS_DETAILS")
@Data // Lombok annotation giúp tạo getter, setter, toString, equals, hashcode tự động
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto Increment ID
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "IDORD", referencedColumnName = "ID", nullable = false)
    private Order order;  // Assuming you have an Order entity class representing the ORDER table

    @ManyToOne
    @JoinColumn(name = "IDPRODUCT", referencedColumnName = "ID", nullable = false)
    private Product product;  // Assuming you have a Product entity class representing the PRODUCT table

    @Column(name = "PRICE", precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "QTY")
    private Integer qty;

    @Column(name = "TOTAL", precision = 10, scale = 2)
    private BigDecimal total;

    @Column(name = "RETURN_QTY")
    private Integer returnQty;
}
