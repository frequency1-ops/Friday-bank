package com.frequency.bank.dtos;

import java.util.UUID;

import com.frequency.bank.entities.RoleType;

import lombok.Data;

@Data
public class EmployeeDto {
	
	private UUID employeeId;
	private String firstName;
	private String lastName;
	private RoleType role;
	private String branchName;


}
