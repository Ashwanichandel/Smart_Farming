package com.cf.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cf.entity.InvestorProfile;
import com.cf.entity.User;

public interface InvestorProfileRepository extends JpaRepository<InvestorProfile, Long>{
Optional<InvestorProfile> findByUser(User user);
    
    Optional<InvestorProfile> findByUserId(Long userId);
    
    @Query("SELECT i FROM InvestorProfile i WHERE i.investmentCapacityMin <= :amount AND i.investmentCapacityMax >= :amount")
    List<InvestorProfile> findByInvestmentRange(Double amount);
    
    List<InvestorProfile> findByRiskAppetite(String riskAppetite);
}
