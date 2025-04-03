package com.lehoaikhiem.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TRANSPORT_METHOD")
@Data // Lombok annotation giúp tạo getter, setter, toString, equals, hashcode tự động
public class TransportMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto Increment ID
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", length = 250)
    private String name;

    @Column(name = "NOTES", columnDefinition = "TEXT")
    private String notes;

    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "UPDATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    @Column(name = "ISDELETE")
    private Boolean isDelete;

    @Column(name = "ISACTIVE")
    private Boolean isActive;

}

