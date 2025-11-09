package com.frequency.bank.entities;

import java.util.List;

import jakarta.persistence.Entity;

@Entity
public class Customer {

	private Integer customerId;
	private String customerName;
	private List<Account> accounts;
}
