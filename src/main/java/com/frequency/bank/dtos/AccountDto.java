package com.frequency.bank.dtos;

import java.util.UUID;

import com.frequency.bank.entities.AccountType;

import lombok.Data;

@Data
public class AccountDto {
	private UUID accountId;
	private UUID customertId;
	private UUID branchId;
	private String accountNumber;
	private AccountType accountType;
}
