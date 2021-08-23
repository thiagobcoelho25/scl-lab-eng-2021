package edu.ifes.ci.si.les.scl.services;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import antlr.collections.List;
import edu.ifes.ci.si.les.scl.model.enums.EntregavelStatus;
import edu.ifes.ci.si.les.scl.model.enums.StatusEntrega;
import edu.ifes.ci.si.les.scl.model.enums.TipoFormaPagamento;
import edu.ifes.ci.si.les.scl.model.enums.TipoIngrediente;
import edu.ifes.ci.si.les.scl.models.Acrescimos;
import edu.ifes.ci.si.les.scl.models.Bairro;
import edu.ifes.ci.si.les.scl.models.Cliente;
import edu.ifes.ci.si.les.scl.models.Entrega;
import edu.ifes.ci.si.les.scl.models.Estoque;
import edu.ifes.ci.si.les.scl.models.Funcionario;
import edu.ifes.ci.si.les.scl.models.Gerente;
import edu.ifes.ci.si.les.scl.models.Ingrediente;
import edu.ifes.ci.si.les.scl.models.ItensPedido;
import edu.ifes.ci.si.les.scl.models.Pagamento;
import edu.ifes.ci.si.les.scl.models.Pedido;
import edu.ifes.ci.si.les.scl.models.Produto;
import edu.ifes.ci.si.les.scl.models.ProdutosIngredientes;
import edu.ifes.ci.si.les.scl.repositories.AcrescimosRepository;
import edu.ifes.ci.si.les.scl.repositories.BairroRepository;
import edu.ifes.ci.si.les.scl.repositories.ClienteRepository;
import edu.ifes.ci.si.les.scl.repositories.EntregaRepository;
import edu.ifes.ci.si.les.scl.repositories.EstoqueRepository;
import edu.ifes.ci.si.les.scl.repositories.FuncionarioRepository;
import edu.ifes.ci.si.les.scl.repositories.GerenteRepository;
import edu.ifes.ci.si.les.scl.repositories.IngredienteRepository;
import edu.ifes.ci.si.les.scl.repositories.ItensPedidoRepository;
import edu.ifes.ci.si.les.scl.repositories.PagamentoRepository;
import edu.ifes.ci.si.les.scl.repositories.PedidoRepository;
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
	private FuncionarioRepository funcionarioRepository;
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
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private EntregaRepository entregaRepository;
	
	public void instantiateTestDatabase() throws ParseException{
		
		Ingrediente ingred1 = new Ingrediente(null, "Carne de Boi", 10.0);
		Ingrediente ingred2 = new Ingrediente(null, "Batata", 5.0);
		Ingrediente ingred3 = new Ingrediente(null, "Frango Empanado", 8.0);
		Ingrediente ingred4 = new Ingrediente(null, "Macarrao", 5.0);
		
		Estoque estoq1 = new Estoque(null, 50,ingred1);
		Estoque estoq2 = new Estoque(null, 10,ingred2);
		
		
		//Instancia Bairro
		Bairro b1 = new Bairro(null, "BNH", 0.0, EntregavelStatus.nao);
		Bairro b2 = new Bairro(null, "Aeroporto", 5.0, EntregavelStatus.sim);
		Bairro b3 = new Bairro(null, "Gilberto Machado", 6.0, EntregavelStatus.sim);
		
		//Instancia Gerente
		Gerente g1 = new Gerente(null, "Israel", "rua de Muqui", 25, b2, "admin", "123");
		Funcionario func1 = new Funcionario(null, "Jhon", "Rua Joaquim Afonso", 7, b2, "admin", "123", "Atendente", 1500.0);
		Funcionario func2 = new Funcionario(null, "Gabriel", "Av F. Lacerda de Aguiar", 16, b3, "admin", "123", "Chapeiro", 1600.0);
		
		//Instancia Cliente
		Cliente c1 = new Cliente(null, "Thiago", "Rua sem nome", 5, "perto da fabrica C&M", b1);
		Cliente c2 = new Cliente(null, "Maria", "C. Dias Lopes", 10, "perto da rodoviaria", b3);
		Cliente c3 = new Cliente(null, "Thalis", "Rua do aeroporto", 24, "perto da marbrasa", b2);
		
		//Instancia Produto
		Produto p1 = new Produto(null, "Hamburgue", 25.0);
		Produto p2 = new Produto(null, "Fritas & Frango", 13.0);
		Produto p3 = new Produto(null, "Yakisoba", 13.0);
		
		//Instancia ProdutosIngredientes
		ProdutosIngredientes pi1 = new ProdutosIngredientes(p1, ingred1, 1, TipoIngrediente.principal);
		ProdutosIngredientes pi2 = new ProdutosIngredientes(p2, ingred2, 1, TipoIngrediente.principal);
		ProdutosIngredientes pi3 = new ProdutosIngredientes(p2, ingred3, 2, TipoIngrediente.secundario);
		ProdutosIngredientes pi4 = new ProdutosIngredientes(p3, ingred3, 2, TipoIngrediente.principal);
		ProdutosIngredientes pi5 = new ProdutosIngredientes(p3, ingred4, 2, TipoIngrediente.secundario);

		//Instancia Pedido
//		Pagamento pag1 = new Pagamento(null, 38.0, 0.0, TipoFormaPagamento.cartao);
//		Entrega entrega1 = new Entrega(null, LocalDateTime.now(), StatusEntrega.naoEntregue);
//		Pedido pd1 = new Pedido(null, new Date(), func1, c1, pag1, entrega1);
		Pedido pd1 = new Pedido(null, new Date(), func1, c1);
		Pagamento pag1 = new Pagamento(null, 38.0, 0.0, TipoFormaPagamento.cartao, pd1);
		ArrayList<Pedido> arr = new ArrayList<Pedido>();
		arr.add(pd1);
		Entrega entrega1 = new Entrega(null, LocalDateTime.now(), StatusEntrega.naoEntregue, arr);
		
		
//		
//		Pagamento pag2 = new Pagamento(null, 56.0, 0.0, TipoFormaPagamento.cartao);
//		Entrega entrega2 = new Entrega(null, LocalDateTime.now(), StatusEntrega.naoEntregue);
//		Pedido pd2 = new Pedido(null, new Date(), g1, c2, pag2, entrega1);
		Pedido pd2 = new Pedido(null, new Date(), g1, c2);
		Pagamento pag2 = new Pagamento(null, 56.0, 0.0, TipoFormaPagamento.cartao, pd2);
		Entrega entrega2 = new Entrega(null, LocalDateTime.now(), StatusEntrega.naoEntregue, Arrays.asList(pd2));
		
//		Pagamento pag3 = new Pagamento(null, 21.0, 3.0, TipoFormaPagamento.cartao);
//		Entrega entrega3 = new Entrega(null, LocalDateTime.now(), StatusEntrega.entregue);
//		Pedido pd3 = new Pedido(null, new Date(), func1, c3, pag3, entrega3);
		Pedido pd3 = new Pedido(null, new Date(), func1, c3);
		Pagamento pag3 = new Pagamento(null, 21.0, 3.0, TipoFormaPagamento.cartao, pd3);
		Entrega entrega3 = new Entrega(null, LocalDateTime.now(), StatusEntrega.entregue, Arrays.asList(pd3));
		
		//Instancia ItensPedido
		ItensPedido ip1 = new ItensPedido(null, p1, pd1);
		ItensPedido ip2 = new ItensPedido(null, p2, pd1);
		ItensPedido ip3 = new ItensPedido(null, p1, pd2);
		ItensPedido ip4 = new ItensPedido(null, p1, pd2);
		ItensPedido ip5 = new ItensPedido(null, p2, pd3);
		

		//Instancia Acrescimos
		Acrescimos ac1 = new Acrescimos(ip5, ingred2, 1);
		
		
		//------------------------------------------------------------------------------------------
		bairroRep.saveAll(Arrays.asList(b1,b2,b3));
		clienteRepository.saveAll(Arrays.asList(c1,c2,c3));
		gerenteRepository.save(g1);
		funcionarioRepository.saveAll(Arrays.asList(func1,func2));
		
		ingredienteRepository.saveAll(Arrays.asList(ingred1,ingred2,ingred3,ingred4));
		estoqueRepository.saveAll(Arrays.asList(estoq1,estoq2));
		//estoqueRepository.saveAll(Arrays.asList(estoq1,estoq2));
		//ingredienteRepository.saveAll(Arrays.asList(ingred1,ingred2,ingred3));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		produtosIngredientesRepository.saveAll(Arrays.asList(pi1,pi2,pi3,pi4,pi5));
//		pagamentoRepository.saveAll(Arrays.asList(pag1,pag2,pag3));
//		entregaRepository.saveAll(Arrays.asList(entrega1,entrega2,entrega3));
//		pedidoRepository.saveAll(Arrays.asList(pd1,pd2,pd3));

		pedidoRepository.saveAll(Arrays.asList(pd1,pd2,pd3));
		pagamentoRepository.saveAll(Arrays.asList(pag1,pag2,pag3));
		entregaRepository.saveAll(Arrays.asList(entrega1,entrega2,entrega3));
		
		itensPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3,ip4,ip5));
		acrescimosRepository.save(ac1);
	}
	
}
