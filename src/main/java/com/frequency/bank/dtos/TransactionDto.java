package com.frequency.bank.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.frequency.bank.entities.TransactionType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionDto {
	
	private UUID transactionId;
	private TransactionType transactionType;
	private BigDecimal amount;
	private String description;
	private LocalDate timeStamp;

}
