package com.frequency.bank.controllers;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frequency.bank.dtos.BranchDto;
import com.frequency.bank.dtos.CreateBranchRequest;
import com.frequency.bank.mappers.BranchMapper;
import com.frequency.bank.repositories.BranchRepository;
import com.frequency.bank.service.BranchService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/branches")
@AllArgsConstructor
public class BranchController {
	
	private final BranchService branchService;
	
	@GetMapping()
	public ResponseEntity<Iterable<BranchDto>> getAllBranches(){
		return ResponseEntity.ok(branchService.getAllBranches());
	}
	
	@PostMapping("/create-branch")
	public ResponseEntity<Void> createBranch(
			@RequestBody CreateBranchRequest request
			){
		branchService.createBranch(request);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping("/{branch-id}")
	public ResponseEntity<BranchDto>  getBranch(
				@PathVariable(name = "branch-id") UUID branchId
			){
		var branchDto = branchService.getBranch(branchId);
		return ResponseEntity.ok(branchDto);
	}
	@DeleteMapping("/{branch-id}")
	public ResponseEntity<Void> deleteBranch(
				@PathVariable(name = "branch-id") UUID branchId
			){
		
		branchService.deleteBranch(branchId);
		return ResponseEntity.noContent().build();
	}
	

}
