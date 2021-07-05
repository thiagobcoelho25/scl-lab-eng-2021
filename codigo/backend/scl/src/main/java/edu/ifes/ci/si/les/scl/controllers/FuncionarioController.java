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

import edu.ifes.ci.si.les.scl.models.Funcionario;
import edu.ifes.ci.si.les.scl.services.FuncionarioService;


@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@GetMapping
	public ResponseEntity<List <Funcionario>> ListAll(){
		return ResponseEntity.ok().body(funcionarioService.listAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Funcionario> find(@PathVariable Integer id){
		return ResponseEntity.ok().body(funcionarioService.find(id));
	}
	
	@PostMapping
	public ResponseEntity<Funcionario> insert(@Valid @RequestBody Funcionario funcionario){
		return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioService.insert(funcionario));
	}
	
	@PutMapping
	public ResponseEntity<Funcionario> update(@Valid @RequestBody Funcionario funcionario){
		return ResponseEntity.ok().body(funcionarioService.update(funcionario));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		funcionarioService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
