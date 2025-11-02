package com.cf.util;

import com.cf.service.AuthService;
import lombok.Value;
import org.hibernate.annotations.Comment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtil {
    /**
     * Utility class for password hashing and verification
     * Uses BCrypt algorithm for secure password storage
     */
    private static final Logger log = LoggerFactory.getLogger(AuthService.class);
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructor initializes BCryptPasswordEncoder with default strength (10)
     */
    public PasswordUtil() {
        this.passwordEncoder = new BCryptPasswordEncoder();
        log.debug("PasswordUtil initialized with BCryptPasswordEncoder");
    }

    /**
     * Hash a plain text password using BCrypt
     *
     * @param plainPassword - The plain text password to hash
     * @return Hashed password string
     * @throws IllegalArgumentException if password is null or empty
     */
    public String hashPassword(String plainPassword) {
        try {
            if (plainPassword == null || plainPassword.trim().isEmpty()) {
                log.error("Attempted to hash null or empty password");
                throw new IllegalArgumentException("Password cannot be null or empty");
            }

            log.debug("Hashing password");
            String hashedPassword = passwordEncoder.encode(plainPassword);
            log.debug("Password hashed successfully");

            return hashedPassword;

        } catch (IllegalArgumentException e) {
            log.error("Password hashing validation failed: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Unexpected error during password hashing", e);
            throw new RuntimeException("Failed to hash password: " + e.getMessage(), e);
        }
    }

    /**
     * Verify if a plain text password matches a hashed password
     *
     * @param plainPassword  - The plain text password to verify
     * @param hashedPassword - The hashed password to compare against
     * @return true if passwords match, false otherwise
     * @throws IllegalArgumentException if either password is null or empty
     */
    public boolean verifyPassword(String plainPassword, String hashedPassword) {
        try {
            if (plainPassword == null || plainPassword.trim().isEmpty()) {
                log.error("Plain password is null or empty during verification");
                throw new IllegalArgumentException("Plain password cannot be null or empty");
            }

            if (hashedPassword == null || hashedPassword.trim().isEmpty()) {
                log.error("Hashed password is null or empty during verification");
                throw new IllegalArgumentException("Hashed password cannot be null or empty");
            }

            log.debug("Verifying password");
            boolean matches = passwordEncoder.matches(plainPassword, hashedPassword);

            if (matches) {
                log.debug("Password verification successful");
            } else {
                log.debug("Password verification failed - passwords do not match");
            }

            return matches;

        } catch (IllegalArgumentException e) {
            log.error("Password verification validation failed: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Unexpected error during password verification", e);
            throw new RuntimeException("Failed to verify password: " + e.getMessage(), e);
        }
    }

    /**
     * Check if a given string is a valid BCrypt hash
     * BCrypt hashes start with $2a$, $2b$, or $2y$ and are 60 characters long
     *
     * @param password - The string to check
     * @return true if the string appears to be a BCrypt hash
     */
    public boolean isBCryptHash(String password) {
        if (password == null || password.isEmpty()) {
            return false;
        }

        // BCrypt hash format: $2a$10$... (60 characters total)
        return password.matches("^\\$2[ayb]\\$.{56}$");
    }

    /**
     * Generate a BCrypt hash with custom strength
     *
     * @param plainPassword - The plain text password to hash
     * @param strength      - The BCrypt strength (4-31, recommended: 10-12)
     * @return Hashed password string
     * @throws IllegalArgumentException if password is invalid or strength is out of range
     */
    public String hashPasswordWithStrength(String plainPassword, int strength) {
        try {
            if (plainPassword == null || plainPassword.trim().isEmpty()) {
                log.error("Attempted to hash null or empty password");
                throw new IllegalArgumentException("Password cannot be null or empty");
            }

            if (strength < 4 || strength > 31) {
                log.error("Invalid BCrypt strength: {}", strength);
                throw new IllegalArgumentException("BCrypt strength must be between 4 and 31");
            }

            log.debug("Hashing password with custom strength: {}", strength);
            BCryptPasswordEncoder customEncoder = new BCryptPasswordEncoder(strength);
            String hashedPassword = customEncoder.encode(plainPassword);
            log.debug("Password hashed successfully with strength: {}", strength);

            return hashedPassword;

        } catch (IllegalArgumentException e) {
            log.error("Password hashing with custom strength failed: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Unexpected error during password hashing with custom strength", e);
            throw new RuntimeException("Failed to hash password: " + e.getMessage(), e);
        }
    }
}
