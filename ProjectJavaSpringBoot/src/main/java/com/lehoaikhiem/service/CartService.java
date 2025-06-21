package com.lehoaikhiem.service;
import com.lehoaikhiem.entity.Cart;
import com.lehoaikhiem.entity.Product;
import com.lehoaikhiem.entity.User;
import com.lehoaikhiem.exception.ResourceNotFoundException;
import com.lehoaikhiem.payload.request.CartRequest;
import com.lehoaikhiem.payload.response.CartResponse;
import com.lehoaikhiem.repository.CartRepository;
import com.lehoaikhiem.repository.ProductRepository;
import com.lehoaikhiem.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UsersRepository userRepository; // Inject UserRepository

    // Phương thức thêm sản phẩm vào giỏ hàng (hoặc cập nhật nếu đã tồn tại)
    @Transactional
    public CartResponse addOrUpdateCartItem(Long userId, CartRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + request.getProductId()));

        if (product.getQuantity() < request.getQuantity()) {
            throw new IllegalArgumentException("Not enough stock for product: " + product.getName() + ". Available: " + product.getQuantity());
        }

        Optional<Cart> existingCartItem = cartItemRepository.findByUserAndProduct(user, product);

        Cart cartItem;
        if (existingCartItem.isPresent()) {
            cartItem = existingCartItem.get();
            // Cập nhật số lượng
            int newQuantity = cartItem.getQuantity() + request.getQuantity();
            if (product.getQuantity() < newQuantity) {
                throw new IllegalArgumentException("Not enough stock for product: " + product.getName() + " to add " + request.getQuantity() + ". Max quantity allowed: " + (product.getQuantity() - cartItem.getQuantity()));
            }
            cartItem.setQuantity(newQuantity);
        } else {
            // Thêm mới
            cartItem = new Cart(user, product, request.getQuantity());
        }

        Cart savedCartItem = cartItemRepository.save(cartItem);
        return mapToCartItemResponse(savedCartItem);
    }

    // Phương thức cập nhật số lượng sản phẩm trong giỏ hàng
    @Transactional
    public CartResponse updateCartItemQuantity(Long userId, Long cartItemId, Integer newQuantity) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        Cart cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart item not found with id: " + cartItemId));

        if (!cartItem.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("Cart item does not belong to the authenticated user.");
        }

        Product product = cartItem.getProduct();
        if (product.getQuantity() < newQuantity) {
            throw new IllegalArgumentException("Not enough stock for product: " + product.getName() + ". Requested: " + newQuantity + ", Available: " + product.getQuantity());
        }
        if (newQuantity <= 0) {
            deleteCartItem(userId, cartItemId); // Xóa nếu số lượng là 0 hoặc âm
            return null; // Hoặc trả về một đối tượng đặc biệt để báo hiệu đã xóa
        }

        cartItem.setQuantity(newQuantity);
        Cart savedCartItem = cartItemRepository.save(cartItem);
        return mapToCartItemResponse(savedCartItem);
    }

    // Phương thức xóa sản phẩm khỏi giỏ hàng
    @Transactional
    public void deleteCartItem(Long userId, Long cartItemId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        Cart cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart item not found with id: " + cartItemId));

        if (!cartItem.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("Cart item does not belong to the authenticated user.");
        }

        cartItemRepository.delete(cartItem);
    }

    // Phương thức lấy tất cả các sản phẩm trong giỏ hàng của người dùng
    @Transactional(readOnly = true)
    public List<CartResponse> getCartItems(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        List<Cart> cartItems = cartItemRepository.findByUser(user);
        return cartItems.stream()
                .map(this::mapToCartItemResponse)
                .collect(Collectors.toList());
    }

    // Phương thức xóa toàn bộ giỏ hàng của người dùng
    @Transactional
    public void clearCart(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        cartItemRepository.deleteByUser(user); // Cần @Transactional ở đây để method này hoạt động
    }


    // Helper method để chuyển đổi CartItem entity sang CartItemResponse DTO
    private CartResponse mapToCartItemResponse(Cart cartItem) {
        Product product = cartItem.getProduct();
        BigDecimal subtotal = product.getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity()));
        return new CartResponse(
                cartItem.getId(),
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                cartItem.getQuantity(),
                subtotal
        );
    }
}
