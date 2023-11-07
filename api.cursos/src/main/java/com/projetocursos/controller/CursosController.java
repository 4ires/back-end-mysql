package com.projetocursos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetocursos.entities.Cursos;
import com.projetocursos.service.CursosService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/cursos")
public class CursosController {
	private final CursosService cursosService;

	@Autowired
	public CursosController(CursosService cursosService) {
		this.cursosService = cursosService;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cursos> buscaCursosControlId(@PathVariable Long id){
		Cursos cursos  = cursosService.buscaCursosId(id);
		if(cursos != null) {
			return ResponseEntity.ok(cursos);
		}
		else {
			return ResponseEntity.notFound().build();
		}

	}
	@GetMapping
	public ResponseEntity<List<Cursos>> buscaTodosCursosControl(){
		List<Cursos> cursos = cursosService.buscaTodosCursos();
		return ResponseEntity.ok(cursos);
	}

	@PostMapping
	public ResponseEntity<Cursos> salvaCursosControl(@RequestBody @Valid Cursos cursos){
		Cursos salvaCursos = cursosService.salvaCursos(cursos);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvaCursos);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cursos> alteraCursosControl(@PathVariable Long id, @RequestBody @Valid Cursos cursos){
		Cursos alteraCursos = cursosService.alterarCursos(id, cursos);
		if(alteraCursos != null) {
			return ResponseEntity.ok(cursos);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> apagaCursosControl(@PathVariable Long id){
		boolean apagar = cursosService.apagarCursos(id);
		if (apagar) {
			return ResponseEntity.ok().body("O Curso foi excluido com sucesso");
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
}
