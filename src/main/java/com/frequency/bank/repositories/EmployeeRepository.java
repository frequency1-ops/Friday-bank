package com.frequency.bank.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.frequency.bank.entities.Employee;



public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
	
	Optional<Employee> findByEmail(String email);
	

}
