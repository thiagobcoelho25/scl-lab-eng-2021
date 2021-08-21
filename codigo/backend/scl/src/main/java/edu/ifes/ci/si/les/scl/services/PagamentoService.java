package edu.ifes.ci.si.les.scl.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.scl.exceptions.DataIntegrityException;
import edu.ifes.ci.si.les.scl.exceptions.ObjectNotFoundException;
import edu.ifes.ci.si.les.scl.models.Pagamento;
import edu.ifes.ci.si.les.scl.repositories.PagamentoRepository;

@Service
public class PagamentoService {

	@Autowired
	private PagamentoRepository pagamentoRepository;

	public List<Pagamento> listAll(){
		return pagamentoRepository.findAll();
	}

	public Pagamento find(Integer id) {
		return pagamentoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Pagamento não existe"));
	}
	
	public Pagamento insert(Pagamento pagamento) {
		pagamento.setId(null);
		return pagamentoRepository.save(pagamento);
	}
	
	public Pagamento update(Pagamento pagamento) {
		find(pagamento.getId()); 
		return pagamentoRepository.save(pagamento);
	}
	
	public void delete(Integer id) {
		try {
			pagamentoRepository.deleteById(id);	
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Pagamento não existe");
		}
	}
}
