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
import edu.ifes.ci.si.les.scl.models.Produto;
import edu.ifes.ci.si.les.scl.models.ProdutosIngredientes;
import edu.ifes.ci.si.les.scl.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	public List<Produto> listAll() {
		return produtoRepository.findAll();
	}

	public Produto find(Integer id) {
		return produtoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Produto não existe"));
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW) // Esta notação tem objetivo de controlar a propagação da transação (garantindo que sejam realizadas todas as modificações no BD, ou nada)
	public Produto insert(Produto produto) {
		produto.setId(null);

		try {
			
			for (ProdutosIngredientes item : produto.getIngredientes()) {
				item.setProduto(produto);
			}
			
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Erro na inserção no Banco");
		}
		return produtoRepository.save(produto);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Produto update(Produto produto) {
		for (ProdutosIngredientes item : produto.getIngredientes()) {
			item.setProduto(produto);
		}
		return produtoRepository.save(produto);
	}

	public void delete(Integer id) {
		try {
			produtoRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ObjectNotFoundException("Produto não existe");
		}
	}
}
