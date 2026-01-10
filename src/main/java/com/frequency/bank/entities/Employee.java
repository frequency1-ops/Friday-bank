package com.frequency.bank.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Employee")
@Getter
@Setter
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "employee_id", columnDefinition = "BINARY(16)")
	private UUID employeeId;
	
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	private RoleType role;
	
	@Column(name = "hire_date")
	private LocalDate hireDate;
	
	@Column(name = "salary")
	private BigDecimal salary;
	
	@ManyToOne
	@JoinColumn(name = "branch_id")
	private Branch branch;
	
	@OneToOne(mappedBy = "bankManager")
    private Branch managedBranch;
	
	
}
