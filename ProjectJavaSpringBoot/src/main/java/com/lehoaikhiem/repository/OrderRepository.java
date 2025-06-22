package com.lehoaikhiem.repository;

import com.lehoaikhiem.entity.Orders;
import com.lehoaikhiem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface OrderRepository extends JpaRepository<Orders, Long> {
    // Tìm đơn hàng theo trạng thái và thời gian tạo
    List<Orders> findByIsActiveAndOrdersDateBetween(Boolean isActive, Date startDate, Date endDate);

    // <<< THÊM PHƯƠNG THỨC NÀY VÀO
    List<Orders> findByUser(User user);
}
