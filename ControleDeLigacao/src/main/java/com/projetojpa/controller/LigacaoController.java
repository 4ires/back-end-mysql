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

import com.projetojpa.entities.Ligacao;
import com.projetojpa.service.LigacaoService;


@RestController
@RequestMapping("/ligacao")
public class LigacaoController {

	private final LigacaoService ligacaoService;
		
		@Autowired
		public LigacaoController(LigacaoService ligacaoService) {
			this.ligacaoService = ligacaoService;
		}
		@GetMapping ("/{id}")
		public ResponseEntity<Ligacao> buscaLigacaoIdControlId (@ PathVariable Long id) {
			Ligacao ligacao = ligacaoService.buscaLigacaoId(id);
			if (ligacao != null) {
				return ResponseEntity.ok(ligacao);
			}
			else {
				return ResponseEntity.notFound().build();
			}
		}

		@GetMapping("/")
		public ResponseEntity<List<Ligacao>> buscaTodasLigacaoControl(){
			List<Ligacao> ligacao = ligacaoService.buscaTodasLigacao();
			return ResponseEntity.ok(ligacao);
		}
		@PostMapping("/")
		public ResponseEntity<Ligacao> salvaLigacaoControl(@RequestBody Ligacao ligacao){
			Ligacao salvaLigacao = ligacaoService.salvaLigacao(ligacao);
			return ResponseEntity.status(HttpStatus.CREATED).body(salvaLigacao);
		}
		@PutMapping("/{id}")
		public ResponseEntity<Ligacao> alterarLigacaoControl(@PathVariable Long id, @RequestBody Ligacao ligacao){
			Ligacao alterarLigacao = ligacaoService.alterarLigacao(id, ligacao);
			if(alterarLigacao != null) {
				return ResponseEntity.ok(alterarLigacao);
			}
			else {
				return ResponseEntity.notFound().build();
			}
		}
		@DeleteMapping("/{id}")
		public ResponseEntity<String> apagaLigacaoControl(@PathVariable Long id){
			boolean apagar = ligacaoService.apagarLigacao(id);
			if (apagar) {
				return ResponseEntity.ok().body("O produto foi excluído com sucesso");
			}
			else {
				return ResponseEntity.notFound().build();
			}
		}
	}

