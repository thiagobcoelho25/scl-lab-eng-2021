package edu.ifes.ci.si.les.scl.services;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.scl.model.Bairro;
import edu.ifes.ci.si.les.scl.model.enums.EntregavelStatus;
import edu.ifes.ci.si.les.scl.repositories.BairroRepository;

@Service
public class DBService {

	@Autowired
	private BairroRepository bairroRep;
	
	public void instantiateTestDatabase() throws ParseException{
		//Instancia objetos Bairro
		Bairro b1 = new Bairro(null, "BNH", 0.0, EntregavelStatus.sim);
		Bairro b2 = new Bairro(null, "Aeroporto", 5.0, EntregavelStatus.sim);
		
		bairroRep.saveAll(Arrays.asList(b1,b2));
	}
	
}
