package com.lehoaikhiem.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "NEWS")
@Data // Lombok annotation giúp tạo getter, setter, toString, equals, hashcode tự động
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto Increment ID
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", length = 500)
    private String name;

    @Column(name = "DESCRIPTION", columnDefinition = "TEXT")
    private String description;

    @Column(name = "IMAGE", length = 550)
    private String image;

    @ManyToOne
    @JoinColumn(name = "IDNEWSCATEGORY", referencedColumnName = "ID", nullable = false)
    private NewsCategory newsCategory;  // Assuming you have a NewsCategory entity class

    @Column(name = "CONTENTS", columnDefinition = "TEXT")
    private String contents;

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
