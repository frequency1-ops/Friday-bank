package com.frequency.bank.controllers;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frequency.bank.dtos.CustomerDto;
import com.frequency.bank.mappers.CustomerMapper;
import com.frequency.bank.repositories.CustomerRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/customers")
public class CustomerController {
	private final CustomerRepository customerRepository;
	private final CustomerMapper customerMapper;
	
	@GetMapping
	public Iterable<CustomerDto> getAllCustomers(){
		return customerRepository.findAll().stream()
				.map(customerMapper::toDto).toList();
	}
	
	@GetMapping("/{customerId}")
	private ResponseEntity<CustomerDto> getCustomerById(@PathVariable UUID customerId){
		var customer = customerRepository.findById(customerId).orElse(null);
		if (customer == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(customerMapper.toDto(customer));
	}
}
