package edu.ifes.ci.si.les.scl.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.scl.exceptions.ObjectNotFoundException;
import edu.ifes.ci.si.les.scl.models.Cliente;
import edu.ifes.ci.si.les.scl.repositories.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;

	public List<Cliente> listAll(){
		return clienteRepository.findAll();
	}

	public Cliente find(Integer id) {
		return clienteRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Cliente não existe"));
	}
	
	public Cliente insert(Cliente cliente) {
		cliente.setId(null);
		return clienteRepository.save(cliente);
	}
	
	
	
}
