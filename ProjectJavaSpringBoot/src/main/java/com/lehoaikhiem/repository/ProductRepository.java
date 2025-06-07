package com.lehoaikhiem.repository;

import com.lehoaikhiem.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);
    
    // Find by Name
    List<Product> findByNameContainingIgnoreCase(String name);

    // Find by ID
    List<Product> findByCategoryId(Long categoryId);

    // Find by Price (Low -> High)
    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);

    // Find by keyword in value
    List<Product> findByNameContainingAndCategoryId(String name, Long categoryId);

    // Find by slug
    Optional<Product> findBySlug(String slug);
}
