package com.frequency.bank.dtos;

import java.math.BigDecimal;

import com.frequency.bank.entities.RoleType;

import lombok.Data;

@Data
public class AddEmployeeRequest {
	private String firstName;
	private String lastName;
	private RoleType role;
	private BigDecimal salary;
	private String branch;
}
