package edu.ifes.ci.si.les.scl.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.scl.exceptions.DataIntegrityException;
import edu.ifes.ci.si.les.scl.exceptions.ObjectNotFoundException;
import edu.ifes.ci.si.les.scl.models.Bairro;
import edu.ifes.ci.si.les.scl.repositories.BairroRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BairroService {

    @Autowired
    private BairroRepository bairroRepository;

    public List<Bairro> findAll(){
        return bairroRepository.findAll();

    }

    public Bairro findById(Integer id){
        return bairroRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto Bairro Nao encontrado com id = " + id));
    }

    public Bairro insert(Bairro bairro){
        bairro.setId(null);
        try {
            return bairroRepository.save(bairro);
        }catch (DataIntegrityException e){
            throw new DataIntegrityException("Campos obrigatórios do Bairro nao foram preenchidos: ");
        }
    }

    public Bairro update(Bairro bairro){
        try {
            return bairroRepository.save(bairro);
        }catch(DataIntegrityException e){
            throw new DataIntegrityException("Campos obrigatórios do Bairro nao foram preenchidos: ");
        }

    }

    public void delete(Integer id){
        if(bairroRepository.findById(id) != null) {
            try {
                bairroRepository.deleteById(id);
            } catch (DataIntegrityException e){
                throw new DataIntegrityException("Não foi possivel Excluir o objeto Bairro");
            }
        }
    }



}
