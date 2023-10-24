package com.projetojpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetojpa.entities.Cliente;
import com.projetojpa.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	private final ClienteService clienteService;

	@Autowired
	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@GetMapping ("/{id}")
	@Operation(summary = "Busca um cliente pelo Id")
	public ResponseEntity<Cliente> buscaClienteIdControlId (@ PathVariable Long id) {
		Cliente cliente = clienteService.buscaClienteId(id);
		if (cliente != null) {
			return ResponseEntity.ok(cliente);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/")
	@Operation(summary = "Busca todos os clientes")
	public ResponseEntity<List<Cliente>> buscaTodosUsuarioControl(){
		List<Cliente> cliente = clienteService.buscaTodosCliente();
		return ResponseEntity.ok(cliente);
	}
	@PostMapping("/")
	public ResponseEntity<Cliente> salvaClienteControl(@RequestBody @Valid Cliente cliente){
		Cliente salvaCliente = clienteService.salvaCliente(cliente);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvaCliente);
	}
	@PutMapping("/{id}")
	@Operation(summary = "Altera um cliente")
	public ResponseEntity<Cliente> alterarClienteControl(@PathVariable Long id, @RequestBody @Valid Cliente cliente){
		Cliente alterarCliente = clienteService.alterarCliente(id, cliente);
		if(alterarCliente != null) {
			return ResponseEntity.ok(alterarCliente);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	@DeleteMapping("/{id}")
	@Operation(summary = "Exclui um cliente")
	public ResponseEntity<String> apagaClienteControl(@PathVariable Long id){
		boolean cliente = clienteService.apagarCliente(id);
		if (cliente) {
			return ResponseEntity.ok().body("O produto foi excluído com sucesso");
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
}

