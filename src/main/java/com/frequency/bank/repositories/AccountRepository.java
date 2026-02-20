package com.frequency.bank.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.frequency.bank.entities.Account;
import java.util.List;
import java.util.Optional;


public interface AccountRepository extends JpaRepository<Account, UUID>{
	
	Optional<Account> findByAccountNumber(String accountNumber);
}
