package com.lehoaikhiem.repository;

import com.lehoaikhiem.entity.TransportMethod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransportMethodRepository extends JpaRepository<TransportMethod, Long> {
    // Tìm kiếm cơ bản theo tên (chứa chuỗi, không phân biệt hoa thường)
    List<TransportMethod> findByNameContainingIgnoreCase(String name);

    // Lọc theo trạng thái hoạt động
    List<TransportMethod> findByIsActive(Boolean isActive);

    // Lọc theo trạng thái đã xóa hay chưa
    List<TransportMethod> findByIsDelete(Boolean isDelete);

//    // Kết hợp lọc theo tên + hoạt động
//    List<TransportMethod> findByNameContainingIgnoreCaseAndIsActive(String name, Boolean isActive);
//
//    // Kết hợp tên + hoạt động + isDelete
//    List<TransportMethod> findByNameContainingIgnoreCaseAndIsActiveAndIsDelete(
//            String name, Boolean isActive, Boolean isDelete
//    );
}
