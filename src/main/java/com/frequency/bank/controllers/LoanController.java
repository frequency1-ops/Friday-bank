package com.frequency.bank.controllers;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frequency.bank.dtos.LoanApplicationRequest;
import com.frequency.bank.dtos.LoanDto;
import com.frequency.bank.dtos.LoanPaymentRequest;
import com.frequency.bank.service.LoanService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/loans")
@AllArgsConstructor
public class LoanController {
	private final LoanService loanService;
	
	
	@GetMapping
	public ResponseEntity<Iterable<LoanDto>> getAllLoans(){
		return ResponseEntity.ok(loanService.getAllLoans());
	}
	@GetMapping("/{loan-id}")
	public ResponseEntity<LoanDto> getLoan(
				@PathVariable(name="loan-id") UUID loanId
			){
		var loanDto = loanService.getLoan(loanId);
		return ResponseEntity.ok(loanDto);
	}
	
	@PostMapping("/{loan-id}/pay-loan")
	public ResponseEntity<LoanDto> payLoan(
			@PathVariable(name = "loan-id") UUID loanId,
			@RequestBody LoanPaymentRequest request){
		var loanDto = loanService.payLoan(loanId, request);
		return ResponseEntity.ok(loanDto);
	}
	@PostMapping("/{account-id}/apply-loan")
	public ResponseEntity<LoanDto> applyLoan(
			@PathVariable(name = "account-id") UUID accountId,
			@RequestBody LoanApplicationRequest request
			
			){
		var loanDto = loanService.applyLoan(accountId, request);
		
		return ResponseEntity.ok(loanDto);
	}

}
