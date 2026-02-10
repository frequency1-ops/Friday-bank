package com.frequency.bank.dtos;

import java.time.LocalDate;
import java.util.UUID;

import com.frequency.bank.entities.CardType;

import jakarta.persistence.EnumType;
import lombok.Data;

@Data
public class CardDto {
	
	private UUID cardId;
	private String cardNumber;
	private CardType cardType;
	private LocalDate expiryDate;
}
