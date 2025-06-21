package com.lehoaikhiem.repository;

import com.lehoaikhiem.entity.Cart;
import com.lehoaikhiem.entity.Product;
import com.lehoaikhiem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByUser(User user);
    Optional<Cart> findByUserAndProduct(User user, Product product);
    void deleteByUser(User user); // Cần @Transactional ở Service khi gọi
}
