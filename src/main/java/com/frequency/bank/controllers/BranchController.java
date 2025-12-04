package com.frequency.bank.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frequency.bank.dtos.BranchDto;

@RestController
@RequestMapping("/branches")
public class BranchController {
	
	@GetMapping()
	public ResponseEntity<Iterable<BranchDto>> getAllBranches(){
		return null;
	}
	

}
