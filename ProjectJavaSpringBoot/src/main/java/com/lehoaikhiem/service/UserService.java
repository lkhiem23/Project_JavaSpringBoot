package com.lehoaikhiem.service;

import com.lehoaikhiem.entity.Roles;
import com.lehoaikhiem.entity.User;
import com.lehoaikhiem.repository.RolesRepository;
import com.lehoaikhiem.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UsersRepository usersRepository;
    private final RolesRepository roleRepository;
    private final PasswordEncoder passwordEncoder; // cần cấu hình Spring Security để dùng

    public User registerUser(String username, String rawPassword, String email) {
        Roles userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new RuntimeException("Role USER not found"));

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(rawPassword)); // mã hóa mật khẩu
        user.setEmail(email);
        user.setRoles(Collections.singleton(userRole));

        return usersRepository.save(user);
    }

    // Nếu muốn thêm user với role khác
    public User registerUserWithRole(String username, String rawPassword, String email, String roleName) {
        Roles role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Role " + roleName + " not found"));

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setEmail(email);
        user.setRoles(Collections.singleton(role));

        return usersRepository.save(user);
    }
}