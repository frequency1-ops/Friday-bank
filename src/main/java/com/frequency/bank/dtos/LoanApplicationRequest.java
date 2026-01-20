package com.frequency.bank.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;


import lombok.Data;

@Data
public class LoanApplicationRequest {
	
	private UUID loanId;
	private BigDecimal amount;
	private BigDecimal interestRate;
	private LocalDate startDate;
	private LocalDate endDate;

}
