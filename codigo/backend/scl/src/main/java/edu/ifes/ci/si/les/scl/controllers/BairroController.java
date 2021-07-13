package edu.ifes.ci.si.les.scl.controllers;

import edu.ifes.ci.si.les.scl.exceptions.ConstraintException;
import edu.ifes.ci.si.les.scl.models.Bairro;
import edu.ifes.ci.si.les.scl.services.BairroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @PostMapping()
    public ResponseEntity<Bairro> insert(@Valid @RequestBody Bairro bairro){
        return ResponseEntity.ok().body(bairroService.insert(bairro));
    }

    @PutMapping()
    public ResponseEntity<Bairro> update(@Valid @RequestBody Bairro bairro, BindingResult br){
        if(br.hasErrors()){
            throw new ConstraintException((br.getAllErrors().get(0).toString()));
        }
        return ResponseEntity.ok().body(bairroService.update(bairro));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        bairroService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
