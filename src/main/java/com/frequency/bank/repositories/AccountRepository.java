package com.frequency.bank.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.frequency.bank.entities.Account;

public interface AccountRepository extends JpaRepository<Account, UUID>{

}
