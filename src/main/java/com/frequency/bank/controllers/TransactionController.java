package com.frequency.bank.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frequency.bank.mappers.TransactionMapper;
import com.frequency.bank.repositories.TransactionRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/transactions")
@AllArgsConstructor
public class TransactionController {
	
	private final TransactionRepository transactionRepository;
	private final TransactionMapper transactionMapper;
}
