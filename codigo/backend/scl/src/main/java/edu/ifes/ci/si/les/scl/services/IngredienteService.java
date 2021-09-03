package edu.ifes.ci.si.les.scl.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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

	public List<Ingrediente> listAll() {
		return ingredienteRepository.findAll();
	}

	public Ingrediente find(Integer id) {
		return ingredienteRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Ingrediente não existe"));
	}

	public Ingrediente insert(Ingrediente ingrediente) {
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
			Ingrediente IngredienteFromDB = ingredienteRepository.findById(ingrediente.getId()).orElseThrow(() -> new ObjectNotFoundException("Ingrediente não existe"));
			Estoque estoqueFromDB = IngredienteFromDB.getEstoque();
			if (estoqueFromDB == null) {
				estoqueFromRequest.setIngrediente(ingrediente);
				ingrediente.setEstoque(estoqueRepository.save(estoqueFromRequest));
			} else {
				estoqueFromDB.setQuantidade(estoqueFromDB.getQuantidade() + estoqueFromRequest.getQuantidade());
				ingrediente.setEstoque(estoqueRepository.save(estoqueFromDB));
			}
			return ingrediente;
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não foi possivel Inserir o objeto Estoque em Ingrediente");
		} catch (IllegalArgumentException e) {
			throw new ObjectNotFoundException("Objeto Ingrediente não existe");
		}
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Ingrediente update(Ingrediente ingrediente) {

		try {
			ingredienteRepository.findById(ingrediente.getId()).orElseThrow(() -> new ObjectNotFoundException("Ingrediente não existe"));
			Estoque estoque = ingrediente.getEstoque();
			ingrediente.setEstoque(null);
			ingrediente = ingredienteRepository.save(ingrediente);
			ingrediente.setEstoque(estoque);
			return ingrediente;
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não foi possivel Atualizar o objeto Ingrediente");
		} catch (IllegalArgumentException e) {
			throw new ObjectNotFoundException("Objeto Ingrediente não existe");
		}
	}

	public void delete(Integer id) {
		try {
			ingredienteRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não foi possivel Excluir o objeto Ingrediente");
		} catch (EmptyResultDataAccessException e) {
			throw new ObjectNotFoundException("Objeto Ingrediente não existe");
		}
	}
	
	public List<Ingrediente> listagemIngredientePorQuantidadeMaximaNoEstoque(Integer quantidade){
		return ingredienteRepository.listOfIngredientesThatHaveEstoqueQuantidadeGreaterThan(quantidade);
	}

}
