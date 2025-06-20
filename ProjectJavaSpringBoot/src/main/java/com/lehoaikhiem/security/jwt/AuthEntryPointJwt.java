package com.lehoaikhiem.security.jwt;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {
    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        logger.error("Unauthorized error: {}", authException.getMessage());
        // Ghi log lỗi không được ủy quyền.

        // Trả về lỗi 401 Unauthorized và thông báo lỗi dưới dạng JSON
        response.setContentType("application/json"); // Đặt Content-Type là JSON
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // Đặt trạng thái HTTP là 401 Unauthorized
        response.getOutputStream().println("{\"error\": \"" + authException.getMessage() + "\"}"); // Ghi thông báo lỗi vào response body
    }
}
