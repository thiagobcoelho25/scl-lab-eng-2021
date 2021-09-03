package edu.ifes.ci.si.les.scl.controllers;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.ifes.ci.si.les.scl.models.Bairro;
import edu.ifes.ci.si.les.scl.services.BairroService;

@RestController
@RequestMapping(value = "/bairros")
public class BairroController {

    @Autowired
    private BairroService bairroService;

    @GetMapping
    public ResponseEntity<List<Bairro>> findAll(){
        return ResponseEntity.ok().body(bairroService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Bairro> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(bairroService.findById(id));
    }
    
    @GetMapping(value = "/relatorios")
    public ResponseEntity<List<Bairro>> findByEntregavel(@RequestParam String entregavel){
        return ResponseEntity.ok().body(bairroService.findByEntregavel(entregavel));
    }

    @PostMapping()
    public ResponseEntity<Bairro> insert(@Valid @RequestBody Bairro bairro){
        return ResponseEntity.ok().body(bairroService.insert(bairro));
    }

    @PutMapping()
    public ResponseEntity<Bairro> update(@Valid @RequestBody Bairro bairro){
    	System.out.println(bairro);
        return ResponseEntity.ok().body(bairroService.update(bairro));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        bairroService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
