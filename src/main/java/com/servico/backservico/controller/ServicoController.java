package com.servico.backservico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servico.backservico.entity.Servico;
import com.servico.backservico.service.ServicoService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/servico")
public class ServicoController {
	
	@Autowired
	private ServicoService servicoService;
	
	@GetMapping("/")
	public List<Servico> buscarTodos(){
		return servicoService.buscarTodos();
	}
	
	@GetMapping("/pagamentoPendente")
	public List<Servico> buscarServicosPagamanetosPendente(){
		return servicoService.buscarServicosPagamanetosPendente();
	}
	
	@GetMapping("/cancelados")
	public List<Servico> buscarServicosCancelados() {
		return servicoService.buscarServicosCancelados();
	}
	
	@PostMapping("/")
	public Servico inserir(@RequestBody Servico servico) {
		return servicoService.inserir(servico);
	}
	
	@PutMapping("/")
	public Servico alterar(@RequestBody Servico servico) {
		return servicoService.alterar(servico);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathParam("id") Long id){
		servicoService.remover(id);
		return ResponseEntity.ok().build();
	}
}
