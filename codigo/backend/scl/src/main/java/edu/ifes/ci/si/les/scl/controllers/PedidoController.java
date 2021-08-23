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

import edu.ifes.ci.si.les.scl.models.Pedido;
import edu.ifes.ci.si.les.scl.services.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoService pedidoService;
	
	@GetMapping
	public ResponseEntity<List <Pedido>> ListAll(){
		return ResponseEntity.ok().body(pedidoService.listAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pedido> find(@PathVariable Integer id){
		return ResponseEntity.ok().body(pedidoService.find(id));
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Pedido> insert(@Valid @RequestBody Pedido pedido){
		return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.insert(pedido));
	}
	
	@PutMapping
	public ResponseEntity<Pedido> update(@Valid @RequestBody Pedido pedido){
		return ResponseEntity.ok().body(pedidoService.update(pedido));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		pedidoService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
