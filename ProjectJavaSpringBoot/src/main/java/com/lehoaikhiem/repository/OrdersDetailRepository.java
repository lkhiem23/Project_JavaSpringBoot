package com.lehoaikhiem.repository;

import com.lehoaikhiem.entity.OrdersDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.*;

public interface OrdersDetailRepository extends JpaRepository<OrdersDetail, Long> {

    // Tìm tất cả chi tiết theo ID đơn hàng
    List<OrdersDetail> findByOrderId(Long orderId);

    // Tìm tất cả chi tiết theo ID sản phẩm
    List<OrdersDetail> findByProductId(Long productId);

    // Tìm chi tiết theo đơn hàng và sản phẩm
    Optional<OrdersDetail> findByOrderIdAndProductId(Long orderId, Long productId);

    // Tìm các dòng có số lượng trả hàng > 0
    List<OrdersDetail> findByReturnQtyGreaterThan(Integer qty);

    // Tìm các dòng có số lượng mua > X
    List<OrdersDetail> findByQtyGreaterThan(Integer qty);

    // Tổng số lượng bán theo sản phẩm
    @Query("SELECT SUM(od.qty) FROM OrdersDetail od WHERE od.product.id = :productId")
    Integer getTotalSoldQtyByProductId(@Param("productId") Long productId);

    // Tìm các sản phẩm bán chạy theo tổng số lượng
    @Query("SELECT od.product.id, SUM(od.qty) as totalQty FROM OrdersDetail od GROUP BY od.product.id ORDER BY totalQty DESC")
    List<Object[]> findTopSellingProducts();

    // Tìm chi tiết đơn hàng có tổng tiền > x
    List<OrdersDetail> findByTotalGreaterThan(BigDecimal total);
}
