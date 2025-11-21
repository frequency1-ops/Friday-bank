package com.frequency.bank.dtos;


import java.time.LocalDate;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterCustomerRequest {
	@NotBlank(message = "first name cannot be null")
	private String firstName;
	
	@NotBlank(message = "last name cannot be null")
	private String lastName;
	
	@NotBlank(message = "password cannot be null")
	@Size(min = 6, max = 25, message = "Password must be between 6 and 25 characters")
	private String password;
	
	@NotBlank(message = "email cannot be null")
	private String email;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dateOfBirth;
	
	@NotBlank(message = "Phone number cannot be left blank")
	private String phone;
	
	@NotBlank(message = "Addresses cannot be left blank")
	private String address;
	
	@NotBlank(message = "branch cannot be null")
	private String branchName;
	
	
	
}
