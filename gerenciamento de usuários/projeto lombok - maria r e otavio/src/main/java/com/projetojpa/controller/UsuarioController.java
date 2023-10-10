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

import com.projetojpa.entities.Usuario;
import com.projetojpa.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	private final UsuarioService usuarioService;

	@Autowired
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@GetMapping ("/{id}")
	public ResponseEntity<Usuario> buscaUsuarioIdControlId (@ PathVariable Long id) {
		Usuario usuario = usuarioService.buscaUsuarioId(id);
		if (usuario != null) {
			return ResponseEntity.ok(usuario);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/")
	public ResponseEntity<List<Usuario>> buscaTodosUsuarioControl(){
		List<Usuario> usuario = usuarioService.buscaTodosUsuario();
		return ResponseEntity.ok(usuario);
	}
	@PostMapping("/")
	public ResponseEntity<Usuario> salvaUsuarioControl(@RequestBody @Valid Usuario usuario){
		Usuario salvaUsuario = usuarioService.salvaUsuario(usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvaUsuario);
	}
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> alterarUsuarioControl(@PathVariable Long id, @RequestBody @Valid Usuario usuario){
		Usuario alterarUsuario = usuarioService.alterarUsuario(id, usuario);
		if(alterarUsuario != null) {
			return ResponseEntity.ok(alterarUsuario);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> apagaUsuarioControl(@PathVariable Long id){
		boolean usuario = usuarioService.apagarUsuario(id);
		if (usuario) {
			return ResponseEntity.ok().body("O produto foi excluído com sucesso");
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
}

