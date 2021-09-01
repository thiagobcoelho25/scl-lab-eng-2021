package edu.ifes.ci.si.les.scl.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ifes.ci.si.les.scl.models.Estoque;
import edu.ifes.ci.si.les.scl.services.EstoqueService;

@RestController
@RequestMapping("/estoques")
public class EstoqueController {
	
	@Autowired
	private EstoqueService estoqueService;
	
	@GetMapping
	public ResponseEntity<List<Estoque>> listAll(){
		return ResponseEntity.ok().body(estoqueService.listAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Estoque> find(@PathVariable Integer id) {
		return ResponseEntity.ok().body(estoqueService.find(id));
	}
	
	@GetMapping("/diminuirQtdEstoque/{quantidade}/{ingredienteID}")
	public ResponseEntity<Void> diminuirQtdEstoquePedido(@PathVariable(name = "quantidade") Integer quantidade, @PathVariable(name = "ingredienteID") Integer ingredienteID){
		estoqueService.diminuirQtdEstoquePedido(quantidade, ingredienteID);
		return ResponseEntity.noContent().build();
	}
	
}
