package edu.ifes.ci.si.les.scl.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.scl.exceptions.DataIntegrityException;
import edu.ifes.ci.si.les.scl.exceptions.ObjectNotFoundException;
import edu.ifes.ci.si.les.scl.models.Entrega;
import edu.ifes.ci.si.les.scl.repositories.EntregaRepository;

@Service
public class EntregaService {

	@Autowired
	private EntregaRepository entregaRepository;

	public List<Entrega> listAll() {
		return entregaRepository.findAll();
	}

	public Entrega find(Integer id) {
		return entregaRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Entrega não existe"));
	}

	public Entrega insert(Entrega entrega) {
		entrega.setId(null);
		return entregaRepository.save(entrega);
	}

	public Entrega update(Entrega entrega) {
		try {
			entregaRepository.save(entrega);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não foi possivel alterar o objeto Entrega");
		}
		return null;
	}

	public void delete(Integer id) {
		try {
			entregaRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não foi possivel Excluir o objeto Entrega");
		}
	}
}
