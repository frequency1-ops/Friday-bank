package com.frequency.bank.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.frequency.bank.entities.LoanStatus;

import lombok.Data;

@Data
public class LoanDto {
	
	private UUID loanId;
	private LoanStatus status;
	private BigDecimal amount;
	private BigDecimal interestRate;
	private LocalDate startDate;
	private LocalDate endDate;

}
