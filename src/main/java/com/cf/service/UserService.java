package com.cf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cf.constant.UserRole;
import com.cf.dto.ProfileUpdateRequest;
import com.cf.dto.UserResponse;
import com.cf.entity.User;
import com.cf.exception.ResourceNotFoundException;
import com.cf.repository.FarmerProfileRepository;
import com.cf.repository.InvestorProfileRepository;
import com.cf.repository.LandOwnerProfileRepository;
import com.cf.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService implements UserServiceMethods {
	private final UserRepository userRepository;
	private final FarmerProfileRepository farmerProfileRepository;
	private final InvestorProfileRepository investorProfileRepository;
	private final LandOwnerProfileRepository landOwnerProfileRepository;

	@Autowired
    public UserService(UserRepository userRepository, FarmerProfileRepository farmerProfileRepository, InvestorProfileRepository investorProfileRepository, LandOwnerProfileRepository landOwnerProfileRepository) {
        this.userRepository = userRepository;
        this.farmerProfileRepository = farmerProfileRepository;
        this.investorProfileRepository = investorProfileRepository;
        this.landOwnerProfileRepository = landOwnerProfileRepository;
    }

    @Override
	public User getUserById(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

	}

	@Override
	public List<User> getAllUsers() {
		List<User> all = userRepository.findAll();
		return all;
	}

	@Override
	public List<User> getUsersByRole(UserRole role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateUser(Long id, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean existsByEmail(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean existsByPhoneNumber(String phoneNumber) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User updateProfileImage(Long userId, String imageUrl) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserResponse updateProfile(Long userId, ProfileUpdateRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void verifyEmail(Long userId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void verifyPhone(Long userId) {
		// TODO Auto-generated method stub

	}

	@Override
	public Long getUserCountByRole(UserRole role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserResponse getUserResponseById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}
