package com.frequency.bank.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.frequency.bank.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, UUID>{

}
