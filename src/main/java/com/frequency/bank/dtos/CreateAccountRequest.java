package com.frequency.bank.dtos;

import com.frequency.bank.entities.AccountType;

import lombok.Data;

@Data
public class CreateAccountRequest {
	private AccountType accountType;
	private String branchName;
}
