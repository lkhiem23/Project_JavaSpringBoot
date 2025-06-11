package com.lehoaikhiem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // Ví dụ: "ADMIN", "USER"

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}
