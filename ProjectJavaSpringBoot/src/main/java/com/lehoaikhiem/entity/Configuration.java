package com.lehoaikhiem.entity;


import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CONFIGURATIONS")
@Data // Lombok annotation giúp tạo getter, setter, toString, equals, hashcode tự động
public class Configuration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto Increment ID
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", length = 500)
    private String name;

    @Column(name = "NOTES", columnDefinition = "TEXT")
    private String notes;

    @Column(name = "ISDELETE")
    private Boolean isDelete;

    @Column(name = "ISACTIVE")
    private Boolean isActive;

}
