//package com.lehoaikhiem.config;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//
//import java.io.IOException;
//
//@Configuration
//public class SercurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/signup", "/login", "/css/**", "/js/**").permitAll()
//                        .requestMatchers("/admin/**").hasAuthority("ADMIN") // Chỉ ADMIN được vào /admin
//                        .anyRequest().authenticated()
//                )
//                .formLogin(form -> form
//                        .loginPage("/login")
//                        .successHandler(customAuthenticationSuccessHandler()) // ✅ Điều hướng theo role
//                        .permitAll()
//                )
//                .logout(logout -> logout
//                        .logoutSuccessUrl("/login?logout")
//                        .permitAll()
//                );
//
//        return http.build();
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        // Không khai báo user nào cả, để tắt auto cấu hình user
//        return new InMemoryUserDetailsManager();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    // Điều hướng người dùng / admin
//    @Bean
//    public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
//        return new AuthenticationSuccessHandler() {
//            @Override
//            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//                for (GrantedAuthority auth : authentication.getAuthorities()) {
//                    if (auth.getAuthority().equals("ADMIN")) {
//                        response.sendRedirect("/admin/dashboard");
//                        return;
//                    } else if (auth.getAuthority().equals("USER")) {
//                        response.sendRedirect("/home");
//                        return;
//                    }
//                }
//
//                // fallback
//                response.sendRedirect("/");
//            }
//        };
//    }
//}