package com.frequency.bank.dtos;

import lombok.Data;

@Data
public class CreateBranchRequest {
	
	private String branchName;
	private String address;
	private String city;
}
