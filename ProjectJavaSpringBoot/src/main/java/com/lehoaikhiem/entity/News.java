package com.lehoaikhiem.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor // <-- Giữ lại cái này nếu JPA cần constructor mặc định
@Entity
@Table(name = "NEWS")
@Data // Tạo getter, setter, toString, equals, hashcode tự động
@SuperBuilder(toBuilder = true) // <-- PHẢI LÀ @SuperBuilder cho lớp kế thừa
@EqualsAndHashCode(callSuper = true) // <-- RẤT QUAN TRỌNG VỚI @Data VÀ KẾ THỪA

public class News extends AbstractEntity {

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

    @Column(name = "ISDELETE")
    private Boolean isDelete;

    @Column(name = "ISACTIVE")
    private Boolean isActive;

}
