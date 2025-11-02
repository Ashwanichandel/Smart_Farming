package com.cf.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cf.entity.FarmerProfile;
import com.cf.entity.User;

public interface FarmerProfileRepository extends JpaRepository<FarmerProfile, Long> {
Optional<FarmerProfile> findByUser(User user);
}
