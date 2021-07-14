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

import edu.ifes.ci.si.les.scl.models.Ingrediente;
import edu.ifes.ci.si.les.scl.services.IngredienteService;

@RestController
@RequestMapping("/ingredientes")
public class IngredienteController {
	
	@Autowired
	private IngredienteService ingredienteService;
	
	@GetMapping
	public ResponseEntity<List<Ingrediente>> listAll(){
		return ResponseEntity.ok().body(ingredienteService.listAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Ingrediente> find(@PathVariable Integer id){
		return ResponseEntity.ok().body(ingredienteService.find(id));
	}
	
	@PostMapping
	public ResponseEntity<Ingrediente> insert(@Valid @RequestBody Ingrediente ingrediente){
		return ResponseEntity.status(HttpStatus.CREATED).body(ingredienteService.insert(ingrediente));
	}
	
	@PutMapping()
    public ResponseEntity<Ingrediente> update(@Valid @RequestBody Ingrediente ingrediente){
        return ResponseEntity.ok().body(ingredienteService.update(ingrediente));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
    	ingredienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

	
	@PostMapping("/estoque")
	public ResponseEntity<Ingrediente> insertEstoqueIngrediente(@Valid @RequestBody Ingrediente ingrediente){
		return ResponseEntity.status(HttpStatus.CREATED).body(ingredienteService.insertEstoqueIngrediente(ingrediente));
	}
}
