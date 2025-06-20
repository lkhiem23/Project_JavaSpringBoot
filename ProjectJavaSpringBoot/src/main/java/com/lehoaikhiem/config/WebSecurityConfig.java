package com.lehoaikhiem.config;

import com.lehoaikhiem.security.jwt.AuthEntryPointJwt;
import com.lehoaikhiem.security.jwt.AuthTokenFilter;
import com.lehoaikhiem.security.services.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider; // Để cấu hình AuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity; // Để bật @PreAuthorize
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy; // Để cấu hình quản lý session
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; // Cho PasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter; // Để thêm AuthTokenFilter vào chuỗi filter

@Configuration // Đánh dấu đây là lớp cấu hình Spring
@EnableMethodSecurity // Bật bảo mật dựa trên phương thức (ví dụ: @PreAuthorize)
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final UserDetailsServiceImpl userDetailsService;
    private final AuthEntryPointJwt unauthorizedHandler;
    // Bỏ dòng này: private final AuthTokenFilter authenticationJwtTokenFilter; // <-- XÓA DÒNG NÀY ĐI

    // Bean: PasswordEncoder (BCryptPasswordEncoder là lựa chọn tốt nhất)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Bean: AuthenticationProvider
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    // Bean: AuthenticationManager
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    // *** DÒNG QUAN TRỌNG: AuthTokenFilter không còn là field private final nữa ***
    // Thay vào đó, nó được đưa vào làm tham số của phương thức filterChain
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthTokenFilter authenticationJwtTokenFilter) throws Exception { // <--- ĐẢM BẢO CHỮ KÝ NÀY CHÍNH XÁC
        http.csrf(csrf -> csrf.disable())
                .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/api/auth/**").permitAll()
                                .requestMatchers("/api/test/**").permitAll()
                                .anyRequest().authenticated()
                );

        http.authenticationProvider(authenticationProvider());
        // Sử dụng tham số đã được inject vào phương thức
        http.addFilterBefore(authenticationJwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
