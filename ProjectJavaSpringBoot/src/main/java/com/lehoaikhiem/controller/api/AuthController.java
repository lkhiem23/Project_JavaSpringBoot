package com.lehoaikhiem.controller.api;
import com.lehoaikhiem.payload.request.LoginRequest;
import com.lehoaikhiem.payload.request.RegisterRequest;
import com.lehoaikhiem.payload.response.JwtResponse;
import com.lehoaikhiem.payload.response.MessageResponse;
import com.lehoaikhiem.security.jwt.JwtUtils;
import com.lehoaikhiem.security.services.UserDetailsImpl;
import com.lehoaikhiem.service.UserService;
import jakarta.validation.Valid; // Cho validation của request body
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600) // Cho phép CORS từ mọi origin
@RestController // Đánh dấu đây là REST Controller
@RequestMapping("/api/auth") // Tất cả các endpoint trong controller này sẽ bắt đầu bằng /api/auth
@RequiredArgsConstructor // Lombok: Tự động tạo constructor cho các final field
public class AuthController {
    private final AuthenticationManager authenticationManager; // Để xác thực người dùng
    private final UserService userService; // Để đăng ký người dùng
    private final JwtUtils jwtUtils; // Để tạo và quản lý JWT

    // Endpoint Đăng nhập
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        // 1. Xác thực người dùng bằng username và password
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        // 2. Đặt đối tượng xác thực vào SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 3. Tạo JWT Token
        String jwt = jwtUtils.generateJwtToken(authentication);

        // 4. Lấy thông tin chi tiết người dùng và quyền hạn
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        // 5. Trả về JWT Response
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    // Endpoint Đăng ký
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest signUpRequest) {
        // 1. Kiểm tra username đã tồn tại chưa
        if (userService.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        // 2. Kiểm tra email đã tồn tại chưa
        if (userService.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // 3. Đăng ký người dùng mới (vai trò mặc định là USER, như chúng ta đã cấu hình trong UserService)
        try {
            userService.registerUser(signUpRequest.getUsername(), signUpRequest.getPassword(), signUpRequest.getEmail());
            return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
        } catch (RuntimeException e) {
            // Xử lý nếu có lỗi khi lưu vai trò (ví dụ: vai trò USER không tồn tại trong DB)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MessageResponse("Error: " + e.getMessage()));
        }
    }

}
