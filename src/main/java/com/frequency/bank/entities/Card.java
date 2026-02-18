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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Card")
public class Card {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "card_id", columnDefinition = "BINARY(16)")
	private UUID cardId;
	
	
	@Column(name = "card_number")
	private String cardNumber;
	
	@Column(name = "card_type")
	@Enumerated(EnumType.STRING)
	private CardType cardType;
	
	@Column(name = "expiry_date")
	private LocalDate expiryDate;
	
	@Column(name = "cvv")
	private char[] cvv;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;
	
	public char[] generateCvv() {
		
		long random = (long) (Math.random() * 1_000L); 
		return String.format("%03d", random).toCharArray();

	}
	public String generateCardNumber() {
		
		long random = (long) (Math.random() * 10_000_000_000_000_000L); 
		return String.format("%016d", random);

	}
	 @PrePersist
	    void onCreate() {
	        if (this.expiryDate == null) {
	            this.expiryDate = LocalDate.now().plusMonths(36);
	        }
	    }

}
