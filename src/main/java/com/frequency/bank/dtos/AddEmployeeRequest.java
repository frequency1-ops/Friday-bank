package com.frequency.bank.dtos;

import java.math.BigDecimal;

import com.frequency.bank.entities.RoleType;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AddEmployeeRequest {
	@NotBlank
	private String firstName;
	@NotBlank
	private String lastName;
	@NotBlank
	private String email;
	private RoleType role;
	private BigDecimal salary;
	private String password;
}
