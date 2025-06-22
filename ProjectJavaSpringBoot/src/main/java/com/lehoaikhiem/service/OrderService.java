package com.lehoaikhiem.service;

import com.lehoaikhiem.dto.Orders.OrdersDTO;
import com.lehoaikhiem.dto.Orders.OrdersMapper;
import com.lehoaikhiem.dto.OrdersDetail.OrdersDetailMapper;
import com.lehoaikhiem.entity.*;
import com.lehoaikhiem.exception.ResourceNotFoundException;
import com.lehoaikhiem.payload.request.PlaceOrderRequest;
import com.lehoaikhiem.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository ordersRepository;
    private final OrdersDetailRepository ordersDetailRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UsersRepository userRepository;

    private final OrdersMapper ordersMapper;
    private final OrdersDetailMapper ordersDetailMapper;

    // Phương thức tạo đơn hàng từ giỏ hàng
    @Transactional
    public OrdersDTO placeOrderFromCart(Long userId, PlaceOrderRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        List<Cart> cartItems = cartRepository.findByUser(user);
        if (cartItems.isEmpty()) {
            throw new IllegalArgumentException("Cart is empty. Cannot place an order.");
        }

        BigDecimal totalOrderAmount = BigDecimal.ZERO; // <<< Dùng BigDecimal.ZERO
        List<OrdersDetail> ordersDetailsToSave = new ArrayList<>();
        List<Product> productsToUpdate = new ArrayList<>();

        for (Cart cartItem : cartItems) {
            Product product = cartItem.getProduct();
            Integer requestedQuantity = cartItem.getQuantity();

            if (product.getQuantity() < requestedQuantity) {
                throw new IllegalArgumentException("Not enough stock for product: " + product.getName() + ". Available: " + product.getQuantity() + ", Requested: " + requestedQuantity);
            }

            // Tạo OrdersDetail
            OrdersDetail ordersDetail = new OrdersDetail(
                    null, // Orders sẽ được set sau
                    product,
                    product.getPrice(), // Giá sản phẩm (BigDecimal)
                    requestedQuantity
            );
            ordersDetailsToSave.add(ordersDetail);

            totalOrderAmount = totalOrderAmount.add(product.getPrice().multiply(BigDecimal.valueOf(requestedQuantity))); // <<< Dùng BigDecimal.add() và BigDecimal.multiply()

            product.setQuantity(product.getQuantity() - requestedQuantity);
            productsToUpdate.add(product);
        }

        // Tạo Orders chính
        Orders orders = new Orders(
                user,
                totalOrderAmount, // <<< Dùng BigDecimal
                request.getNameReceiver(),
                request.getShippingAddress(),
                request.getPhoneNumber(),
                request.getNotes(),
                request.getEmail()
        );
        orders.setIdOrders(UUID.randomUUID().toString().substring(0, 10));
        orders.setIdPayment(null); // Set null hoặc ID mặc định
        orders.setIdTransport(null); // Set null hoặc ID mặc định
        orders.setIsActive(true);
        orders.setIsDelete(false);

        Orders savedOrders = ordersRepository.save(orders);

        for (OrdersDetail item : ordersDetailsToSave) {
            item.setOrder(savedOrders);
            savedOrders.addOrderDetail(item);
        }
        ordersDetailRepository.saveAll(ordersDetailsToSave);

        productRepository.saveAll(productsToUpdate);

        cartRepository.deleteByUser(user);

        return ordersMapper.toDto(savedOrders);
    }

    // Các phương thức khác của OrderService (lấy đơn hàng theo ID, theo User)
    @Transactional(readOnly = true)
    public OrdersDTO getOrderById(Long orderId, Long userId) {
        Orders orders = ordersRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + orderId));

        if (!orders.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("Order does not belong to the authenticated user.");
        }

        return ordersMapper.toDto(orders);
    }

    @Transactional(readOnly = true)
    public List<OrdersDTO> getOrdersByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        List<Orders> orders = ordersRepository.findByUser(user);
        return ordersMapper.toDtoList(orders);
    }
}
