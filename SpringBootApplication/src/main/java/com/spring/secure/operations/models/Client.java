package com.spring.secure.operations.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Clients")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Client {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String firstName;
	
	private String lastName;
	
	@Column(unique=true,nullable=false)
	private String email;
	
	private String phone;
	
	private String address;
	
	private String status;
	
	private Date createdAt;
	
	
	
}
