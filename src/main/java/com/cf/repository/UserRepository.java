package com.cf.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cf.constant.UserRole;
import com.cf.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);

	Optional<User> findByPhoneNumber(String phoneNumber);

	boolean existsByEmail(String email);

	boolean existsByPhoneNumber(String phoneNumber);

	List<User> findByRole(UserRole role);

	List<User> findByEmailVerifiedTrue();

	@Query("SELECT u FROM User u WHERE u.role = :role AND u.emailVerified = true AND u.status = 'ACTIVE'")
	List<User> findActiveUsersByRole(UserRole role);

	@Query("SELECT COUNT(u) FROM User u WHERE u.role = :role")
    Long countByRole(UserRole role);
}
