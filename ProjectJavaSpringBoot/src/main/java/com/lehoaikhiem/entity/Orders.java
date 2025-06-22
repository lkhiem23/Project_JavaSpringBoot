package com.lehoaikhiem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
    private BigDecimal totalMoney;

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
    private Boolean isDelete;

    @Column(name = "ISACTIVE")
    private Boolean isActive;

    // Thiết lập quan hệ với bảng users
    @ManyToOne
    @JoinColumn(name = "IDCUSTOMER", referencedColumnName = "id")
    private User user;

    // Mối quan hệ @OneToMany với OrdersDetail
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrdersDetail> ordersDetails = new HashSet<>();

    // Constructor tiện lợi cho việc tạo Orders mới từ service
    // Không bao gồm idTransport trong constructor này để đơn giản hóa
    public Orders(User user, BigDecimal totalMoney, String nameReceiver, String address, String phone, String notes, String email) {
        this.user = user;
        this.totalMoney = totalMoney;
        this.nameReceiver = nameReceiver;
        this.address = address;
        this.phone = phone;
        this.notes = notes;
        this.email = email;
        this.ordersDate = new Date(); // Thiết lập ngày tạo
        this.isDelete = false; // Mặc định
        this.isActive = true;  // Mặc định
        // idOrders, idPayment, idTransport sẽ được set riêng hoặc là null/mặc định
    }

    // Helper method để thêm OrdersDetail vào danh sách
    public void addOrderDetail(OrdersDetail ordersDetail) {
        ordersDetails.add(ordersDetail);
        ordersDetail.setOrder(this);
    }

    public void removeOrderDetail(OrdersDetail ordersDetail) {
        ordersDetails.remove(ordersDetail);
        ordersDetail.setOrder(null);
    }
}
