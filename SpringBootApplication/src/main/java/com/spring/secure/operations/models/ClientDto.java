package com.spring.secure.operations.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ClientDto {
	
	@NotEmpty(message="Please Provide the First Name")
	private  String firstName;
	
	@NotEmpty(message="Please Provide the Last Name")
	private String lastName;
	
	@NotEmpty(message="Email is Required")
	@Email
	private String email;
	
	private String phone;
	private String address;
	
	@NotEmpty(message="Please Provide the Status")
	private String status;
}
