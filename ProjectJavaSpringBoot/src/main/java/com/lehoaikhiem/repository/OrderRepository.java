package com.lehoaikhiem.repository;

import com.lehoaikhiem.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface OrderRepository extends JpaRepository<Order, Long> {
    // Tìm đơn hàng theo trạng thái
    List<Order> findByIsActive(Boolean isActive);

    // Tìm đơn hàng theo ngày tạo
    List<Order> findByCreatedDateBetween(Date startDate, Date endDate);

    // Tìm đơn hàng theo khách hàng
    List<Order> findByCustomerId(Long customerId);

    // Tìm đơn hàng theo trạng thái và ngày tạo
    List<Order> findByIsActiveAndCreatedDateBetween(Boolean isActive, Date startDate, Date endDate);
}
