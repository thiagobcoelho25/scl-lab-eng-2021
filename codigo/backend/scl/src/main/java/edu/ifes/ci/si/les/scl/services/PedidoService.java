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
		Pedido retorno = pedidoRepository.save(pedido);
		try {
			
			for (ItensPedido item : pedido.getItensPedido()) {
				item.setPedido(retorno);
				itensPedidoService.insert(item);
			}
			
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Erro na inserção no Banco");
		}
		return retorno;
	}
	
	public Pedido update(Pedido pedido) {
		find(pedido.getId()); 
		return pedidoRepository.save(pedido);
	}
	
	public void delete(Integer id) {
		try {
			Pedido p = find(id);
			List<ItensPedido> itensRemovidos = itensPedidoService.findByPedido(p);
			for (ItensPedido itensPedido : itensRemovidos) {
				itensPedidoService.delete(itensPedido.getId());
			}
			pedidoRepository.deleteById(id);	
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Pedido não existe");
		}
	}

}
