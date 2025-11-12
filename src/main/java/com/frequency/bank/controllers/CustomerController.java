package com.frequency.bank.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frequency.bank.dtos.CustomerDto;
import com.frequency.bank.mappers.CustomerMapper;
import com.frequency.bank.repositories.CustomerRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/customer")
public class CustomerController {
	private final CustomerRepository customerRepository;
	private final CustomerMapper customerMapper;
	
	@GetMapping
	public Iterable<CustomerDto> getAllCustomers(){
		return customerRepository.findAll().stream()
				.map(customerMapper::toDto).toList();
	}
}
