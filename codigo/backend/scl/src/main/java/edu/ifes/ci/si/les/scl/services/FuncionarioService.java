package edu.ifes.ci.si.les.scl.services;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.ifes.ci.si.les.scl.exceptions.DataIntegrityException;
import edu.ifes.ci.si.les.scl.exceptions.ObjectNotFoundException;
import edu.ifes.ci.si.les.scl.models.Bairro;
import edu.ifes.ci.si.les.scl.models.Funcionario;
import edu.ifes.ci.si.les.scl.repositories.FuncionarioRepository;

@Service
public class FuncionarioService {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;

	public List<Funcionario> listAll(){
		return funcionarioRepository.findAll();
	}

	public Funcionario find(Integer id) {
		return funcionarioRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Funcionario não existe"));
	}
	
	public Funcionario insert(Funcionario funcionario) {
		funcionario.setId(null);
		return funcionarioRepository.save(funcionario);
	}
	
	public Funcionario update(Funcionario funcionario) {
		find(funcionario.getId()); 
		return funcionarioRepository.save(funcionario);
	}
	
	public void delete(Integer id) {
		try {
			funcionarioRepository.deleteById(id);	
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Funcionario não existe");
		}
	}
	
	public List<Funcionario> findByBairro(Integer bairroID) {
		return funcionarioRepository.findByBairroId(bairroID);
	}
	
}
