package com.lehoaikhiem.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ORDERS_DETAILS")
@Getter
@Setter
public class OrdersDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto Increment ID
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "IDORD", referencedColumnName = "ID", nullable = false)
    private Orders order;  // Assuming you have an Orders entity class representing the ORDER table

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

    // Constructor tiện lợi cho việc tạo OrdersDetail mới
    public OrdersDetail(Orders order, Product product, BigDecimal price, Integer qty) {
        this.order = order;
        this.product = product;
        this.price = price;
        this.qty = qty;
        this.total = price.multiply(BigDecimal.valueOf(qty));
        this.returnQty = 0;
    }
}
