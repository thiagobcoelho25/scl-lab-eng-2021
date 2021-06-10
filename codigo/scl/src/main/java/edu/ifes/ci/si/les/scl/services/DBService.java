package edu.ifes.ci.si.les.scl.services;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.scl.model.Bairro;
import edu.ifes.ci.si.les.scl.model.Cliente;
import edu.ifes.ci.si.les.scl.model.Gerente;
import edu.ifes.ci.si.les.scl.model.enums.EntregavelStatus;
import edu.ifes.ci.si.les.scl.repositories.BairroRepository;
import edu.ifes.ci.si.les.scl.repositories.ClienteRepository;
import edu.ifes.ci.si.les.scl.repositories.GerenteRepository;

@Service
public class DBService {

	@Autowired
	private BairroRepository bairroRep;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private GerenteRepository gerenteRepository;
	
	public void instantiateTestDatabase() throws ParseException{
		//Instancia Bairro
		Bairro b1 = new Bairro(null, "BNH", 0.0, EntregavelStatus.nao);
		Bairro b2 = new Bairro(null, "Aeroporto", 5.0, EntregavelStatus.sim);
		
		//Instancia Gerente
		Gerente g1 = new Gerente(null, "Israel", "rua de Muqui", 25, b2, "admin", "123");
		
		//Instancia Cliente
		Cliente c1 = new Cliente(null, "Thiago", "Rua sem nome", 5, "perto da fabrica C&M", b1);
		//------------------------------------------------------------------------------------------
		bairroRep.saveAll(Arrays.asList(b1,b2));
		clienteRepository.save(c1);
		gerenteRepository.save(g1);
	}
	
}
