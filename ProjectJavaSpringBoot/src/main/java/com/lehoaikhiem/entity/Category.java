package com.lehoaikhiem.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "CATEGORY")
@Data // Lombok annotation giúp tạo getter, setter, toString, equals, hashcode tự động

public class Category extends AbstractEntity{

    @Column(name = "NAME", length = 500)
    private String name;

    @Column(name = "NOTES", columnDefinition = "TEXT")
    private String notes;

    @Column(name = "ICON", length = 250)
    private String icon;

    @Column(name = "IDPARENT")
    private Long idParent;

    @Column(name = "SLUG", length = 160)
    private String slug;

    @Column(name = "META_TITLE", length = 100)
    private String metaTitle;

    @Column(name = "META_KEYWORD", length = 300)
    private String metaKeyword;

    @Column(name = "META_DESCRIPTION", length = 300)
    private String metaDescription;

    @Column(name = "ISDELETE")
    private Boolean isDelete;

    @Column(name = "ISACTIVE")
    private Boolean isActive;

}
