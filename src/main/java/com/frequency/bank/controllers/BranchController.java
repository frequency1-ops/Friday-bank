package com.frequency.bank.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frequency.bank.dtos.BranchDto;
import com.frequency.bank.mappers.BranchMapper;
import com.frequency.bank.repositories.BranchRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/branches")
@AllArgsConstructor
public class BranchController {
	
	private final BranchRepository branchRepository;
	private final BranchMapper branchMapper;
	
	@GetMapping()
	public ResponseEntity<Iterable<BranchDto>> getAllBranches(){
		return ResponseEntity.ok(branchRepository.findAll()
				.stream().map(branchMapper::toDto).toList());
	}
	
//	@GetMapping("/{branch-name}")
//	public ResponseEntity<BranchDto>  getBranch(
//				@PathVariable(name = "branch-name") String branchName
//			){
//		
//		var branch = branchMapper.toDto(branchRepository.findByBranchName(branchName));
//		return ResponseEntity.ok(branch);
//	}
	

}
