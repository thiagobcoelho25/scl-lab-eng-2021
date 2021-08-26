package edu.ifes.ci.si.les.scl.services;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.scl.exceptions.ObjectNotFoundException;
import edu.ifes.ci.si.les.scl.models.Estoque;
import edu.ifes.ci.si.les.scl.repositories.EstoqueRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EstoqueService {

	private EstoqueRepository estoqueRepository;
	
	public List<Estoque> listAll() {
		return estoqueRepository.findAll();
	}
	
	public Estoque find(Integer id) {
		return estoqueRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Estoque não existe"));
	}
	
}