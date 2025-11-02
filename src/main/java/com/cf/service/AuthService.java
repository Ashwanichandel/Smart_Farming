package com.cf.service;

import com.cf.constant.*;
import com.cf.dto.RegistrationRequest;
import com.cf.dto.UserResponse;
import com.cf.entity.*;
import com.cf.repository.FarmerProfileRepository;
import com.cf.repository.InvestorProfileRepository;
import com.cf.repository.LandOwnerProfileRepository;
import com.cf.repository.UserRepository;
import com.cf.util.JavaUtil;
import com.cf.util.PasswordUtil;
import jakarta.validation.ValidationException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cf.dto.RegistrationRequest;


@Service
public class AuthService {
    private static final Logger log = LoggerFactory.getLogger(AuthService.class);
    private final UserRepository userRepository;
    private final FarmerProfileRepository farmerProfileRepository;
    private final InvestorProfileRepository investorProfileRepository;
    private final LandOwnerProfileRepository landOwnerProfileRepository;
    private final JavaUtil javaUtil;
    private final PasswordUtil passwordUtil;


    public AuthService(UserRepository userRepository, FarmerProfileRepository farmerProfileRepository, InvestorProfileRepository investorProfileRepository, LandOwnerProfileRepository landOwnerProfileRepository, JavaUtil javaUtil, PasswordUtil passwordUtil) {
        this.userRepository = userRepository;
        this.farmerProfileRepository = farmerProfileRepository;
        this.investorProfileRepository = investorProfileRepository;
        this.landOwnerProfileRepository = landOwnerProfileRepository;
        this.javaUtil = javaUtil;
        this.passwordUtil = passwordUtil;
    }

    @Transactional
    public void register(RegistrationRequest request) {
        log.info("Registration request recieved for email;{}", request.getEmail());
        log.info("Ashwan");


    }

    private static void validationRegistrationRequest(RegistrationRequest request) {
        log.info("Validation registration request");
        if (request.getEmail() == null || request.getEmail().trim().isEmpty()) {
            throw new ValidationException("email is required");
        }
        if (request.getPassword() == null || request.getPassword().trim().isEmpty()) {
            throw new ValidationException("password is requried");
        }

        if (request.getFirstName() == null || request.getFirstName().trim().isEmpty()) {
            throw new ValidationException("First name is required");
        }

        if (request.getLastName() == null || request.getLastName().trim().isEmpty()) {
            throw new ValidationException("Last name is required");
        }

        if (request.getPhoneNumber() == null || request.getPhoneNumber().trim().isEmpty()) {
            throw new ValidationException("Phone number is required");
        }

        if (request.getRole() == null) {
            throw new ValidationException("User role is required");
        }
        switch (request.getRole()) {
            case FARMER:
                validateFarmerRegistration(request);
                break;
            case INVESTOR:
                validateInvestorRegistration(request);
                break;
            case LAND_OWNER:
                validateLandOwnerRegistration(request);
                break;
        }

    }
    private static void validateFarmerRegistration(RegistrationRequest request) {
        if (request.getExperienceYears() != null && request.getExperienceYears() < 0) {
            throw new ValidationException("Experience years cannot be negative");
        }
    }

    private static void validateInvestorRegistration(RegistrationRequest request) {
        if (request.getInvestmentCapacityMin() != null && request.getInvestmentCapacityMin() < 0) {
            throw new ValidationException("Investment capacity minimum cannot be negative");
        }

        if (request.getInvestmentCapacityMax() != null && request.getInvestmentCapacityMax() < 0) {
            throw new ValidationException("Investment capacity maximum cannot be negative");
        }

        if (request.getInvestmentCapacityMin() != null && request.getInvestmentCapacityMax() != null) {
            if (request.getInvestmentCapacityMin() > request.getInvestmentCapacityMax()) {
                throw new ValidationException("Investment capacity minimum cannot be greater than maximum");
            }
        }
    }

    private static void validateLandOwnerRegistration(RegistrationRequest request) {
        if (request.getTotalLandArea() != null && request.getTotalLandArea() <= 0) {
            throw new ValidationException("Total land area must be greater than 0");
        }
    }
    private User createUser(RegistrationRequest request) {
        try {
            log.debug("Creating user entity");
            User user = new User();
            user.setEmail(request.getEmail());
            user.setPassword(passwordUtil.hashPassword(request.getPassword()));
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            user.setPhoneNumber(request.getPhoneNumber());
            user.setRole(request.getRole());
            user.setStatus(AccountStatus.PENDING_VERIFICATION);
            user.setEmailVerified(false);
            user.setPhoneVerified(false);

            log.debug("User entity created successfully");
            return user;
        } catch (Exception e) {
            log.error("Error creating user entity", e);
            throw new RuntimeException("Failed to create user: " + e.getMessage(), e);
        }
    }
    private UserProfile createRoleSpecificProfile(User user, RegistrationRequest request) {
        try {
            log.debug("Creating role-specific profile for role: {}", request.getRole());
            UserProfile profile;

            switch (request.getRole()) {
                case FARMER:
                    profile = createFarmerProfile(user, request);
                    break;

                case INVESTOR:
                    profile = createInvestorProfile(user, request);
                    break;

                case LAND_OWNER:
                    profile = createLandOwnerProfile(user, request);
                    break;

                default:
                    log.error("Invalid user role: {}", request.getRole());
                    throw new IllegalArgumentException("Invalid user role: " + request.getRole());
            }

            // Set common profile fields
            profile.setAddress(request.getAddress());
            profile.setCity(request.getCity());
            profile.setState(request.getState());
            profile.setPincode(request.getPincode());
            profile.setIdVerificationStatus(VerificationStatus.PENDING);

            log.debug("Role-specific profile created successfully");
            return profile;

        } catch (IllegalArgumentException e) {
            log.error("Invalid role in profile creation: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Error creating role-specific profile", e);
            throw new RuntimeException("Failed to create profile: " + e.getMessage(), e);
        }
    }

    private FarmerProfile createFarmerProfile(User user, RegistrationRequest request) {
        try {
            log.debug("Creating farmer profile");
            FarmerProfile farmerProfile = new FarmerProfile();
            farmerProfile.setUser(user);
            farmerProfile.setExperienceYears(request.getExperienceYears());
            farmerProfile.setFarmingType(request.getFarmingType());
            farmerProfile.setRating(0.0);
            farmerProfile.setCompletedProjects(0);
            farmerProfile.setLookingForLand(false);
            farmerProfile.setLookingForInvestment(false);
            farmerProfile.setOwnsEquipment(false);

            FarmerProfile savedProfile = farmerProfileRepository.save(farmerProfile);
            log.debug("Farmer profile saved with id: {}", savedProfile.getId());
            return savedProfile;
        } catch (Exception e) {
            log.error("Error creating farmer profile", e);
            throw new RuntimeException("Failed to create farmer profile: " + e.getMessage(), e);
        }
    }

    private InvestorProfile createInvestorProfile(User user, RegistrationRequest request) {
        try {
            log.debug("Creating investor profile");
            InvestorProfile investorProfile = new InvestorProfile();
            investorProfile.setUser(user);
            investorProfile.setInvestmentCapacityMin(request.getInvestmentCapacityMin());
            investorProfile.setInvestmentCapacityMax(request.getInvestmentCapacityMax());
            investorProfile.setRiskAppetite(RiskAppetite.MODERATE);
            investorProfile.setTotalInvested(0.0);
            investorProfile.setActiveInvestments(0);
            investorProfile.setCompletedInvestments(0);
            investorProfile.setAverageReturns(0.0);

            InvestorProfile savedProfile = investorProfileRepository.save(investorProfile);
            log.debug("Investor profile saved with id: {}", savedProfile.getId());
            return savedProfile;
        } catch (Exception e) {
            log.error("Error creating investor profile", e);
            throw new RuntimeException("Failed to create investor profile: " + e.getMessage(), e);
        }
    }

    private LandOwnerProfile createLandOwnerProfile(User user, RegistrationRequest request) {
        try {
            log.debug("Creating land owner profile");
            LandOwnerProfile landOwnerProfile = new LandOwnerProfile();
            landOwnerProfile.setUser(user);
            landOwnerProfile.setTotalLandArea(request.getTotalLandArea());
            landOwnerProfile.setAvailableLandArea(request.getTotalLandArea());
            landOwnerProfile.setSoilType(request.getSoilType());
            landOwnerProfile.setWaterAvailability(WaterAvailability.MODERATE);
            landOwnerProfile.setIrrigationFacility(false);
            landOwnerProfile.setElectricityAvailable(false);
            landOwnerProfile.setRoadConnectivity(Connectivity.MODERATE);
            landOwnerProfile.setLandVerified(false);
            landOwnerProfile.setLookingForPartnership(false);
            landOwnerProfile.setActiveLeases(0);
            landOwnerProfile.setTotalLeases(0);

            LandOwnerProfile savedProfile = landOwnerProfileRepository.save(landOwnerProfile);
            log.debug("Land owner profile saved with id: {}", savedProfile.getId());
            return savedProfile;
        } catch (Exception e) {
            log.error("Error creating land owner profile", e);
            throw new RuntimeException("Failed to create land owner profile: " + e.getMessage(), e);
        }
    }

    private UserResponse buildUserResponse(User user, String token, String refreshToken) {
        try {
            log.debug("Building user response");
            return UserResponse.builder()
                    .id(user.getId())
                    .email(user.getEmail())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .phoneNumber(user.getPhoneNumber())
                    .role(user.getRole())
                    .status(user.getStatus())
                    .emailVerified(user.isEmailVerified())
                    .phoneVerified(user.isPhoneVerified())
                    .profileImageUrl(user.getProfileImageUrl())
                    .createdAt(user.getCreatedAt())
                    .lastLoginAt(user.getLastLoginAt())
                    .token(token)
                    .refreshToken(refreshToken)
                    .build();
        } catch (Exception e) {
            log.error("Error building user response", e);
            throw new RuntimeException("Failed to build user response: " + e.getMessage(), e);
        }
    }

}



