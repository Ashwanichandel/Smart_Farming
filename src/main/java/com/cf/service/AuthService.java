package com.cf.service;

import com.cf.dto.RegistrationRequest;
import com.cf.repository.FarmerProfileRepository;
import com.cf.repository.InvestorProfileRepository;
import com.cf.repository.LandOwnerProfileRepository;
import com.cf.repository.UserRepository;
import com.cf.util.JavaUtil;
import com.cf.util.PasswordUtil;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cf.dto.RegistrationRequest;

@Slf4j
@Service
public class AuthService {
    private static final Logger log = LoggerFactory.getLogger(AuthService.class);
    private final UserRepository userRepository;
    private final FarmerProfileRepository farmerProfileRepository;
    private final InvestorProfileRepository investorProfileRepository;
    private final LandOwnerProfileRepository landOwnerProfileRepository;
    private final JavaUtil javaUtil;
    private final PasswordUtil passwordUtil;


    public AuthService(UserRepository userRepository, FarmerProfileRepository farmerProfileRepository,
                       InvestorProfileRepository investorProfileRepository, LandOwnerProfileRepository
                               landOwnerProfileRepository, JavaUtil javaUtil, PasswordUtil passwordUtil) {
        this.userRepository = userRepository;
        this.farmerProfileRepository = farmerProfileRepository;
        this.investorProfileRepository = investorProfileRepository;
        this.landOwnerProfileRepository = landOwnerProfileRepository;
        this.javaUtil = javaUtil;
        this.passwordUtil = passwordUtil;
    }

    @Transactional
    public void register(RegistrationRequest request) {
        log.info("Registration request recieved for email;{}",request.getEmail());
        log.info("Ashwan");


    }

    private static void validationRegistrationRequest(RegistrationRequest request){
    }
}
