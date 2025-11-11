package com.frequency.bank.entities;

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
@Table(name = "Card")
public class Card {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "card_id")
	private UUID cardId;
	
	@Column(name = "customer_id")
	private UUID customerId;
	
	@Column(name = "account_id")
	private UUID accountId;
	
	@Column(name = "card_number")
	private String cardNumber;
	
	@Column(name = "card_type")
	@Enumerated(EnumType.STRING)
	private CardType cardType;
	
	@Column(name = "expiry_date")
	private LocalDate expiryDate;
	
	@Column(name = "cvv")
	private String cvv;

}
