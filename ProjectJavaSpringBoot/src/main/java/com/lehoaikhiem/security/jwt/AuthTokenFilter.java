package com.lehoaikhiem.security.jwt;

import com.lehoaikhiem.security.services.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthTokenFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final UserDetailsServiceImpl userDetailsService;

    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String jwt = parseJwt(request);
            if (jwt != null) {
                logger.info("AuthTokenFilter: Found JWT in header. Token starts with: {}", jwt.substring(0, Math.min(jwt.length(), 20)));
                if (jwtUtils.validateJwtToken(jwt)) {
                    String username = jwtUtils.getUserNameFromJwtToken(jwt);

                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null, // Không có credentials (mật khẩu) ở đây vì đã xác thực qua token
                                    userDetails.getAuthorities());

                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    logger.info("AuthTokenFilter: User '{}' authenticated and set in SecurityContext. Roles: {}",
                            username, userDetails.getAuthorities()); // <-- THÊM LOG NÀY
                } else {
                    logger.warn("AuthTokenFilter: JWT validation failed for token starting with: {}", jwt.substring(0, Math.min(jwt.length(), 20)));
                }
            } else {
                logger.warn("AuthTokenFilter: No JWT found in Authorization header for request to {}", request.getRequestURI());
            }
        } catch (Exception e) {
            logger.error("AuthTokenFilter: Cannot set user authentication: {}", e.getMessage(), e);
        }

        filterChain.doFilter(request, response);
        // Sau khi filterChain.doFilter(), hãy kiểm tra lại SecurityContext
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            logger.warn("AuthTokenFilter: SecurityContext is null or authentication lost AFTER filterChain.doFilter for {}", request.getRequestURI());
        } else {
            logger.info("AuthTokenFilter: Authentication still present in SecurityContext AFTER filterChain.doFilter for {}", request.getRequestURI());
        }
    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }

        return null;
    }
}