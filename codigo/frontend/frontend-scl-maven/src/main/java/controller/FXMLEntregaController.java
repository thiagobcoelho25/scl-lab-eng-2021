package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Entrega;
import model.Pedido;
import model.enums.StatusEntrega;
import service.EntregaService;
import service.PedidoService;

public class FXMLEntregaController implements Initializable {

	@FXML
	private TableView<Entrega> tabbleViewEntregas;

	@FXML
	private TableColumn<Entrega, Integer> tabbleColumnEntregaId;

	@FXML
	private TableColumn<Entrega, String> tabbleColumnEntregaCliente;

	@FXML
	private TextField textFieldCodigo;

	@FXML
	private ComboBox<Pedido> comboboxPedido;

	@FXML
	private TableView<Pedido> tabbleViewDados;

	@FXML
	private TableColumn<Pedido, Integer> tabbleColumnPedidoId;

	@FXML
	private TableColumn<Pedido, String> tabbleColumnCliente;

	@FXML
	private TextField textFieldHoraSaida;

	@FXML
	private ComboBox<StatusEntrega> comboBoxStatus;

	@FXML
	private Button buttonConfirmar;

	@FXML
	private Button buttonAlterar;

	@FXML
	private Button buttonDeletar;

	@FXML
	private Button buttonAdicionar;

	private final PedidoService pedidoService = new PedidoService();
//	private final ClienteService clienteService = new ClienteService();
	private final EntregaService entregaService = new EntregaService();

//	private ObservableList<Cliente> observableListCliente;
//	private List<Cliente> listaCliente;

	private ObservableList<Pedido> observableListPedido;
	private List<Pedido> listaPedido;

	private ObservableList<StatusEntrega> observableListStatus;
	
	private ObservableList<Entrega> observableListEntrega;
	private List<Entrega> listaEntrega;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		carregarTableViewEntregas();
		carregarComboBoxPedido();
		carregarComboBoxStatus();
		
	}

	//Carrega  a tabela de entregas
	//nota: a intenção era colocar na tabela o id de uma entrega e o nome dos clientes relacionados aos pedidos que foram
	//naquela entrega. Porém, no banco de dados é criado um array de pedidos e não descobri como acessar os clientes associados.
	//Com esse erro não consegui implementar algumas funcionalidades como inserir e alterar.
	public void carregarTableViewEntregas() {
		
//		tabbleColumnEntregaId.setCellValueFactory(new PropertyValueFactory<>("id"));
	
//		tabbleColumnEntregaCliente.setCellValueFactory(
//			cellData -> SimpleStringProperty(cellData.getValue().getPedidos().getCliente().getNome()));
//
//		listaEntrega = entregaService.listAll();
//
//		System.out.println(listaEntrega);
//
//		observableListEntrega = FXCollections.observableArrayList(listaEntrega);
//		tabbleViewEntregas.setItems(observableListEntrega);
//		tabbleViewEntregas.refresh();
	}

	// Esse método mostra na tabela Dados o ID do pedido e nome do cliente relacionado àquele pedido
	public void carregarTableViewDados() {
		tabbleColumnPedidoId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tabbleColumnCliente.setCellValueFactory(new PropertyValueFactory<>("nome"));

		listaPedido = pedidoService.listAll();

		observableListPedido = FXCollections.observableArrayList(listaPedido);
		tabbleViewDados.setItems(observableListPedido);
		tabbleViewDados.refresh();

	}
	
	// Carrega a tabela dados após selecionar um item na tabela da esquerda
	public void carregarTableViewDados(Entrega entrega) {
		listaPedido = (List<Pedido>) entrega.getPedidos();
											
		observableListPedido = FXCollections.observableArrayList(listaPedido);
		tabbleViewDados.setItems(observableListPedido);
		tabbleViewDados.refresh();
		
	}
	
    // Carrega o combobox com os pedidos para inserção de uma entrega 
	public void carregarComboBoxPedido() {
		listaPedido = pedidoService.listAll();
		observableListPedido = FXCollections.observableArrayList(listaPedido);
		comboboxPedido.setItems(observableListPedido);
	}
	// Carrega o combobox com os status de uma entrega para inserção 
	public void carregarComboBoxStatus() {
		observableListStatus = FXCollections.observableArrayList(Arrays.asList(StatusEntrega.entregue, StatusEntrega.naoEntregue));
		comboBoxStatus.setItems(observableListStatus);
	}

	// Adiciona um pedido selecionado no combobox para a tabela Dados
	public void handleButtonAdicionar() {
		if (comboboxPedido != null) {
			listaPedido.add(comboboxPedido.getValue());
			carregarTableViewDados();
		}
	}

	// Carrega os campos da tela Entrega com os dados da tabela da esquerda
	public void selecionarItemTableViewEntrega(Entrega entrega) {
		if (entrega != null) {
			
			textFieldCodigo.setText(entrega.getId().toString());
			textFieldHoraSaida.setText(entrega.getHoraSaida().toString());
			carregarTableViewDados(entrega);
			comboBoxStatus.setValue(entrega.getStatus());

		} else {
			textFieldCodigo.setText("");
		}
	}

	public void handleButtonInserir() {
		
	}

	public void handleButtonAlterar() {

	}

	@FXML
	public void handleButtonDeletar() throws IOException {
		Entrega	entrega = tabbleViewEntregas.getSelectionModel().getSelectedItem();
		if (entrega != null) {
			String resultado = entregaService.delete(entrega.getId());
			exibirMensagemErro(resultado);
			
			carregarTableViewEntregas();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("Por favor, escolha uma entrega na Tabela!");
			alert.show();
		}
		
	}
	
	private boolean verificarCamposPreenchidos() {
		int countPreenchidos = 0;
		if(comboboxPedido.getSelectionModel().getSelectedItem() != null) countPreenchidos++;
		if(textFieldHoraSaida.getText() != null) countPreenchidos++;
		if(comboBoxStatus.getSelectionModel().getSelectedItem() != null) countPreenchidos++;
		if(countPreenchidos == 3) {return true;};
		return false;
	}
	

	public void exibirMensagemErro(String resultado) {
		if (!resultado.equals("")) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText(resultado);
			alert.show();
		}
	}

}
