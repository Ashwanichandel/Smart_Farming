package com.cf.service;

import java.util.List;

import com.cf.constant.UserRole;
import com.cf.dto.ProfileUpdateRequest;
import com.cf.dto.UserResponse;
import com.cf.entity.User;

public interface UserServiceMethods {
	  User getUserById(Long id);
	    
	    User getUserByEmail(String email);
	    
	    UserResponse getUserResponseById(Long id);
	    
	    List<User> getAllUsers();
	    
	    List<User> getUsersByRole(UserRole role);
	    
	    User updateUser(Long id, User user);
	    
	    void deleteUser(Long id);
	    
	    boolean existsByEmail(String email);
	    
	    boolean existsByPhoneNumber(String phoneNumber);
	    
	    User updateProfileImage(Long userId, String imageUrl);
	    
	    UserResponse updateProfile(Long userId, ProfileUpdateRequest request);
	    
	    void verifyEmail(Long userId);
	    
	    void verifyPhone(Long userId);
	    
	    Long getUserCountByRole(UserRole role);
}
