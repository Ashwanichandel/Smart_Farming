package com.cf.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public  class ProfileInfo {
    private Long profileId;
    private String bio;
    private String address;
    private String city;
    private String state;
    private String pincode;
    private String country;
    private String idVerificationStatus;

    // Role-specific fields
    private Object roleSpecificData; // Will be FarmerProfileData, InvestorProfileData, or LandOwnerProfileData
}
