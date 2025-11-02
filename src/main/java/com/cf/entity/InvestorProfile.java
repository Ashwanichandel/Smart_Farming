package com.cf.entity;

import java.util.List;

import com.cf.constant.RiskAppetite;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "investor_profiles")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class InvestorProfile extends UserProfile {

	@Column(name = "investment_capacity_min")
	private Double investmentCapacityMin;

	@Column(name = "investment_capacity_max")
	private Double investmentCapacityMax;

	@ElementCollection
	@CollectionTable(name = "investor_interests", joinColumns = @JoinColumn(name = "investor_profile_id"))
	@Column(name = "interest")
	private List<String> interests; // Crop types they're interested in

	@Column(name = "risk_appetite")
	@Enumerated(EnumType.STRING)
	private RiskAppetite riskAppetite = RiskAppetite.MODERATE;

	@Column(name = "preferred_project_duration_min")
	private Integer preferredProjectDurationMin; // in months

	@Column(name = "preferred_project_duration_max")
	private Integer preferredProjectDurationMax;

	@ElementCollection
	@CollectionTable(name = "investor_preferred_regions", joinColumns = @JoinColumn(name = "investor_profile_id"))
	@Column(name = "region")
	private List<String> preferredRegions;

	@Column(name = "expected_roi_min")
	private Double expectedRoiMin;

	@Column(name = "expected_roi_max")
	private Double expectedRoiMax;

	@Column(name = "total_invested")
	private Double totalInvested = 0.0;

	@Column(name = "active_investments")
	private Integer activeInvestments = 0;

	@Column(name = "completed_investments")
	private Integer completedInvestments = 0;

	@Column(name = "average_returns")
	private Double averageReturns = 0.0;

	@Column(name = "investor_type")
	private String investorType; // Individual, Institutional, Corporate
}

 