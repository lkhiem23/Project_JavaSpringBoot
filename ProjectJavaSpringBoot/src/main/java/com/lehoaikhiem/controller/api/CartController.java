package com.lehoaikhiem.controller.api;
import com.lehoaikhiem.exception.ResourceNotFoundException;
import com.lehoaikhiem.payload.request.CartRequest;
import com.lehoaikhiem.payload.response.CartResponse;
import com.lehoaikhiem.security.services.UserDetailsImpl;
import com.lehoaikhiem.service.CartService;
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
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    // Lấy giỏ hàng của người dùng hiện tại
    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<CartResponse>> getCartItems(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<CartResponse> cartItems = cartService.getCartItems(userDetails.getId());
        return ResponseEntity.ok(cartItems);
    }

    // Thêm sản phẩm mới vào giỏ hàng hoặc cập nhật số lượng của sản phẩm đã có
    @PostMapping("/add")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<CartResponse> addProductToCart(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @Valid @RequestBody CartRequest request) { // Chú ý: tên DTO của bạn có thể là CartRequest
        CartResponse cartItem = cartService.addOrUpdateCartItem(userDetails.getId(), request);
        return ResponseEntity.status(HttpStatus.CREATED).body(cartItem);
    }

    // Cập nhật số lượng của một sản phẩm trong giỏ hàng
    @PutMapping("/{cartItemId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<CartResponse> updateCartItemQuantity(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long cartItemId,
            @RequestParam Integer quantity) {
        CartResponse updatedCartItem = cartService.updateCartItemQuantity(userDetails.getId(), cartItemId, quantity);
        if (updatedCartItem == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(updatedCartItem);
    }

    // Xóa một sản phẩm khỏi giỏ hàng
    @DeleteMapping("/{cartItemId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Void> deleteCartItem(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long cartItemId) {
        cartService.deleteCartItem(userDetails.getId(), cartItemId);
        return ResponseEntity.noContent().build();
    }

    // Xóa toàn bộ giỏ hàng
    @DeleteMapping("/clear")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Void> clearCart(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        cartService.clearCart(userDetails.getId());
        return ResponseEntity.noContent().build();
    }

    // Exception handler cho các lỗi ResourceNotFoundException hoặc IllegalArgumentException
    @ExceptionHandler({ResourceNotFoundException.class, IllegalArgumentException.class})
    public ResponseEntity<String> handleServiceExceptions(RuntimeException ex) {
        // Bạn có thể tùy chỉnh phản hồi lỗi ở đây.
        // Ví dụ: Bọc trong Error DTO của bạn nếu muốn
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
