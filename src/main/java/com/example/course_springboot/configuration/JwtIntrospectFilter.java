package com.example.course_springboot.configuration;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.course_springboot.dto.request.IntrospectRequest;
import com.example.course_springboot.service.AuthenticationService;

@Component
public class JwtIntrospectFilter extends OncePerRequestFilter {
    @Autowired
    AuthenticationService authenticationService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer")) {
            String token = authHeader.substring(7);
            try {
                var introspectResponse = authenticationService.introspect(
                        IntrospectRequest.builder().token(token).build());
                if (!introspectResponse.isValid()) {
                    throw new JwtException("Token invalid");
                }
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Invalid token: " + e.getMessage());
            }
        }
        filterChain.doFilter(request, response);
    }
}
