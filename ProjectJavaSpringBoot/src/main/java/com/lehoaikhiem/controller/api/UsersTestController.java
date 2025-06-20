package com.lehoaikhiem.controller.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600) // Cho phép CORS từ mọi origin
@RestController
@RequestMapping("/api/test")
public class UsersTestController {
    // Endpoint công khai, không yêu cầu xác thực
    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    // Endpoint yêu cầu ít nhất một trong các quyền: USER, MODERATOR, ADMIN
    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String userAccess() {
        return "User Content. (Accessed by USER, MODERATOR, ADMIN)";
    }

    // Endpoint chỉ yêu cầu quyền MODERATOR
    @GetMapping("/mod")
    @PreAuthorize("hasRole('MODERATOR')")
    public String moderatorAccess() {
        return "Moderator Board. (Accessed by MODERATOR)";
    }

    // Endpoint chỉ yêu cầu quyền ADMIN
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board. (Accessed by ADMIN)";
    }
}
