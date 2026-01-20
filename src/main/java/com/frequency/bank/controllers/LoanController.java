package com.frequency.bank.controllers;

import java.math.BigDecimal;
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
import com.frequency.bank.entities.Loan;
import com.frequency.bank.mappers.LoanMapper;
import com.frequency.bank.repositories.LoanRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/loans")
@AllArgsConstructor
public class LoanController {
	
	private final LoanRepository loanRepository;
	private final  LoanMapper loanMapper;
	
	@GetMapping
	public ResponseEntity<Iterable<LoanDto>> getAllLoans(){
		return ResponseEntity.ok(loanRepository.findAll().stream()
				.map(loanMapper::toDto).toList());
	}
	@GetMapping("/{id}")
	public ResponseEntity<LoanDto> getLoan(
				@PathVariable(name="id") UUID loanId
			){
		var loan = loanRepository.findById(loanId).orElseThrow();
		return ResponseEntity.ok(loanMapper.toDto(loan));
	}
	
	@PostMapping("/{id}/pay-loan")
	public ResponseEntity<LoanDto> payLoan(
			@PathVariable(name = "id") UUID loanId,
			@RequestBody LoanPaymentRequest request){
		var loan = loanRepository.findById(loanId).orElseThrow();
		BigDecimal balance = loan.getAmount().subtract(request.getAmount());
		loan.setAmount(balance);
		loanRepository.save(loan);
		return ResponseEntity.ok(loanMapper.toDto(loan));
	}
	@PostMapping("/{id}/apply-loan")
	public ResponseEntity<LoanDto> applyLoan(
			@PathVariable(name = "id") UUID customerId,
			@RequestBody LoanApplicationRequest request
			){
		return null;
	}

}
