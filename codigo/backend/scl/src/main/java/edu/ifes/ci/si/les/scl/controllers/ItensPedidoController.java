package edu.ifes.ci.si.les.scl.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ifes.ci.si.les.scl.models.ItensPedido;
import edu.ifes.ci.si.les.scl.services.ItensPedidoService;

@RestController
@RequestMapping("/itenspedido")
public class ItensPedidoController {

	@Autowired
	private ItensPedidoService itensPedidoService;
	
	@GetMapping
	public ResponseEntity<List <ItensPedido>> ListAll(){
		return ResponseEntity.ok().body(itensPedidoService.listAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ItensPedido> find(@PathVariable Integer id){
		return ResponseEntity.ok().body(itensPedidoService.find(id));
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ItensPedido> insert(@Valid @RequestBody ItensPedido itensPedido){
		return ResponseEntity.status(HttpStatus.CREATED).body(itensPedidoService.insert(itensPedido));
	}
	
	@PutMapping
	public ResponseEntity<ItensPedido> update(@Valid @RequestBody ItensPedido itensPedido){
		return ResponseEntity.ok().body(itensPedidoService.update(itensPedido));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		itensPedidoService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
