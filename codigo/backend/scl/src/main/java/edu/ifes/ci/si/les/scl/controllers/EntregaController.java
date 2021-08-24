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
import edu.ifes.ci.si.les.scl.models.Entrega;
import edu.ifes.ci.si.les.scl.services.EntregaService;

@RestController
@RequestMapping("/entregas")
public class EntregaController {
	
	@Autowired
	private EntregaService entregaService;
	
	@GetMapping
	public ResponseEntity<List <Entrega>> ListAll(){
		return ResponseEntity.ok().body(entregaService.listAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Entrega> find(@PathVariable Integer id){
		return ResponseEntity.ok().body(entregaService.find(id));
	}
	
	@PostMapping
	public ResponseEntity<Entrega> insert(@Valid @RequestBody Entrega entrega){
		return ResponseEntity.status(HttpStatus.CREATED).body(entregaService.insert(entrega));
		
	}
	@PutMapping()
    public ResponseEntity<Entrega> update(@Valid @RequestBody Entrega entrega){
        return ResponseEntity.ok().body(entregaService.update(entrega));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        entregaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
