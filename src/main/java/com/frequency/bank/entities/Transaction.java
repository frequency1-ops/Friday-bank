package com.frequency.bank.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
@Table(name = "Transaction")
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "transaction_id")
	private UUID transactionId;
	
	@Column(name = "account-id")
	private UUID accountId;
	
	@Column(name = "type")
	@Enumerated(EnumType.STRING)
	private TransactionType type;
	
	@Column(name = "amount")
	private BigDecimal amount;
	
	@Column(name = "timestamp")
	private LocalDateTime timeStamp;
	
	@Column(name = "description")
	private String description;

}
