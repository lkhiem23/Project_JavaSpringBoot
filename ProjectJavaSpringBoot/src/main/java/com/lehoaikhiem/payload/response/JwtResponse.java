package com.lehoaikhiem.payload.response;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JwtResponse {
    private String token;
    private String type = "Bearer"; // Loại token, mặc định là "Bearer"
    private Long id;
    private String username;
    private String email;
    private List<String> roles; // Danh sách các vai trò của người dùng

    // Constructor để tạo JwtResponse
    public JwtResponse(String accessToken, Long id, String username, String email, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}
