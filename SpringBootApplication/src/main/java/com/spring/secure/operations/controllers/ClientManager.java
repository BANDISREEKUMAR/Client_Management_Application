package com.spring.secure.operations.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.secure.operations.models.Client;
import com.spring.secure.operations.models.ClientDto;
import com.spring.secure.operations.repositories.ClientRepo;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/clients")
public class ClientManager {

	@Autowired
	private ClientRepo clientRepo;

	@GetMapping({ "", "/" })
	public String getClients(Model model) {
		var clients = clientRepo.findAll(Sort.by(Sort.Direction.DESC, "id"));
		model.addAttribute("clients", clients);
		return "clients/index";
	}

	@GetMapping("/create")
	public String createClient(Model model) {
		ClientDto clientDto = new ClientDto();
		model.addAttribute("clientDto", clientDto);
		return "clients/create";
	}

	@PostMapping("/create")
	public String createClient(@Valid @ModelAttribute ClientDto clientDto, BindingResult result) {
		if (clientRepo.findByEmail(clientDto.getEmail()) != null) {
			result.addError(new FieldError("clientDto", "email", clientDto.getEmail(), false, null, null,
					"Email Address is already in Use!"));
		}
		if (result.hasErrors()) {
			return "clients/create";
		}

		Client client = new Client();
		client.setFirstName(clientDto.getFirstName());
		client.setLastName(clientDto.getLastName());
		client.setPhone(clientDto.getPhone());
		client.setEmail(clientDto.getEmail());
		client.setAddress(clientDto.getAddress());
		client.setStatus(clientDto.getStatus());
		client.setCreatedAt(new Date());

		clientRepo.save(client);
		return "redirect:/clients";
	}

	@GetMapping("/edit")
	public String editClient(Model model, @RequestParam int id) {
		Client client = clientRepo.findById(id).orElse(null);
		if (client == null) {
			return "redirect:/clients";
		}
		ClientDto clientDto = new ClientDto();
		clientDto.setFirstName(client.getFirstName());
		clientDto.setLastName(client.getLastName());
		clientDto.setEmail(client.getEmail());
		clientDto.setPhone(client.getPhone());
		clientDto.setAddress(client.getAddress());
		clientDto.setStatus(client.getStatus());

		model.addAttribute("client", client);
		model.addAttribute("clientDto", clientDto);

		return "clients/edit";
	}

	@PostMapping("/edit")
	public String editClient(Model model, @RequestParam int id, @Valid @ModelAttribute ClientDto clientDto,
			BindingResult result) {
		return "redirect:/clients";
	}
}
