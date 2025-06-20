package com.lehoaikhiem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Thay đổi từ Long sang Integer để khớp với JpaRepository<Roles, Integer>

    @Enumerated(EnumType.STRING) // Annotation này chỉ định cách Enum được lưu trong DB (dưới dạng chuỗi)
    @Column(length = 20)
    private ERole name;

    public Roles(ERole name) {
        this.name = name;
    }

}