package edu.ifes.ci.si.les.scl.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.ifes.ci.si.les.scl.exceptions.DataIntegrityException;
import edu.ifes.ci.si.les.scl.exceptions.ObjectNotFoundException;
import edu.ifes.ci.si.les.scl.models.Acrescimos;
import edu.ifes.ci.si.les.scl.models.Ingrediente;
import edu.ifes.ci.si.les.scl.models.ItensPedido;
import edu.ifes.ci.si.les.scl.models.Pedido;
import edu.ifes.ci.si.les.scl.models.Produto;
import edu.ifes.ci.si.les.scl.models.ProdutosIngredientes;
import edu.ifes.ci.si.les.scl.repositories.ItensPedidoRepository;

@Service
public class ItensPedidoService {
	
	@Autowired
	private ItensPedidoRepository itensPedidoRepository;
	
	
	
	public List<ItensPedido> listAll(){
		return itensPedidoRepository.findAll();
	}

	public ItensPedido find(Integer id) {
		return itensPedidoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Item Pedido não existe"));
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public ItensPedido insert(ItensPedido itensPedido) {
		
//		Pedido pedido = itensPedido.getPedido();
//		Produto produto = itensPedido.getProduto();
//		
//		if(pedido.getId() == null || produto.getId() == null) {
//			throw new DataIntegrityException("Não foi possivel Inserir o objeto Item pedido");
//		}
		

		itensPedido.setId(null);
		try {
			for (Acrescimos acrescimo : itensPedido.getAcrescimos()) {
				acrescimo.setItensPedido(itensPedido);
			}
			//itensPedido.setPedido(pedido);
			//itensPedido.setProduto(produto);
			
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não foi possivel Inserir o objeto Item pedido");
		}
		return itensPedidoRepository.save(itensPedido);
		
		
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public ItensPedido update(ItensPedido itensPedido) {
		
		ItensPedido itensFromBD = itensPedidoRepository.findById(itensPedido.getId()).get();
		itensFromBD.getAcrescimos().clear();
		itensFromBD.getAcrescimos().addAll(itensPedido.getAcrescimos());
		
//		
//		find(itensPedido.getId());
//		Pedido pedido = itensPedido.getPedido();
//		Produto produto = itensPedido.getProduto();
//		
//		if(pedido.getId() == null || produto.getId() == null) {
//			throw new DataIntegrityException("Não foi possivel Atualizar o objeto Item pedido");
//		}
//		try {
//			//itensPedido.setPedido(pedidoRepository.getById(pedido.getId()));
//			//itensPedido.setProduto(produtoRepository.getById(produto.getId()));
//			return itensPedidoRepository.save(itensPedido);
//		} catch (DataIntegrityViolationException e) {
//			throw new DataIntegrityException("Não foi possivel Atualizar o objeto Item pedido");
//		}
		
		return itensPedidoRepository.save(itensFromBD);
	}
	
	public void delete(Integer id) {
		try {
			itensPedidoRepository.deleteById(id);	
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Item Pedido não existe");
		}
	}
	
	public List<ItensPedido> findByPedido(Pedido pedido){
		return (List<ItensPedido>) itensPedidoRepository.findByPedido(pedido);
	}

}
