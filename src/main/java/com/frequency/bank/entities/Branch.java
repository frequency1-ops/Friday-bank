package com.frequency.bank.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Branch {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "branch_id")
	private UUID branchId;
	

}
