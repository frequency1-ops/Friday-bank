package com.frequency.bank.entities;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="Customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "customer_id", columnDefinition = "BINARY(16)")
	private UUID customerId;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "date_of_birth")
	private LocalDate dateOfBirth;
	
	@Column(name = "created_at", updatable = false, insertable = false)
	private LocalDateTime createdAt;
	
	@OneToMany(mappedBy = "customer")
	private List<Account> accounts = new ArrayList<Account>();
	
	@OneToMany(mappedBy = "customer")
	private List<Card> cards = new ArrayList<Card>();
	
	@OneToMany(mappedBy = "customer")
	private List<Loan> loans = new ArrayList<Loan>();

	
}
