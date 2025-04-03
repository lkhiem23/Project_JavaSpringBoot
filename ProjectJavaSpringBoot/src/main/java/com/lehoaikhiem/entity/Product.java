package com.lehoaikhiem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PRODUCT")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto Increment ID
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", length = 500)
    private String name;

    @Column(name = "DESCRIPTION", columnDefinition = "TEXT")
    private String description;

    @Column(name = "NOTES", columnDefinition = "TEXT")
    private String notes;

    @Column(name = "IMAGE", length = 550)
    private String image;

    @Column(name = "IDCATEGORY")
    private Long categoryId;

    @Column(name = "CONTENTS", columnDefinition = "TEXT")
    private String contents;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @Column(name = "SLUG", length = 160)
    private String slug;

    @Column(name = "META_TITLE", length = 100)
    private String metaTitle;

    @Column(name = "META_KEYWORD", length = 500)
    private String metaKeyword;

    @Column(name = "META_DESCRIPTION", length = 500)
    private String metaDescription;

    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "UPDATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    @Column(name = "CREATED_BY")
    private Long createdBy;

    @Column(name = "UPDATED_BY")
    private Long updatedBy;

    @Column(name = "ISDELETE")
    private Boolean isDelete;

    @Column(name = "ISACTIVE")
    private Boolean isActive;

}
