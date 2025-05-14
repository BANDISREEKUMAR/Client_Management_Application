package com.spring.secure.operations.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.secure.operations.models.Client;

public interface ClientRepo extends JpaRepository<Client,Integer> {
	public Client findByEmail(String email);
}
