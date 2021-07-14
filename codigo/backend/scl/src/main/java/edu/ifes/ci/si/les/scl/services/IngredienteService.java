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
		ingrediente.setId(null);
		ingrediente.setEstoque(null);
		return ingredienteRepository.save(ingrediente);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Ingrediente insertEstoqueIngrediente(Ingrediente ingrediente) {
		Estoque estoqueFromRequest = ingrediente.getEstoque();
		
		if(estoqueFromRequest.getId() == null) {
			estoqueFromRequest.setIngrediente(ingrediente);
			ingrediente.setEstoque(estoqueRepository.save(estoqueFromRequest));
		}else {
			Estoque estoqueFromDB = ingredienteRepository.findById(ingrediente.getId()).get().getEstoque();
			estoqueFromDB.setQuantidade(estoqueFromDB.getQuantidade() + estoqueFromRequest.getQuantidade());
			ingrediente.setEstoque(estoqueRepository.save(estoqueFromDB));
		}
		return ingrediente;
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Ingrediente update(Ingrediente ingrediente) {
		if(ingrediente.getEstoque() != null) {
			Estoque estoque = estoqueRepository.getById(ingrediente.getEstoque().getId());
			estoque.setIngrediente(ingrediente);
			ingrediente.setEstoque(estoque);
		} else {
			ingrediente.setEstoque(null);
		}
		return ingredienteRepository.save(ingrediente);
	}
	
	public void delete(Integer id) {
		try {
			ingredienteRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não foi possivel Excluir o objeto Ingrediente");
		}
	}
	
}
