package edu.ifes.ci.si.les.scl.services;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.scl.model.Bairro;
import edu.ifes.ci.si.les.scl.model.Cliente;
import edu.ifes.ci.si.les.scl.model.Estoque;
import edu.ifes.ci.si.les.scl.model.Gerente;
import edu.ifes.ci.si.les.scl.model.Ingrediente;
import edu.ifes.ci.si.les.scl.model.Produto;
import edu.ifes.ci.si.les.scl.model.ProdutosIngredientes;
import edu.ifes.ci.si.les.scl.model.enums.EntregavelStatus;
import edu.ifes.ci.si.les.scl.model.enums.TipoIngrediente;
import edu.ifes.ci.si.les.scl.repositories.BairroRepository;
import edu.ifes.ci.si.les.scl.repositories.ClienteRepository;
import edu.ifes.ci.si.les.scl.repositories.EstoqueRepository;
import edu.ifes.ci.si.les.scl.repositories.GerenteRepository;
import edu.ifes.ci.si.les.scl.repositories.IngredienteRepository;
import edu.ifes.ci.si.les.scl.repositories.ProdutoRepository;
import edu.ifes.ci.si.les.scl.repositories.ProdutosIngredientesRepository;

@Service
public class DBService {

	@Autowired
	private BairroRepository bairroRep;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private GerenteRepository gerenteRepository;
	@Autowired
	private IngredienteRepository ingredienteRepository;
	@Autowired
	private EstoqueRepository estoqueRepository;
	@Autowired
	private ProdutosIngredientesRepository produtosIngredientesRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public void instantiateTestDatabase() throws ParseException{
		//Instancia Estoque
		Estoque estoq1 = new Estoque(null, 50);
		
		//Instancia Ingrediente
		Ingrediente ingred1 = new Ingrediente(null, "Batata", 10.0, null);
		Ingrediente ingred2 = new Ingrediente(null, "Carne de Boi", 30.0, estoq1);
		
		//Instancia Bairro
		Bairro b1 = new Bairro(null, "BNH", 0.0, EntregavelStatus.nao);
		Bairro b2 = new Bairro(null, "Aeroporto", 5.0, EntregavelStatus.sim);
		
		//Instancia Gerente
		Gerente g1 = new Gerente(null, "Israel", "rua de Muqui", 25, b2, "admin", "123");
		
		//Instancia Cliente
		Cliente c1 = new Cliente(null, "Thiago", "Rua sem nome", 5, "perto da fabrica C&M", b1);
		
		//Instancia Produto
		Produto p1 = new Produto(null, "Hamburgue", 55.0);
		Produto p2 = new Produto(null, "macarrao", 60.0);
		
		//Instancia ProdutosIngredientes
		ProdutosIngredientes pi1 = new ProdutosIngredientes(p1, ingred1, 1, TipoIngrediente.secundario);
		ProdutosIngredientes pi2 = new ProdutosIngredientes(p1, ingred2, 1, TipoIngrediente.principal);
		ProdutosIngredientes pi3 = new ProdutosIngredientes(p2, ingred1, 2, TipoIngrediente.principal);
		
		//------------------------------------------------------------------------------------------
		bairroRep.saveAll(Arrays.asList(b1,b2));
		clienteRepository.save(c1);
		gerenteRepository.save(g1);
		estoqueRepository.save(estoq1);
		ingredienteRepository.saveAll(Arrays.asList(ingred1,ingred2));
		produtoRepository.saveAll(Arrays.asList(p1,p2));
		produtosIngredientesRepository.saveAll(Arrays.asList(pi1,pi2,pi3));
	}
	
}
