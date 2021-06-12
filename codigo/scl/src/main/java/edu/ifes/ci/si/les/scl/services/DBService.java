package edu.ifes.ci.si.les.scl.services;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;

import edu.ifes.ci.si.les.scl.model.*;
import edu.ifes.ci.si.les.scl.model.enums.StatusEntrega;
import edu.ifes.ci.si.les.scl.model.enums.TipoFormaPagamento;
import edu.ifes.ci.si.les.scl.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.scl.model.enums.EntregavelStatus;
import edu.ifes.ci.si.les.scl.model.enums.TipoIngrediente;

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
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private ItensPedidoRepository itensPedidoRepository;
	@Autowired
	private AcrescimosRepository acrescimosRepository;
//	@Autowired
//	private PagamentoRepository pagamentoRepository;
//	@Autowired
//	private EntregaRepository entregaRepository;
	
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

		//Instancia Pedido
//		Pagamento pag1 = new Pagamento(null, 100.0, 0.0, TipoFormaPagamento.cartao);
//		Entrega entrega1 = new Entrega(null, LocalDateTime.now(), StatusEntrega.naoEntregue);
		Pedido pd1 = new Pedido(null, new Date(), c1);

		//Instancia ItensPedido
		ItensPedido ip1 = new ItensPedido(null, p1, pd1);

		//Instancia Acrescimos
		Acrescimos ac1 = new Acrescimos(ip1, ingred1, 2);

		
		//------------------------------------------------------------------------------------------
		bairroRep.saveAll(Arrays.asList(b1,b2));
		clienteRepository.save(c1);
		gerenteRepository.save(g1);
		estoqueRepository.save(estoq1);
		ingredienteRepository.saveAll(Arrays.asList(ingred1,ingred2));
		produtoRepository.saveAll(Arrays.asList(p1,p2));
		produtosIngredientesRepository.saveAll(Arrays.asList(pi1,pi2,pi3));
//		entregaRepository.save(entrega1);
//		pagamentoRepository.save(pag1);
		pedidoRepository.save(pd1);
		itensPedidoRepository.save(ip1);
		acrescimosRepository.save(ac1);
	}
	
}
