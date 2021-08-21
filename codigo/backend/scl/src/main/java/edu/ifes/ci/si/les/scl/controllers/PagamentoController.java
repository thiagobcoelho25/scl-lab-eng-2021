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

import edu.ifes.ci.si.les.scl.models.Pagamento;
import edu.ifes.ci.si.les.scl.services.PagamentoService;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {

	@Autowired 
	private PagamentoService pagamentoService;
	
	@GetMapping
	public  ResponseEntity<List<Pagamento>> listAll(){
		return ResponseEntity.ok().body(pagamentoService.listAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pagamento> find(@PathVariable Integer id){
		return ResponseEntity.ok().body(pagamentoService.find(id));
	}
	
	@PostMapping
	public ResponseEntity<Pagamento> insert(@Valid @RequestBody Pagamento pagamento){
		return ResponseEntity.status(HttpStatus.CREATED).body(pagamentoService.insert(pagamento));
	}
	
	@PutMapping
	public ResponseEntity<Pagamento> update(@Valid @RequestBody Pagamento pagamento){
		return ResponseEntity.ok().body(pagamentoService.update(pagamento));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		pagamentoService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
