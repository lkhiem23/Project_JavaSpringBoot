package com.lehoaikhiem.repository;

import com.lehoaikhiem.entity.NewsCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsCategoryRepository extends JpaRepository<NewsCategory, Long> {
    List<NewsCategory> findByNameContainingIgnoreCase(String name);
}
