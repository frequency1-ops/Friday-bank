package com.frequency.bank.service;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frequency.bank.dtos.LoanApplicationRequest;
import com.frequency.bank.dtos.LoanDto;
import com.frequency.bank.dtos.LoanPaymentRequest;
import com.frequency.bank.entities.LoanStatus;
import com.frequency.bank.mappers.LoanMapper;
import com.frequency.bank.repositories.AccountRepository;
import com.frequency.bank.repositories.CustomerRepository;
import com.frequency.bank.repositories.LoanRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LoanService {
	

	private final LoanRepository loanRepository;
	private final  LoanMapper loanMapper;
	private final AccountRepository accountRepository;
	
	public Iterable<LoanDto> getAllLoans(){
		return loanRepository.findAll().stream()
				.map(loanMapper::toDto).toList();
	}
	
	public LoanDto getLoan(UUID loanId) {
		
		var loan = loanRepository.findById(loanId).orElseThrow();
		
		return loanMapper.toDto(loan);
		
	}
	
	public LoanDto payLoan(UUID loanId, LoanPaymentRequest request) {
		var loan = loanRepository.findById(loanId).orElseThrow();
		BigDecimal balance = loan.getAmount().subtract(request.getAmount());
		loan.setAmount(balance);
		var account = loan.getAccount();
		account.setBalance(account.getBalance().subtract(request.getAmount()));
		loan.setAccount(account);
		loanRepository.save(loan);
		
		return loanMapper.toDto(loan);
	}
	
	@Transactional
	public LoanDto applyLoan(UUID accountId, LoanApplicationRequest request) {
		
		var account = accountRepository.findById(accountId).orElseThrow();
		var loan = loanMapper.toEntity(request);
		loan.setAccount(account);
		loan.setStatus(LoanStatus.ACTIVE);
		account.setBalance(account.getBalance().add(request.getAmount()));
		accountRepository.save(account);
		loanRepository.save(loan);
		
		return loanMapper.toDto(loan);
		
	}
	
	

}
