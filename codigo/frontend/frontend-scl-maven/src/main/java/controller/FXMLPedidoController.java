package controller;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

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
import model.Usuario;
import service.ClienteService;
import service.FuncionarioService;
import service.IngredienteService;
import service.PedidoService;
import service.ProdutoService;

public class FXMLPedidoController implements Initializable {
	
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
	private Button btnDeletar;
	
	@FXML
	private Button btnAlterar;

	@FXML
	private Button btnInserir;
	
	
	private final PedidoService pedidoService = new PedidoService();
	private final ClienteService clienteService = new ClienteService();
	private final ProdutoService produtoService = new ProdutoService();
	private final IngredienteService ingredienteService = new IngredienteService();
	private final FuncionarioService funcionarioService = new  FuncionarioService();
	
	
	private ObservableList<Cliente> observableCliente;
	private List<Cliente> listaCliente;
	
	private ObservableList<Produto> observableProduto;
	private List<Produto> listaProdutos;
	

	private ObservableList<Ingrediente> observableIngredientes;
	private List<Ingrediente> listaIngredientes;
	
	private ObservableList<Produto> observableProdutosPedido;
	private List<Produto> listaProdutosPedido = new ArrayList<>();
	
	
	private Usuario usuarioAtual;
	
	
	private ObservableList<IngredienteAcrescimo> observableIngredienteAcrescimos;
	private ArrayList<IngredienteAcrescimo> acrescimosAdicionados = new ArrayList<>();
	
	private ArrayList<ItensPedido> itensPedidoSalvos = new ArrayList<>();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		carregarUsuarioAtual();
		
		carregarComboBoxClientes();
		carregarComboBoxProdutos();
		carregarComboBoxAcrescimos();
		carregarTableViewItensPedido();
		carregarTableViewAcrescimos();
		
		carregarDataPedido();
		
		
	}
	
	private void carregarUsuarioAtual() {
		this.usuarioAtual = funcionarioService.listAll().get(0);
	}
	
	private void carregarDataPedido() {
		LocalDate data = LocalDate.now();
		this.datePickerData.setValue(data);
	}
	
	private void carregarTableViewItensPedido() {
        
		tableColumnProduto.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tableColumnPrecoUnit.setCellValueFactory(new PropertyValueFactory<>("precoFinal"));
		
		if(listaProdutosPedido.size() != 0) {
			observableProdutosPedido = FXCollections.observableArrayList(listaProdutosPedido);
			
			tableItemsPedido.setItems(observableProdutosPedido);
			tableItemsPedido.refresh();
		}
		
    }
	
	/* consertar depois. Precisa retirar a dependencia de Pedido com entrega e Pagamento*/
	public void onClickBtnSalvar() {
		Cliente clientePedido = combBoxCliente.getValue();
		
		if(itensPedidoSalvos.size() != 0) {
			
			Pedido novoPedido = new Pedido(null, new Date(System.currentTimeMillis()), usuarioAtual, clientePedido);
			
			pedidoService.insert(novoPedido);
		}
		
		for(ItensPedido item : itensPedidoSalvos) {
			System.out.println("Item = " + item.getProduto().getNome());
		}
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
			
			acrescimosAdicionados.clear();
			carregarTableViewAcrescimos();
		}
		
		
	}
	
	public void onClickAdicionarAcresimo() {
		if(combBoxAcrescimo.getValue() != null && textFieldQtd.getText() != "") {
			acrescimosAdicionados.add(new IngredienteAcrescimo(combBoxAcrescimo.getValue(),Integer.parseInt(textFieldQtd.getText())));
			carregarTableViewAcrescimos();
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
		
		
//		if(acrescimosAdicionados.size() != 0) {
//			IngredienteAcrescimo aux = acrescimosAdicionados.get(acrescimosAdicionados.size() - 1);
//			//System.out.println(" Acrescimo = " + aux.nome + "\n Preco = " + aux.valor + "\n Qtd = " + aux.quantidade );
//			
//			
//			
//			observableIngredienteAcrescimos = FXCollections.observableArrayList(acrescimosAdicionados);
//			
//			tableAcrescimos.setItems(observableIngredienteAcrescimos);
//			tableAcrescimos.refresh();
//		}
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
		}
		//System.out.println("Combobox = " + combBoxCliente.getValue().getBairro().toString());
	}
	
	
		

}
