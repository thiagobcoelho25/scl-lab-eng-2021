package controller;

import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValueBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import model.Acrescimos;
import model.Bairro;
import model.Cliente;
import model.Entrega;
import model.Funcionario;
import model.Ingrediente;
import model.IngredienteAcrescimo;
import model.ItensPedido;
import model.Pagamento;
import model.Pedido;
import model.Produto;
import model.ProdutosIngredientes;
import model.Usuario;
import service.ClienteService;
import service.FuncionarioService;
import service.GerenteService;
import service.IngredienteService;
import service.ItensPedidoService;
import service.PedidoService;
import service.ProdutoService;

public class FXMLPedidoController implements Initializable {
	
	@FXML
	private TableView<Pedido> tablePedidos;
	
	@FXML
	private TableColumn<Pedido, Integer> tableColumnPedidoID;
	
	@FXML
	private TableColumn<Pedido, String> tableColumnPedidoCliente;
	
	@FXML
	private TableColumn<Pedido, String> tableColumnPedidoData;
	
	
	@FXML
    private TextField textFielCodigo;

	@FXML
	private ComboBox<Cliente> combBoxCliente;
	
	@FXML
    private TextField textFieldEndereco;
	
	@FXML
	private ComboBox<Produto> combBoxProduto;
	
	@FXML
	private Button btnAdicionarProduto;
	
	@FXML
	private TableView<Produto> tableItemsPedido;
	
	@FXML
	private TableColumn<Produto, String> tableColumnProduto;
	
	@FXML
	private TableColumn<Produto, Double> tableColumnPrecoUnit;
	
	@FXML
	private ComboBox<Ingrediente> combBoxAcrescimo;
	
	@FXML
    private TextField textFieldQtd;
	
	@FXML
	private TableView<IngredienteAcrescimo> tableAcrescimos;
	
	@FXML
	private TableColumn<IngredienteAcrescimo, String> tableColumnAcrescimo;
	
	@FXML
	private TableColumn<IngredienteAcrescimo, Integer> tableColumnQtd;
	
	@FXML
	private TableColumn<IngredienteAcrescimo, Double> tableColumnPrecoUnitAcrescimo;
	
	@FXML
	private DatePicker datePickerData;
	
	@FXML
    private TextField textFieldFrete;
	
	@FXML
    private TextField textFieldValorTotal;
	
	@FXML
	private Button btnDeletar;
	
	@FXML
	private Button btnLimpar;

	@FXML
	private Button btnInserir;
	
	
	private final PedidoService pedidoService = new PedidoService();
	private final ClienteService clienteService = new ClienteService();
	private final ProdutoService produtoService = new ProdutoService();
	private final IngredienteService ingredienteService = new IngredienteService();
	private final FuncionarioService funcionarioService = new  FuncionarioService();
	private final ItensPedidoService itensPedidoService = new ItensPedidoService();
	
	/*eliminar depois*/
	private final GerenteService gerenteService = new GerenteService();
	
	
	private ObservableList<Pedido> observablePedido;
	private List<Pedido> listaPedido;
	
	private ObservableList<Cliente> observableCliente;
	private List<Cliente> listaCliente;
	
	private ObservableList<Produto> observableProduto;
	private List<Produto> listaProdutos;
	

	private ObservableList<Ingrediente> observableIngredientes;
	private List<Ingrediente> listaIngredientes;
	
	private ObservableList<Produto> observableProdutosPedido;
	private List<Produto> listaProdutosPedido = new ArrayList<>();
	
	
	private Usuario usuarioAtual;
	
	private Double total = 0.0;
	
	
	private ObservableList<IngredienteAcrescimo> observableIngredienteAcrescimos;
	private ArrayList<IngredienteAcrescimo> acrescimosAdicionados = new ArrayList<>();
	
	private ArrayList<ItensPedido> itensPedidoSalvos = new ArrayList<>();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		carregarUsuarioAtual();
		carregarTableViewPedidos();
		
		carregarComboBoxClientes();
		carregarComboBoxProdutos();
		carregarComboBoxAcrescimos();
		carregarTableViewItensPedido();
		carregarTableViewAcrescimos();
		
		carregarDataPedido();
		
		
	}
	
	private void carregarUsuarioAtual() {
		this.usuarioAtual = gerenteService.listAll().get(0);
	}
	
	private void carregarDataPedido() {
		LocalDate data = LocalDate.now();
		this.datePickerData.setValue(data);
	}
	
	private void carregarTableViewPedidos() {
		tableColumnPedidoID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnPedidoCliente.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCliente().getNome()));
        
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy hh:mm:ss");
        tableColumnPedidoData.setCellValueFactory(cellData -> new SimpleStringProperty(dateFormat.format(cellData.getValue().getData())));

        listaPedido = pedidoService.listAll();
        
        observablePedido = FXCollections.observableArrayList(listaPedido);
        tablePedidos.setItems(observablePedido);
        tablePedidos.refresh();
	}
	
	private void carregarTableViewItensPedido() {
        
		tableColumnProduto.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tableColumnPrecoUnit.setCellValueFactory(new PropertyValueFactory<>("precoFinal"));
		
		//if(listaProdutosPedido.size() != 0) {
			observableProdutosPedido = FXCollections.observableArrayList(listaProdutosPedido);
			
			tableItemsPedido.setItems(observableProdutosPedido);
			tableItemsPedido.refresh();
		//}
		
    }
	
	public void onClickBtnDeletar() {
		if(tablePedidos.getSelectionModel().getSelectedItem() != null) {
			Pedido p = tablePedidos.getSelectionModel().getSelectedItem();
			pedidoService.delete(p.getId());
			carregarTableViewPedidos();
		}
	}
	
	public void onClickBtnSalvar() {
		Cliente clientePedido = combBoxCliente.getValue();
		
		if(itensPedidoSalvos.size() != 0) {
			
			/* Adicionando pedido ao banco de dados */
			Pedido novoPedido = new Pedido(null, new Date(System.currentTimeMillis()), usuarioAtual, clientePedido, total);
			/* criar um find by object no service para pegar o id do produto inserido */
			pedidoService.insert(novoPedido);
			
			/* Pegando o ultimo pedido adicionado com ID */
			List<Pedido> lista = pedidoService.listAll();
			novoPedido = lista.get(lista.size()-1);
			System.out.println("Last peido = " + novoPedido.getId());
			
			/* Setando Pedido de cada ItensPedido do novo Pedido */
			for(ItensPedido item : itensPedidoSalvos) {
				System.out.println("Item = " + item.getProduto().getNome());
				item.setPedido(novoPedido);
				itensPedidoService.insert(item);
			}
		}
			
		/* Listando todos os pedidos ja salvos no banco */
		List<Pedido> teste = pedidoService.listAll();
		for (Pedido pedido : teste) {
			System.out.println("Pedido = " + pedido.getId() + " / " + pedido.getData());
		}
	
		/* teste quantidade acrescimos */
		for (ItensPedido item : itensPedidoSalvos) {
			for (ProdutosIngredientes ingrediente : item.getProduto().getIngredientes()) {
				System.out.println("Quantidade item ingrediente = " + ingrediente.getQuantidade());
				System.out.println("Quantidade item ingrediente ID = " + ingrediente.getId().getIngrediente().getId());
			}
			for (Acrescimos acrescimo : item.getAcrescimos()) {
				System.out.println("Quantidade item acrescimo = " + acrescimo.getQuantidade());
				System.out.println("Quantidade item acrescimo ingrediente ID = " + acrescimo.getIngrediente().getId());
			}
		}
		
		
		limparAllCampos();
		carregarTableViewPedidos();
		total = 0.0;
		
		
		
	}
	
	public void onClickBtnLimpar() {
		limparAllCampos();
		total = 0.0;
	}
	
	private void limparAllCampos() {
		combBoxAcrescimo.setValue(null);
		combBoxProduto.setValue(null);
		combBoxCliente.setValue(null);
		textFieldEndereco.clear();
		textFieldQtd.clear();
		textFieldFrete.clear();
		textFieldValorTotal.clear();
		
		listaProdutosPedido.clear();
		acrescimosAdicionados.clear();
		carregarTableViewItensPedido();
		
		
		System.out.println("Lista Produtos Pedido = " + listaProdutosPedido);
	}
	
	
	public void onClickAdicionarProduto() {
		if(combBoxProduto.getValue() != null) {
			listaProdutosPedido.add(combBoxProduto.getValue());
			ItensPedido novoItem = new ItensPedido();
			
			ArrayList<Acrescimos> acrescimos = new ArrayList<>();
			for(IngredienteAcrescimo acrescimo : acrescimosAdicionados) {
				acrescimos.add(new Acrescimos(novoItem, acrescimo.getIngrediente(), acrescimo.getQuantidade()));
			}
			
			novoItem.setAcrescimos(acrescimos);
			novoItem.setProduto(combBoxProduto.getValue());
			
			itensPedidoSalvos.add(novoItem);
			carregarTableViewItensPedido();
			
			// somando ao total pedido
			total += novoItem.getProduto().getPrecoFinal();
			// atualizando o total pedido
			textFieldValorTotal.setText(total.toString());
			
			acrescimosAdicionados.clear();
			carregarTableViewAcrescimos();
		}
		
		
	}
	
	public void onClickAdicionarAcresimo() {
		if(combBoxAcrescimo.getValue() != null && textFieldQtd.getText() != "") {
			acrescimosAdicionados.add(new IngredienteAcrescimo(combBoxAcrescimo.getValue(),Integer.parseInt(textFieldQtd.getText())));
			carregarTableViewAcrescimos();
			
			/* somando valor dos acrescimos */
			for (IngredienteAcrescimo ingredienteAcrescimo : acrescimosAdicionados) {
				System.out.println("Quantidade acrescimo = " + ingredienteAcrescimo.getQuantidade());
				total += (ingredienteAcrescimo.getValor() * ingredienteAcrescimo.getQuantidade());
			}
			
			// atualizando o total pedido
			textFieldValorTotal.setText(total.toString());
		}
	}
	
	private void carregarTableViewAcrescimos() {
		System.out.println("Tamanho acrescimo = " + acrescimosAdicionados.size());
		tableColumnAcrescimo.setCellValueFactory(new PropertyValueFactory<IngredienteAcrescimo,String>("nome"));
		tableColumnPrecoUnitAcrescimo.setCellValueFactory(new PropertyValueFactory<IngredienteAcrescimo, Double>("valor"));
		tableColumnQtd.setCellValueFactory(new PropertyValueFactory<IngredienteAcrescimo, Integer>("quantidade"));
		
		observableIngredienteAcrescimos = FXCollections.observableArrayList(acrescimosAdicionados);
		
		tableAcrescimos.setItems(observableIngredienteAcrescimos);
		tableAcrescimos.refresh();
		
    }
	
	private void carregarComboBoxClientes() {
		listaCliente = clienteService.listAll();
		observableCliente = FXCollections.observableArrayList(listaCliente);
		combBoxCliente.setItems(observableCliente);
	}
	
	private void carregarComboBoxProdutos() {
		listaProdutos = produtoService.listAll();
		observableProduto = FXCollections.observableArrayList(listaProdutos);
		combBoxProduto.setItems(observableProduto);
	}
	
	private void carregarComboBoxAcrescimos() {
		listaIngredientes = ingredienteService.listAll();
		observableIngredientes = FXCollections.observableArrayList(listaIngredientes);
		combBoxAcrescimo.setItems(observableIngredientes);
	}
	
	public void carregarBairroCliente() {
		if(combBoxCliente.getValue() != null) {
			textFieldEndereco.setText(combBoxCliente.getValue().getBairro().toString());
			textFieldFrete.setText(combBoxCliente.getValue().getBairro().getFrete().toString());
			
			Double frete = combBoxCliente.getValue().getBairro().getFrete();
			
			// somando ao total pedido
			total += frete;
			// atualizando o total pedido
			textFieldValorTotal.setText(total.toString());
		}
	}
	
	
		

}
