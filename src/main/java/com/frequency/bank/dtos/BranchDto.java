package com.frequency.bank.dtos;

import java.util.UUID;

import lombok.Data;

@Data
public class BranchDto {
	private UUID branchId;
	private String branchName;
	private String address;
	private String city;
	private UUID bankManagerId;
	private String branchManagerName;
}
