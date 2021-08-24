package edu.ifes.ci.si.les.scl.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import edu.ifes.ci.si.les.scl.exceptions.DataIntegrityException;
import edu.ifes.ci.si.les.scl.exceptions.ObjectNotFoundException;
import edu.ifes.ci.si.les.scl.models.ItensPedido;
import edu.ifes.ci.si.les.scl.models.Pedido;
import edu.ifes.ci.si.les.scl.models.ProdutosIngredientes;
import edu.ifes.ci.si.les.scl.repositories.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ItensPedidoService itensPedidoService;

	public List<Pedido> listAll(){
		return pedidoRepository.findAll();
	}

	public Pedido find(Integer id) {
		return pedidoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Pedido não existe"));
	}
	
	
	public Pedido insert(Pedido pedido) {
		pedido.setId(null);
		try {
			
			for (ItensPedido item : pedido.getItensPedido()) {
				item.setPedido(pedido);
				itensPedidoService.insert(item);
			}
			
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Erro na inserção no Banco");
		}
		return pedidoRepository.save(pedido);
	}
	
	public Pedido update(Pedido pedido) {
		find(pedido.getId()); 
		return pedidoRepository.save(pedido);
	}
	
	public void delete(Integer id) {
		try {
			pedidoRepository.deleteById(id);	
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Pedido não existe");
		}
	}

}
