package com.lehoaikhiem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CUSTOMER")
@SuperBuilder(toBuilder = true)
@Data
public class Customer extends AbstractEntity{
    @Column(name = "NAME", length = 250)
    private String name;

    @Column(name = "USERNAME", length = 50, unique = true)
    private String username;

    @Column(name = "PASSWORD", length = 32)
    private String password;

    @Column(name = "ADDRESS", length = 500)
    private String address;

    @Column(name = "EMAIL", length = 150)
    private String email;

    @Column(name = "PHONE", length = 15)
    private String phone;

    @Column(name = "AVATAR", length = 250)
    private String avatar;

    @Column(name = "ISDELETE")
    private Boolean isDelete;

    @Column(name = "ISACTIVE")
    private Boolean isActive;

}
