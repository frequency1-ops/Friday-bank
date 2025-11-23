package com.frequency.bank.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Account")
@Entity
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "account_id", columnDefinition = "BINARY(16)")
	private UUID accountId;
	
	@Column(name = "account_number")
	private String accountNumber;
	
	@Column(name = "account_type")
	@Enumerated(EnumType.STRING)
	private AccountType accountType;
	
	@Column(name = "balance", columnDefinition = "DECIMAL(15,2) DEFAULT 0.0")
	private BigDecimal balance = BigDecimal.ZERO;
	
	@Column(name= "created_at", updatable = false)
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	@OneToMany(mappedBy = "account")
	private List<Card> cards = new ArrayList<Card>();
	
	@OneToMany(mappedBy = "account")
	private List<Transaction> transactions = new ArrayList<Transaction>();
	
	@ManyToOne
	@JoinColumn(name = "branch_id")
	private Branch branch;
	
	public String generateAccountNumber() {
	       
        long random = (long) (Math.random() * 1_000_000_000L);
        return String.format("%09d", random);
    }

	

}
