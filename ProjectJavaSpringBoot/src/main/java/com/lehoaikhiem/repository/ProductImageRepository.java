package com.lehoaikhiem.repository;

import com.lehoaikhiem.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

    // Basic search: Find all images by product ID
    List<ProductImage> findByProductId(Long productId);

    // Advanced search: Find image by name (exact match)
    List<ProductImage> findByName(String name);

    // Advanced search: Find image by partial name (contains)
    List<ProductImage> findByNameContainingIgnoreCase(String name);

    // Custom query: search both name and URL with a keyword
    @Query("SELECT pi FROM ProductImage pi WHERE LOWER(pi.name) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(pi.urlImg) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<ProductImage> searchByKeyword(String keyword);
}
