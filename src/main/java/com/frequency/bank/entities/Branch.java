package com.frequency.bank.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Branch")
public class Branch {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "branch_id", columnDefinition = "BINARY(16)")
	private UUID branchId;
	
	@Column(name = "branch_name")
	private String branchName;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "city")
	private String city;
	
	@OneToMany(mappedBy = "branch")
	private List<Employee> employees = new ArrayList<Employee>();
	
	@OneToMany(mappedBy = "branch")
	private List<Account> accounts = new ArrayList<>();
	
	@OneToOne
    @JoinColumn(name = "bank_manager_id", unique = true)
    private Employee bankManager;


}
