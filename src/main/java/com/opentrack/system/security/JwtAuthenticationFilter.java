package com.opentrack.system.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();
        
        logger.debug("JWT Filter processing path: " + path);

        // Allow public paths without authentication
        if (isPublicPath(path)) {
            logger.debug("Public path detected, allowing access: " + path);
            filterChain.doFilter(request, response);
            return;
        }

        // For protected paths, require JWT token
        logger.debug("Protected path, checking JWT: " + path);
        
        try {
            String token = extractTokenFromRequest(request);

            if (token != null && jwtUtil.validateToken(token)) {
                String username = jwtUtil.extractUsername(token);
                String role = jwtUtil.extractRole(token);
                Long userId = jwtUtil.extractUserId(token);

                Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role));

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(username, null, authorities);
                authentication.setDetails(userId);

                SecurityContextHolder.getContext().setAuthentication(authentication);

                logger.debug("JWT authentication successful for user: " + username);
                filterChain.doFilter(request, response);
                return;
            }
        } catch (Exception e) {
            logger.error("JWT authentication error: " + e.getMessage());
        }

        // No valid token found for protected resource
        logger.debug("No valid JWT token, returning 401 for path: " + path);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write("{\"error\": \"Unauthorized\", \"message\": \"Valid JWT token required\"}");
    }

    private boolean isPublicPath(String path) {
        boolean isPublic = path.equals("/") ||
               path.equals("/index.html") ||
               path.equals("/driver.html") ||
               path.equals("/dispatcher.html") ||
               path.equals("/admin.html") ||
               path.equals("/reports.html") ||
               path.startsWith("/static/") ||
               path.startsWith("/css/") ||
               path.startsWith("/js/") ||
               path.startsWith("/images/") ||
               path.startsWith("/fonts/") ||
               path.startsWith("/webjars/") ||
               path.startsWith("/api/auth/") ||
               path.startsWith("/swagger-ui") ||
               path.startsWith("/v3/api-docs") ||
               path.matches(".*\\.(html|css|js|png|jpg|jpeg|gif|svg|ico|woff|woff2|ttf|eot)$");

        if (isPublic) {
            logger.debug("Path identified as public: " + path);
        }

        return isPublic;
    }

    private String extractTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}