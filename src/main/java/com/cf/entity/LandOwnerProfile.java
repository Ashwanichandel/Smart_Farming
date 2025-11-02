package com.cf.entity;

import com.cf.constant.Connectivity;
import com.cf.constant.WaterAvailability;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "land_owner_profiles")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class LandOwnerProfile extends UserProfile {

	@Column(name = "total_land_area")
	private Double totalLandArea; // in acres

	@Column(name = "available_land_area")
	private Double availableLandArea;

	@ElementCollection
	@CollectionTable(name = "land_locations", joinColumns = @JoinColumn(name = "land_owner_profile_id"))
	@Column(name = "location")
	private List<String> landLocations;

	@Column(name = "soil_type")
	private String soilType; // Alluvial, Black, Red, Laterite, etc.

	@Column(name = "water_availability")
	@Enumerated(EnumType.STRING)
	private WaterAvailability waterAvailability = WaterAvailability.MODERATE;

	@Column(name = "irrigation_facility")
	private Boolean irrigationFacility = false;

	@ElementCollection
	@CollectionTable(name = "irrigation_types", joinColumns = @JoinColumn(name = "land_owner_profile_id"))
	@Column(name = "irrigation_type")
	private List<String> irrigationTypes; // Drip, Sprinkler, Canal, etc.

	@Column(name = "electricity_available")
	private Boolean electricityAvailable = false;

	@Column(name = "road_connectivity")
	@Enumerated(EnumType.STRING)
	private Connectivity roadConnectivity = Connectivity.MODERATE;

	@Column(name = "market_proximity")
	private Double marketProximity; // in km

	@Column(name = "ownership_documents")
	private String ownershipDocuments;

	@Column(name = "land_verified")
	private Boolean landVerified = false;

	@Column(name = "preferred_lease_duration_min")
	private Integer preferredLeaseDurationMin; // in months

	@Column(name = "preferred_lease_duration_max")
	private Integer preferredLeaseDurationMax;

	@Column(name = "lease_rate_per_acre")
	private Double leaseRatePerAcre;

	@Column(name = "revenue_sharing_percentage")
	private Double revenueSharingPercentage;

	@Column(name = "looking_for_partnership")
	private Boolean lookingForPartnership = false;

	@Column(name = "active_leases")
	private Integer activeLeases = 0;

	@Column(name = "total_leases")
	private Integer totalLeases = 0;
}

