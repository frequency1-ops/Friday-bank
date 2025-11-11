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
import jakarta.persistence.Table;

@Entity
@Table(name = "Loan")
public class Loan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "loan_id")
	private UUID loanId;
	
	@Column(name = "customer_id")
	private UUID customerId;
	
	@Column(name = "amount")
	private BigDecimal amount;
	
	@Column(name = "interest_rate")
	private BigDecimal interestRate;
	
	@Column(name = "start_date")
	private LocalDate startDate;
	
	@Column(name = "end_date")
	private LocalDate endDate;
	
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private LoanStatus status;

}
