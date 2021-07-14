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

import edu.ifes.ci.si.les.scl.models.Cliente;
import edu.ifes.ci.si.les.scl.services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public ResponseEntity<List <Cliente>> ListAll(){
		return ResponseEntity.ok().body(clienteService.listAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> find(@PathVariable Integer id){
		return ResponseEntity.ok().body(clienteService.find(id));
	}
	
	@PostMapping
	public ResponseEntity<Cliente> insert(@Valid @RequestBody Cliente cliente){
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.insert(cliente));
		
	}
	@PutMapping()
    public ResponseEntity<Cliente> update(@Valid @RequestBody Cliente cliente){
        return ResponseEntity.ok().body(clienteService.update(cliente));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
