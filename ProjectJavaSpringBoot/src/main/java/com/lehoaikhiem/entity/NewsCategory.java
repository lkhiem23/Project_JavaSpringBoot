package com.lehoaikhiem.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "NEWS_CATEGORY")
public class NewsCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto Increment ID
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", length = 500)
    private String name;

    @Column(name = "NOTES", columnDefinition = "TEXT")
    private String notes;

    @Column(name = "ICON", length = 250)
    private String icon;

    @ManyToOne
    @JoinColumn(name = "IDPARENT", referencedColumnName = "ID")
    private NewsCategory parentCategory;  // Quan hệ cha con với chính bảng này (Self-referencing)

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
