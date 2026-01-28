package com.opentrack.system.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Utility to generate BCrypt password hashes
 * Run this as a Java application to generate the correct hash
 */
public class PasswordHashGenerator {
    
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        // Generate hash for "password123"
        String password = "password123";
        String hash = encoder.encode(password);
        
        System.out.println("========================================");
        System.out.println("PASSWORD HASH GENERATOR");
        System.out.println("========================================");
        System.out.println("Password: " + password);
        System.out.println("Hash: " + hash);
        System.out.println("========================================");
        System.out.println("\nVerification test:");
        System.out.println("Does hash match password? " + encoder.matches(password, hash));
        System.out.println("========================================");
        System.out.println("\nCopy this hash to your init-data.sql file!");
        System.out.println("Replace the old hash with:");
        System.out.println(hash);
        System.out.println("========================================");
    }
}