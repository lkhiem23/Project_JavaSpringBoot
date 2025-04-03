package com.lehoaikhiem.repository;

import com.lehoaikhiem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
