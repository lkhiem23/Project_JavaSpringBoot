package com.lehoaikhiem.controller.api;

import com.lehoaikhiem.dto.Orders.OrdersDTO;
import com.lehoaikhiem.exception.ResourceNotFoundException;
import com.lehoaikhiem.payload.request.PlaceOrderRequest;
import com.lehoaikhiem.security.services.UserDetailsImpl;
import com.lehoaikhiem.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    /**
     * API để đặt hàng từ giỏ hàng của người dùng.
     * Yêu cầu xác thực (USER hoặc ADMIN role).
     * @param userDetails Thông tin người dùng hiện tại (lấy từ Spring Security context).
     * @param request Dữ liệu yêu cầu đặt hàng (thông tin người nhận, địa chỉ, v.v.).
     * @return ResponseEntity chứa OrdersDTO của đơn hàng đã tạo.
     */
    @PostMapping("/place")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<OrdersDTO> placeOrder(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @Valid @RequestBody PlaceOrderRequest request) {
        OrdersDTO newOrder = orderService.placeOrderFromCart(userDetails.getId(), request);
        return ResponseEntity.status(HttpStatus.CREATED).body(newOrder);
    }

    /**
     * API để lấy chi tiết một đơn hàng cụ thể.
     * Yêu cầu xác thực (USER hoặc ADMIN role).
     * Đảm bảo đơn hàng thuộc về người dùng đang đăng nhập.
     * @param userDetails Thông tin người dùng hiện tại.
     * @param orderId ID của đơn hàng cần lấy chi tiết.
     * @return ResponseEntity chứa OrdersDTO của đơn hàng.
     */
    @GetMapping("/{orderId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<OrdersDTO> getOrderDetails(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long orderId) {
        OrdersDTO orderDTO = orderService.getOrderById(orderId, userDetails.getId());
        return ResponseEntity.ok(orderDTO);
    }

    /**
     * API để lấy tất cả các đơn hàng của người dùng hiện tại.
     * Yêu cầu xác thực (USER hoặc ADMIN role).
     * @param userDetails Thông tin người dùng hiện tại.
     * @return ResponseEntity chứa danh sách OrdersDTO của các đơn hàng.
     */
    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<OrdersDTO>> getUserOrders(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<OrdersDTO> orders = orderService.getOrdersByUser(userDetails.getId());
        return ResponseEntity.ok(orders);
    }

    /**
     * Xử lý các ngoại lệ (exceptions) phổ biến.
     * @param ex Ngoại lệ RuntimeException.
     * @return ResponseEntity chứa thông báo lỗi.
     */
    @ExceptionHandler({ResourceNotFoundException.class, IllegalArgumentException.class})
    public ResponseEntity<String> handleServiceExceptions(RuntimeException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
