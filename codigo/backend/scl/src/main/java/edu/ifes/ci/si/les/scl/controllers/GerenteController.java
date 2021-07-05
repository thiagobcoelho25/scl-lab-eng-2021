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

import edu.ifes.ci.si.les.scl.models.Gerente;
import edu.ifes.ci.si.les.scl.services.GerenteService;


@RestController
@RequestMapping("/gerente")
public class GerenteController {
	
	@Autowired
	private GerenteService gerenteService;
	
	@GetMapping
	public ResponseEntity<List <Gerente>> ListAll(){
		return ResponseEntity.ok().body(gerenteService.listAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Gerente> find(@PathVariable Integer id){
		return ResponseEntity.ok().body(gerenteService.find(id));
	}
	
	@PostMapping
	public ResponseEntity<Gerente> insert(@Valid @RequestBody Gerente gerente){
		return ResponseEntity.status(HttpStatus.CREATED).body(gerenteService.insert(gerente));
	}
	
	@PutMapping
	public ResponseEntity<Gerente> update(@Valid @RequestBody Gerente gerente){
		return ResponseEntity.ok().body(gerenteService.update(gerente));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		gerenteService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
