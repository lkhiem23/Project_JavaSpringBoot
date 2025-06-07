package com.lehoaikhiem.repository;

import com.lehoaikhiem.entity.ProductConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductConfigRepository extends JpaRepository<ProductConfig, Long> {
    // Basic search: Find by Product ID
    List<ProductConfig> findByProductId(Long productId);

    // Basic search: Find by Config ID
    List<ProductConfig> findByConfigId(Long configId);

    // Advanced search: Find by productId and configId
    List<ProductConfig> findByProductIdAndConfigId(Long productId, Long configId);

    // Custom query: search by keyword in value
    @Query("SELECT pc FROM ProductConfig pc WHERE LOWER(pc.value) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<ProductConfig> searchByValueKeyword(String keyword);


}
