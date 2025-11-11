package com.frequency.bank.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "employee_id")
	private UUID employeeId;
	
	@Column(name = "branch_id")
	private UUID branch_id;
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "position")
	private String position;
	
	@Column(name = "hire_date")
	private LocalDate hireDate;
	
	@Column(name = "salary")
	private BigDecimal salary;
	
	
}
