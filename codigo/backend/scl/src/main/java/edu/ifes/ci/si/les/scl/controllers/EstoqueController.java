package edu.ifes.ci.si.les.scl.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public List<Estoque> listAll(){
		return estoqueService.listAll();
	}
	
	@GetMapping("/{estoqueId}")
	public Estoque find(@PathVariable Integer estoqueId) {
		return estoqueService.find(estoqueId);
	}
}
