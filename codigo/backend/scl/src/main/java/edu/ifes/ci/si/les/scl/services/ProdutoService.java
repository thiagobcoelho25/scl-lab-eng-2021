package edu.ifes.ci.si.les.scl.services;

import java.util.Collection;
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
	
	public List<Produto> listAllDisponiveis() {
		return (List<Produto>) produtoRepository.findAllDisponiveis();
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
			return produtoRepository.save(produto);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Erro na inserção no Banco");
		}
	}
	
	//Resolução do Problema no StackOverFlow: https://stackoverflow.com/questions/5587482/hibernate-a-collection-with-cascade-all-delete-orphan-was-no-longer-referenc
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Produto update(Produto produto) {
		
		try {
			produtoRepository.findById(produto.getId()).orElseThrow(() -> new ObjectNotFoundException("Produto não existe"));
			for (ProdutosIngredientes item : produto.getIngredientes()) {
				item.setProduto(produto);
			}
			
			Produto newProd = produtoRepository.findById(produto.getId()).get();
			newProd.getIngredientes().clear();
			newProd.getIngredientes().addAll(produto.getIngredientes());
			newProd.setPrecoFinal(produto.getPrecoFinal());
			
			return produtoRepository.save(newProd);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Produto não pode ser alterado");
		} catch (IllegalArgumentException e) {
			throw new ObjectNotFoundException("Produto não existe");
		}
	}

	public void delete(Integer id) {
		try {
			produtoRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Produto não pode ser deletado");
		} catch (EmptyResultDataAccessException e) {
			throw new ObjectNotFoundException("Produto não existe");
		}
	}
	
	public Collection<Produto> findAllProdutosPrecoMaiorQue(Double valorMinimo){
		return produtoRepository.findAllProdutosPrecoMaiorQue(valorMinimo);
	}
}
