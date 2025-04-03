package com.lehoaikhiem.repository;

import com.lehoaikhiem.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Tìm khách hàng theo tên
    List<Customer> findByNameContaining(String name);

    // Tìm khách hàng theo username
    Optional<Customer> findByUsername(String username);

    // Tìm khách hàng theo email
    Optional<Customer> findByEmail(String email);

    // Tìm khách hàng theo trạng thái hoạt động
    List<Customer> findByIsActive(Boolean isActive);
}
