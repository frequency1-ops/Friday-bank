package com.frequency.bank.dtos;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class LoanPaymentRequest {
	private BigDecimal amount;
}
