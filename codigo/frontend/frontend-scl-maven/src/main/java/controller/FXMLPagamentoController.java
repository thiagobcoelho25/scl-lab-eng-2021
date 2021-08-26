package controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

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
import model.Pagamento;
import model.Pedido;
import service.PagamentoService;

public class FXMLPagamentoController implements Initializable {
	@FXML
	private TableView<Pagamento> tableViewPagamento;

	@FXML
	private TableColumn<Pagamento, Integer> tableColumnCodigo;

	@FXML
	private TableColumn<Pedido, String> tableColumnCliente;

	@FXML
	private TextField textFieldCodigo;

	@FXML
	private ComboBox<Pedido> comboBoxCliente;

	// @FXML
	// private ComboBox<Pedido> comboBoxPedido;

	@FXML
	private TextField textfieldDesconto;

	@FXML
	private TextField textFieldValor;

	@FXML
	private ComboBox<String> comboBoxFormaDePagamento;

	@FXML
	private CheckBox checkbox;

	@FXML
	private Button buttonConfirmar;

	@FXML
	private Button buttonAlterar;

	@FXML
	private Button buttonDeletar;

	private final PagamentoService pagamentoService = new PagamentoService();

	private List<Pagamento> listPagamentos;
	private ObservableList<Pagamento> observableListPagamento;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	public void carregarTableViewPagamento() {
		tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnCliente.setCellValueFactory(new PropertyValueFactory<>("nome"));

		listPagamentos = pagamentoService.listAll();

		observableListPagamento = FXCollections.observableArrayList(listPagamentos);
		tableViewPagamento.setItems(observableListPagamento);
		tableViewPagamento.refresh();
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
			alert.setContentText("Por favor, escolha um bairro na Tabela!");
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
