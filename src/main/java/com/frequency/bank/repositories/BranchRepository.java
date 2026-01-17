package com.frequency.bank.repositories;


import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.frequency.bank.entities.Branch;


public interface BranchRepository extends JpaRepository<Branch, UUID>{
	Optional<Branch> findByBranchName(String branchName);
	
}
