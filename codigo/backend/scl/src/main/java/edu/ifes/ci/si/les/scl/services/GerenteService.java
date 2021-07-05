package edu.ifes.ci.si.les.scl.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.scl.exceptions.ObjectNotFoundException;
import edu.ifes.ci.si.les.scl.models.Gerente;
import edu.ifes.ci.si.les.scl.repositories.GerenteRepository;


@Service
public class GerenteService {
	
	@Autowired
	private GerenteRepository gerenteRepository;

	public List<Gerente> listAll(){
		return gerenteRepository.findAll();
	}

	public Gerente find(Integer id) {
		return gerenteRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Gerente não existe"));
	}
	
	public Gerente insert(Gerente gerente) {
		gerente.setId(null);
		return gerenteRepository.save(gerente);
	}
	
	public Gerente update(Gerente gerente) {
		find(gerente.getId()); 
		return gerenteRepository.save(gerente);
	}
	
	public void delete(Integer id) {
		try {
			gerenteRepository.deleteById(id);	
		}catch (DataIntegrityViolationException e) {
			throw new ObjectNotFoundException("Gerente não existe");
		}
	}
	
}
