package com.opentrack.system.service;

import com.opentrack.system.dto.LoginRequest;
import com.opentrack.system.dto.LoginResponse;
import com.opentrack.system.model.User;
import com.opentrack.system.repository.UserRepository;
import com.opentrack.system.security.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public LoginResponse login(LoginRequest request) {
        logger.info("=== LOGIN ATTEMPT ===");
        logger.info("Username: {}", request.getUsername());
        logger.info("Password length: {}", request.getPassword() != null ? request.getPassword().length() : "null");

        User user = userRepository.findByUsername(request.getUsername())
                .orElse(null);

        if (user == null) {
            logger.error("ERROR: User not found");
            return new LoginResponse(null, null, null, null, "Invalid credentials");
        }

        logger.info("User found: {} (ID: {})", user.getUsername(), user.getId());
        logger.info("User active: {}", user.getIsActive());
        logger.info("User role: {}", user.getRole());
        logger.info("Stored password hash: {}...", user.getPassword().substring(0, 20));
        logger.info("Raw password from request: {}", request.getPassword());

        if (!user.getIsActive()) {
            logger.error("ERROR: User is not active");
            return new LoginResponse(null, null, null, null, "Invalid credentials");
        }

        boolean passwordMatches = passwordEncoder.matches(request.getPassword(), user.getPassword());
        logger.info("Password matches: {}", passwordMatches);

        if (!passwordMatches) {
            logger.error("ERROR: Password verification failed");
            return new LoginResponse(null, null, null, null, "Invalid credentials");
        }

        logger.info("SUCCESS: Login successful for user: {}", user.getUsername());
        String token = jwtUtil.generateToken(user.getUsername(), user.getId(), user.getRole().toString());

        return new LoginResponse(
            user.getId(),
            user.getUsername(),
            user.getRole().toString(),
            token,
            "Login successful"
        );
    }
}