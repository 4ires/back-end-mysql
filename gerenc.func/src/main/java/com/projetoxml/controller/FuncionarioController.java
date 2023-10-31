package com.projetoxml.controller;

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

import com.projetoxml.entities.Funcionario;
import com.projetoxml.service.FuncionarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "", description = "API REST DE GERENCIAMENTO DO Gerenciamento")
@RestController
@RequestMapping("/Funcionario")
public class FuncionarioController {

		private final FuncionarioService funcionarioService;

		@Autowired
		public FuncionarioController(FuncionarioService funcionarioService) {
			this.funcionarioService = funcionarioService;
		}

		@GetMapping ("/{id}")
		@Operation(summary = "Busca um funcionário pelo Id")
		public ResponseEntity<Funcionario> buscaFuncionarioIdControlId (@ PathVariable Long id) {
			Funcionario funcionario = funcionarioService.buscaFuncionarioId(id);
			if (funcionario != null) {
				return ResponseEntity.ok(funcionario);
			}
			else {
				return ResponseEntity.notFound().build();
			}
		}

		@GetMapping("/")
		@Operation(summary = "Busca todos os funcionários")
		public ResponseEntity<List<Funcionario>> buscaTodosFuncionarioControl(){
			List<Funcionario> funcionario = funcionarioService.buscaTodosFuncionario();
			return ResponseEntity.ok(funcionario);
		}
		@PostMapping("/")
		public ResponseEntity<Funcionario> salvaFuncionarioControl(@RequestBody @Valid Funcionario funcionario){
			Funcionario salvaFuncionario = funcionarioService.salvaFuncionario(funcionario);
			return ResponseEntity.status(HttpStatus.CREATED).body(salvaFuncionario);
		}
		@PutMapping("/{id}")
		@Operation(summary = "Altera um funcionário")
		public ResponseEntity<Funcionario> alterarFuncionarioControl(@PathVariable Long id, @RequestBody @Valid Funcionario funcionario){
			Funcionario alterarFuncionario = funcionarioService.alterarFuncionario(id, funcionario);
			if(alterarFuncionario != null) {
				return ResponseEntity.ok(alterarFuncionario);
			}
			else {
				return ResponseEntity.notFound().build();
			}
		}
		@DeleteMapping("/{id}")
		@Operation(summary = "Exclui um funcionário")
		public ResponseEntity<String> apagaFuncionarioControl(@PathVariable Long id){
			boolean funcionario = funcionarioService.apagarFuncionario(id);
			if (funcionario) {
				return ResponseEntity.ok().body("O funcionário foi excluído com sucesso");
			}
			else {
				return ResponseEntity.notFound().build();
			}
		}
	}
