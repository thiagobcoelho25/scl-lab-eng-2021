package edu.ifes.ci.si.les.scl.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.ifes.ci.si.les.scl.exceptions.DataIntegrityException;
import edu.ifes.ci.si.les.scl.exceptions.ObjectNotFoundException;
import edu.ifes.ci.si.les.scl.models.Estoque;
import edu.ifes.ci.si.les.scl.models.Ingrediente;
import edu.ifes.ci.si.les.scl.repositories.EstoqueRepository;
import edu.ifes.ci.si.les.scl.repositories.IngredienteRepository;

@Service
public class IngredienteService {
	
	@Autowired
	private IngredienteRepository ingredienteRepository;
	
	@Autowired
	private EstoqueRepository estoqueRepository;

	public List<Ingrediente> listAll(){
		return ingredienteRepository.findAll();
	}

	public Ingrediente find(Integer id) {
		return ingredienteRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Ingrediente não existe"));
	}
	
	public Ingrediente insert(Ingrediente ingrediente){
		try {
			ingrediente.setId(null);
			ingrediente.setEstoque(null);
			return ingredienteRepository.save(ingrediente);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não foi possivel Inserir o objeto Ingrediente");
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Ingrediente insertEstoqueIngrediente(Ingrediente ingrediente) {
		Estoque estoqueFromRequest = ingrediente.getEstoque();
		
		try {
			if(estoqueFromRequest.getId() == null) {
				estoqueFromRequest.setIngrediente(ingrediente);
				ingrediente.setEstoque(estoqueRepository.save(estoqueFromRequest));
			}else {
				Estoque estoqueFromDB = ingredienteRepository.findById(ingrediente.getId()).get().getEstoque();
				estoqueFromDB.setQuantidade(estoqueFromDB.getQuantidade() + estoqueFromRequest.getQuantidade());
				ingrediente.setEstoque(estoqueRepository.save(estoqueFromDB));
			}
			return ingrediente;
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não foi possivel Inserir o objeto Estoque em Ingrediente");
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Ingrediente update(Ingrediente ingrediente) {
		
		try {
			Estoque estoque = ingrediente.getEstoque();
			ingrediente.setEstoque(null);
			ingrediente = ingredienteRepository.save(ingrediente);
			ingrediente.setEstoque(estoque);
			return ingrediente;
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não foi possivel Atualizar o objeto Ingrediente");
		}
	}
	
	public void delete(Integer id) {
		try {
			ingredienteRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não foi possivel Excluir o objeto Ingrediente");
		}
	}
	
}
