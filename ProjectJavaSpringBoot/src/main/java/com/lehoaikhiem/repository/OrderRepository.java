package com.lehoaikhiem.repository;

import com.lehoaikhiem.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface OrderRepository extends JpaRepository<Orders, Long> {
    // Tìm đơn hàng theo trạng thái
    List<Orders> findByIsActive(Boolean isActive);

    // Tìm đơn hàng theo ngày tạo
    List<Orders> findByCreatedDateBetween(Date startDate, Date endDate);

    // Tìm đơn hàng theo khách hàng
    List<Orders> findByCustomerId(Long customerId);

    // Tìm đơn hàng theo trạng thái và ngày tạo
    List<Orders> findByIsActiveAndCreatedDateBetween(Boolean isActive, Date startDate, Date endDate);
}
