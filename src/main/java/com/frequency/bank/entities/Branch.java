package com.frequency.bank.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "Branch")
public class Branch {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "branch_id")
	private UUID branchId;
	
	@Column(name = "branch_name")
	private String branchName;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "bank_manager_id")
	private UUID bankManagerId;

}
