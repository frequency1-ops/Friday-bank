package com.frequency.bank.dtos;


import java.time.LocalDate;

import lombok.Data;

@Data
public class RegisterCustomerRequest {
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private LocalDate dateOfBirth;
	private String phone;
	private String address;
	
}
