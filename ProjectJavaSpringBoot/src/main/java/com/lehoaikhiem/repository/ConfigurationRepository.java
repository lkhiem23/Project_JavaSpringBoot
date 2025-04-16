package com.lehoaikhiem.repository;

import com.lehoaikhiem.entity.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConfigurationRepository extends JpaRepository<Configuration, Long> {
    // Find configurations by exact name
    List<Configuration> findByName(String name);

    // Find configurations by name containing keyword (case-insensitive)
    List<Configuration> findByNameContainingIgnoreCase(String keyword);

    // Find configurations by isActive status
    List<Configuration> findByIsActive(Boolean isActive);

    // Find configurations by isDelete status
    List<Configuration> findByIsDelete(Boolean isDelete);

    // Find configurations by keyword and isActive status
    List<Configuration> findByNameContainingIgnoreCaseAndIsActive(String keyword, Boolean isActive);

    // Advanced search: keyword, isActive, and isDelete with custom JPQL query
    @Query("SELECT c FROM Configuration c WHERE " +
            "(:keyword IS NULL OR LOWER(c.name) LIKE LOWER(CONCAT('%', :keyword, '%'))) AND " +
            "(:isActive IS NULL OR c.isActive = :isActive) AND " +
            "(:isDelete IS NULL OR c.isDelete = :isDelete)")
    List<Configuration> search(String keyword, Boolean isActive, Boolean isDelete);
}
