package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Cliente;
import model.Funcionario;
import model.Pagamento;
import model.Pedido;
import model.enums.PagamentoStatus;
import model.enums.TipoFormaPagamento;
import service.ClienteService;
import service.PagamentoService;
import service.PedidoService;

public class FXMLPagamentoController implements Initializable {
	@FXML
	private TableView<Pagamento> tableViewPagamento;

	@FXML
	private TableColumn<Pagamento, Integer> tableColumnCodigo;

	@FXML
	private TableColumn<Pagamento, String> tableColumnCliente;

	@FXML
	private TextField textFieldCodigo;

	@FXML
	private ComboBox<Cliente> comboBoxCliente;

	@FXML
	private ComboBox<Pedido> comboBoxPedido;

	@FXML
	private TextField textfieldDesconto;

	@FXML
	private TextField textFieldValor;

	@FXML
	private ComboBox<TipoFormaPagamento> comboBoxFormaDePagamento;

	@FXML
	private CheckBox checkbox;

	@FXML
	private Button buttonConfirmar;

	@FXML
	private Button buttonAlterar;

	@FXML
	private Button buttonDeletar;

	private final PagamentoService pagamentoService = new PagamentoService();
	private final ClienteService clienteService = new ClienteService();
	private final PedidoService pedidoService = new PedidoService();
	
	private List<Pedido> listPedido;
	private ObservableList<Pedido> observableListPedido;
	
	private List<Cliente> listCliente;
	private ObservableList<Cliente> observableListCliente;

	private List<Pagamento> listPagamentos;
	private ObservableList<Pagamento> observableListPagamento;
	
	private ObservableList<TipoFormaPagamento> observableListFormaPagamento;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		textfieldDesconto.setEditable(false);
		textfieldDesconto.setText("0");
		carregarTableViewPagamento();
		carregarComboBoxCliente();
		carregarComboBoxFormaDePagamento();
		
		tableViewPagamento.getSelectionModel().selectedItemProperty().addListener(
	               (observable, oldValue, newValue) -> selecionarItemTableViewPagamento(newValue));
		
		comboBoxCliente.getSelectionModel().selectedItemProperty()
		.addListener((observable, oldValue, newValue) -> carregarComboBoxPedido());
	}

	public void carregarComboBoxFormaDePagamento(){
		observableListFormaPagamento = FXCollections.observableArrayList(Arrays.asList(TipoFormaPagamento.cartao, TipoFormaPagamento.dinheiro, TipoFormaPagamento.pix, TipoFormaPagamento.picpay));
		comboBoxFormaDePagamento.setItems(observableListFormaPagamento);
	}
	
	public void carregarComboBoxCliente() {
		listCliente = clienteService.listAll();
		observableListCliente = FXCollections.observableArrayList(listCliente);
		comboBoxCliente.setItems(observableListCliente);
	}
	
	public void carregarComboBoxPedido() {
		if(!comboBoxCliente.getSelectionModel().isEmpty()) {
			Cliente cliente = comboBoxCliente.getValue();
			
			
		listPedido = pedidoService.listAll().stream().filter(c -> c.getCliente().getId() == cliente.getId()).collect(Collectors.toList());
		
		
		if(listPedido.size() > 25) {
			textfieldDesconto.setEditable(true);
		}
		
		observableListPedido = FXCollections.observableArrayList(listPedido);
		comboBoxPedido.setItems(observableListPedido);
		}
	}
	
	public void selecionarItemTableViewPagamento(Pagamento pagamento) {
		if(pagamento != null) {
			textFieldCodigo.setText(pagamento.getId().toString());
			comboBoxCliente.getSelectionModel().select(pagamento.getPedido().getCliente());
			comboBoxPedido.setValue(pagamento.getPedido());
			textfieldDesconto.setText(pagamento.getDesconto().toString());
			textFieldValor.setText(pagamento.getValor().toString());
			comboBoxFormaDePagamento.setValue(pagamento.getFormaDePagamento());
			checkbox.setSelected(pagamento.getPagamentoStatus().equals(PagamentoStatus.pago) ? true : false);
		}else {
            textFieldCodigo.setText("");
            
            
        }
		
	}
	
	public void carregarTableViewPagamento() {
		tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnCliente.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPedido().getCliente().getNome()));
		
		
		listPagamentos = pagamentoService.listAll();

		observableListPagamento = FXCollections.observableArrayList(listPagamentos);
		tableViewPagamento.setItems(observableListPagamento);
		tableViewPagamento.refresh();
	}

	@FXML
	public void handleButtonInserir() throws IOException{
		if(verificarCamposPreenchidos()) {
			try {
				Pagamento pagamento = new Pagamento();
				pagamento.setId(null);
				pagamento.setPedido(comboBoxPedido.getSelectionModel().getSelectedItem());
				Double valor = (pagamento.getPedido().getValorTotal()) - (Double.valueOf(textfieldDesconto.getText()));
				pagamento.setValor(valor);
				
				if(valor < 50 && (comboBoxFormaDePagamento.getSelectionModel().getSelectedItem() == TipoFormaPagamento.cartao)) {
					exibirMensagemErro("A opção Cartão só será aceita para valores acima de 50 reais");
				}
				pagamento.setFormaDePagamento(comboBoxFormaDePagamento.getValue());
				pagamento.setPagamentoStatus(checkbox.isSelected() == true ? PagamentoStatus.pago : PagamentoStatus.naoPago);
				
				
				String resultado = pagamentoService.insert(pagamento);
				exibirMensagemErro(resultado);
				carregarTableViewPagamento();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Erro ao inserir pagamento");
			}
		}
	}
	
	@FXML
    public void handleButtonAlterar() throws IOException {
		Pagamento pagamento = tableViewPagamento.getSelectionModel().getSelectedItem();
		if(pagamento != null){
			Double valor = (pagamento.getPedido().getValorTotal()) - (Double.valueOf(textfieldDesconto.getText()));
			if(valor < 50 && (comboBoxFormaDePagamento.getSelectionModel().getSelectedItem() == TipoFormaPagamento.cartao)) {
				exibirMensagemErro("A opção Cartão só será aceita para valores acima de 50 reais");
			}
			pagamento.setFormaDePagamento(comboBoxFormaDePagamento.getValue());
			pagamento.setPagamentoStatus(checkbox.isSelected() == true ? PagamentoStatus.pago : PagamentoStatus.naoPago);
			
			String resultado = pagamentoService.update(pagamento);
			exibirMensagemErro(resultado);
			carregarTableViewPagamento();
		 } else {
	            Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setContentText("Por favor, escolha um Funcionário na Tabela!");
	            alert.show();
	     }
	}
	
	
	
	private boolean verificarCamposPreenchidos() {
		int countPreenchidos = 0;
		if(!comboBoxCliente.getSelectionModel().isEmpty()) {
			countPreenchidos++;
		}else {
			exibirMensagemErro("Um Cliente deve ser selecionado");
		}		
		if(!comboBoxPedido.getSelectionModel().isEmpty()) {
			countPreenchidos++;
		}else {
			exibirMensagemErro("Um Pedido deve ser selecionado");
		}
		textfieldDesconto.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,3}?")) {
                	textfieldDesconto.setText(oldValue);
                }
            }
        });
		if(!comboBoxFormaDePagamento.getSelectionModel().isEmpty()) {
			countPreenchidos++;
		}else {
			exibirMensagemErro("Uma Forma de Pagamento deve ser selecionado");
		}
		
		if(countPreenchidos == 3) {return true;};
		return false;
	}
	
	
	
	@FXML
	public void handleButtonDelete() throws IOException {
		Pagamento pagamento = tableViewPagamento.getSelectionModel().getSelectedItem();
		if (pagamento != null) {
			String resultado = pagamentoService.delete(pagamento.getId());
			exibirMensagemErro(resultado);

			carregarTableViewPagamento();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("Por favor, escolha um pagamento na Tabela!");
			alert.show();
		}

	}

	public void exibirMensagemErro(String resultado) {
		if (!resultado.equals("")) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText(resultado);
			alert.show();
		}
	}

}
