package com.frequency.bank.dtos;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.EnumType;
import lombok.Data;

@Data
public class CardDto {
	
	private UUID cardId;
	private String cardNUmber;
	private EnumType cardType;
	private LocalDate expiryDate;
}
