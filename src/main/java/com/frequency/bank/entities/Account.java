package com.frequency.bank.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name = "Account")
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "account_id")
	private UUID accountId;
	
	@Column(name = "customer_id")
	private UUID customerId;
	
	@Column(name = "branch_id")
	private UUID branchId;
	
	@Column(name = "account_number")
	private String accountNumber;
	
	@Column(name = "account_type")
	@Enumerated(EnumType.STRING)
	private AccountType accountType;
	
	@Column(name = "balance")
	private BigDecimal balance;
	
	@Column(name= "created_at")
	private LocalDateTime createdAt;

}
