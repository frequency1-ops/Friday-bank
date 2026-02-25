package com.frequency.bank.dtos;

import java.math.BigDecimal;

import com.frequency.bank.entities.RoleType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddEmployeeRequest {
	@NotBlank(message = "first Name cannot be left blank")
	private String firstName;
	@NotBlank(message = "Last name cannot be left blank")
	private String lastName;
	@NotBlank(message = "Email cannot be blank")
	@Email
	private String email;
	@NotBlank(message = "Role must be entered")
	private RoleType role;
	@NotNull
	@Positive
	private BigDecimal salary;
	@NotBlank
	@Size(min = 6, max = 25, message = "Password should be larger than 6 and less than 25")
	private String password;
}
