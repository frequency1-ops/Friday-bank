package com.frequency.bank.dtos;

import java.math.BigDecimal;

import com.frequency.bank.entities.TransactionType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionRequest {
	
	@NotNull
	private TransactionType type;
	
	@NotNull
	@Positive
	private BigDecimal amount;
	
	@NotBlank
	private String description;

}
