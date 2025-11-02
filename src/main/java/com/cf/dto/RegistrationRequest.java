package com.cf.dto;

import com.cf.constant.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegistrationRequest {

	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email format")
	private String email;

	@NotBlank(message = "Password is required")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", 
	message = "Password must be at least 8 characters with uppercase, lowercase, number, and special character")
	private String password;

	@NotBlank(message = "First name is required")
	private String firstName;

	@NotBlank(message = "Last name is required")
	private String lastName;

	@NotBlank(message = "Phone number is required")
	@Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid Indian phone number")
	private String phoneNumber;

	@NotNull(message = "User role is required")
	private UserRole role; // ✅ inner enum from User class

	// Profile specific fields
	private String address;
	private String city;
	private String state;
	private String pincode;

	// Farmer specific
	private Integer experienceYears;
	private String farmingType;

	// Investor specific
	private Double investmentCapacityMin;
	private Double investmentCapacityMax;

	// Land Owner specific
	private Double totalLandArea;
	private String soilType;
}
