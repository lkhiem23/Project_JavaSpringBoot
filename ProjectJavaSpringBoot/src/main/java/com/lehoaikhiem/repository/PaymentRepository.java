package com.lehoaikhiem.repository;

import com.lehoaikhiem.entity.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface PaymentRepository extends JpaRepository<PaymentMethod, Long> {
    // Tìm phương thức thanh toán theo tên
    List<PaymentMethod> findByNameContaining(String name);
}
