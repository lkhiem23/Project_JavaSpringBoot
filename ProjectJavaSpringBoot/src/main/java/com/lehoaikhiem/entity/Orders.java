package com.lehoaikhiem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ORDERS")
@Data
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "IDORDERS", length = 10)
    private String idOrders;

    @Column(name = "ORDERS_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ordersDate;

    @Column(name = "IDCUSTOMER", insertable = false, updatable = false)
    private Long idCustomer;  // Không cần cập nhật, chỉ để tham chiếu

    @Column(name = "IDPAYMENT")
    private Long idPayment;

    @Column(name = "IDTRANSPORT")
    private Long idTransport;

    @Column(name = "TOTAL_MONEY")
    private Double totalMoney;

    @Column(name = "NOTES", columnDefinition = "TEXT")
    private String notes;

    @Column(name = "NAME_RECIVER", length = 250)
    private String nameReceiver;

    @Column(name = "ADDRESS", length = 500)
    private String address;

    @Column(name = "EMAIL", length = 150)
    private String email;

    @Column(name = "PHONE", length = 50)
    private String phone;

    @Column(name = "ISDELETE")
    private Byte isDelete;

    @Column(name = "ISACTIVE")
    private Byte isActive;

    // Thiết lập quan hệ với bảng users
    @ManyToOne
    @JoinColumn(name = "IDCUSTOMER", referencedColumnName = "id")
    private User user;
}
