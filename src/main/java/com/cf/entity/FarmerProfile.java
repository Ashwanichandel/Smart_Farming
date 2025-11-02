package com.cf.entity;
 

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "farmer_profiles")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class FarmerProfile extends UserProfile {
    
    @Column(name = "experience_years")
    private Integer experienceYears;
    
    @Column(name = "farming_type")
    private String farmingType; // Organic, Traditional, Mixed
    
    @ElementCollection
    @CollectionTable(name = "farmer_specializations", joinColumns = @JoinColumn(name = "farmer_profile_id"))
    @Column(name = "specialization")
    private List<String> specializations; // Rice, Wheat, Cotton, Vegetables, etc.
    
    @ElementCollection
    @CollectionTable(name = "farmer_skills", joinColumns = @JoinColumn(name = "farmer_profile_id"))
    @Column(name = "skill")
    private List<String> skills; // Irrigation Management, Pest Control, etc.
    
    @Column(name = "owns_equipment")
    private Boolean ownsEquipment = false;
    
    @ElementCollection
    @CollectionTable(name = "farmer_equipment", joinColumns = @JoinColumn(name = "farmer_profile_id"))
    @Column(name = "equipment")
    private List<String> equipmentList;
    
    @Column(name = "total_land_area")
    private Double totalLandArea; // in acres
    
    @Column(name = "available_land_area")
    private Double availableLandArea;
    
    @Column(name = "looking_for_land")
    private Boolean lookingForLand = false;
    
    @Column(name = "looking_for_investment")
    private Boolean lookingForInvestment = false;
    
    @Column(name = "preferred_investment_range_min")
    private Double preferredInvestmentRangeMin;
    
    @Column(name = "preferred_investment_range_max")
    private Double preferredInvestmentRangeMax;
    
    @Column(name = "certifications")
    private String certifications; // Organic certification, etc.
    
    @Column(name = "rating")
    private Double rating = 0.0;
    
    @Column(name = "completed_projects")
    private Integer completedProjects = 0;
}