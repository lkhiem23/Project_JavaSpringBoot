package com.lehoaikhiem.repository;

import com.lehoaikhiem.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface NewsRepository extends JpaRepository<News, Long> {
    // Tìm tin tức theo tên
    List<News> findByNameContaining(String name);

    // Tìm tin tức theo danh mục
    List<News> findByCategoryId(Long categoryId);

    // Tìm tin tức theo trạng thái hoạt động
    List<News> findByIsActive(Boolean isActive);
}
