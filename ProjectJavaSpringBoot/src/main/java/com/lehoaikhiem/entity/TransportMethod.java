package com.lehoaikhiem.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TRANSPORT_METHOD")
@SuperBuilder(toBuilder = true)
@Data // Lombok annotation giúp tạo getter, setter, toString, equals, hashcode tự động
public class TransportMethod extends AbstractEntity{

    @Column(name = "NAME", length = 250)
    private String name;

    @Column(name = "NOTES", columnDefinition = "TEXT")
    private String notes;

    @Column(name = "ISDELETE")
    private Boolean isDelete;

    @Column(name = "ISACTIVE")
    private Boolean isActive;

}

