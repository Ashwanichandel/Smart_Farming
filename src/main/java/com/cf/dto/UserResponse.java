package com.cf.dto;

 
import java.time.LocalDateTime;

import com.cf.constant.AccountStatus;
import com.cf.constant.UserRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private UserRole role;
    private AccountStatus status;
    private boolean emailVerified;
    private boolean phoneVerified;
    private String profileImageUrl;
    private LocalDateTime createdAt;
    private LocalDateTime lastLoginAt;
    
    private String token; // JWT token
    private String refreshToken;
    
    // Profile information
    private ProfileInfo profileInfo;
    

    
}
