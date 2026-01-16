package com.frequency.bank.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.frequency.bank.entities.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

}
