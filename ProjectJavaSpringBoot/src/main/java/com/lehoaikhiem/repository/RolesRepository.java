package com.lehoaikhiem.repository;

import com.lehoaikhiem.entity.ERole;
import com.lehoaikhiem.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Roles, Integer> {
    Optional<Roles> findByName(ERole name);
}
