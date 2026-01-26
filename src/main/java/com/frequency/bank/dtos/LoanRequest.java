package com.frequency.bank.dtos;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class LoanRequest {
	private BigDecimal amount;

}
