package com.frequency.bank.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.frequency.bank.dtos.BranchDto;
import com.frequency.bank.dtos.CreateBranchRequest;
import com.frequency.bank.mappers.BranchMapper;
import com.frequency.bank.repositories.BranchRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BranchService {
	private final BranchRepository branchRepository;
	private final BranchMapper branchMapper;
	
	public Iterable<BranchDto> getAllBranches(){
		return branchRepository.findAll()
				.stream().map(branchMapper::toDto).toList();
	}
	
	public void createBranch(CreateBranchRequest request) {
		var branch = branchMapper.toEntity(request);
		branchRepository.save(branch);
	}
	public BranchDto getBranch(UUID branchId) {
		var branch = branchRepository.findById(branchId).orElseThrow();
		return branchMapper.toDto(branch);
	}
	
	public void deleteBranch(UUID branchId) {
		var branch = branchRepository.findById(branchId).orElseThrow();
		branchRepository.delete(branch);
	}

}
