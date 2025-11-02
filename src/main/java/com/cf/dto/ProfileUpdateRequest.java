package com.cf.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileUpdateRequest {
    
    // Basic profile fields
    private String bio;
    private String address;
    private String city;
    private String state;
    private String pincode;
    private String aadharNumber;
    private String panNumber;
    
    // Farmer profile fields
    private Integer experienceYears;
    private String farmingType;
    private List<String> specializations;
    private List<String> skills;
    private Boolean ownsEquipment;
    private List<String> equipmentList;
    private Double totalLandArea;
    private Double availableLandArea;
    private Boolean lookingForLand;
    private Boolean lookingForInvestment;
    private Double preferredInvestmentRangeMin;
    private Double preferredInvestmentRangeMax;
    private String certifications;
    
    // Investor profile fields
    private Double investmentCapacityMin;
    private Double investmentCapacityMax;
    private List<String> interests;
    private String riskAppetite;
    private Integer preferredProjectDurationMin;
    private Integer preferredProjectDurationMax;
    private List<String> preferredRegions;
    private Double expectedRoiMin;
    private Double expectedRoiMax;
    private String investorType;
    
    // Land owner profile fields
    private List<String> landLocations;
    private String soilType;
    private String waterAvailability;
    private Boolean irrigationFacility;
    private List<String> irrigationTypes;
    private Boolean electricityAvailable;
    private String roadConnectivity;
    private Double marketProximity;
    private Integer preferredLeaseDurationMin;
    private Integer preferredLeaseDurationMax;
    private Double leaseRatePerAcre;
    private Double revenueSharingPercentage;
    private Boolean lookingForPartnership;
}