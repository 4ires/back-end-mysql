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

import com.projetocursos.entities.Pedidos;
import com.projetocursos.service.PedidosService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/pedidos")
public class PedidosController {
	private final PedidosService pedidosService;

	@Autowired
	public PedidosController(PedidosService pedidosService) {
		this.pedidosService = pedidosService;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pedidos> buscaPedidosControlId(@PathVariable Long id){
		Pedidos pedidos  = pedidosService.buscaPedidosId(id);
		if(pedidos != null) {
			return ResponseEntity.ok(pedidos);
		}
		else {
			return ResponseEntity.notFound().build();
		}

	}
	@GetMapping
	public ResponseEntity<List<Pedidos>> buscaTodosPedidosControl(){
		List<Pedidos> pedidos = pedidosService.buscaTodosPedidos();
		return ResponseEntity.ok(pedidos);
	}

	@PostMapping
	public ResponseEntity<Pedidos> salvaPedidosControl(@RequestBody @Valid Pedidos pedidos){
		Pedidos salvaPedidos = pedidosService.salvaPedidos(pedidos);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvaPedidos);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Pedidos> alteraCursosControl(@PathVariable Long id, @RequestBody @Valid Pedidos pedidos){
		Pedidos alteraPedidos = pedidosService.alterarPedidos(id, pedidos);
		if(alteraPedidos != null) {
			return ResponseEntity.ok(pedidos);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> apagaPedidosControl(@PathVariable Long id){
		boolean apagar = pedidosService.apagarPedidos(id);
		if (apagar) {
			return ResponseEntity.ok().body("O Curso foi excluido com sucesso");
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
}
