package com.frequency.bank.dtos;


import lombok.Data;

@Data
public class RegisterCustomerRequest {
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private String phone;
	private String address;
	
}
