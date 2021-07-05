package edu.ifes.ci.si.les.scl.controllers;

import java.util.List;

import javax.validation.Valid;

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

import edu.ifes.ci.si.les.scl.models.Produto;
import edu.ifes.ci.si.les.scl.services.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	
	@GetMapping
	public ResponseEntity<List<Produto>> listAll(){
		return ResponseEntity.ok().body(produtoService.listAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> find(@PathVariable Integer id){
		return ResponseEntity.ok().body(produtoService.find(id));
	}
	
	@PostMapping
	public ResponseEntity<Produto> insert(@Valid @RequestBody Produto produto){
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.insert(produto));
	}
	
	@PutMapping
	public ResponseEntity<Produto> update(@Valid @RequestBody Produto produto){
		return ResponseEntity.ok().body(produtoService.update(produto));
	}
	
	@DeleteMapping("/{id}")
	 public ResponseEntity<Void> delete(@PathVariable Integer id) {
		produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
