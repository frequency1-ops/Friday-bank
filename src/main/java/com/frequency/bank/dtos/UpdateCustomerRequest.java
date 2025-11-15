package com.frequency.bank.dtos;

import lombok.Data;

@Data
public class UpdateCustomerRequest {
	private String email;
	private String phone;
	private String address;
}
