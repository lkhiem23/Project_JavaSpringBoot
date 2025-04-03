package com.lehoaikhiem.repository;

import com.lehoaikhiem.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Tìm kiếm theo tên sản phẩm
    List<Product> findByNameContaining(String name);

    // Tìm kiếm theo danh mục sản phẩm
    List<Product> findByCategoryId(Long categoryId);

    // Tìm kiếm sản phẩm theo giá (từ giá thấp đến cao)
    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);

    // Tìm sản phẩm với tên chứa từ khóa và danh mục cụ thể
    List<Product> findByNameContainingAndCategoryId(String name, Long categoryId);

    // Tìm sản phẩm theo slug
    Optional<Product> findBySlug(String slug);
}
