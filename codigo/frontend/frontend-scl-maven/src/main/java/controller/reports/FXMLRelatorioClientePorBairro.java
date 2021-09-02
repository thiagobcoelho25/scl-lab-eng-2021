package controller.reports;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Bairro;
import model.Cliente;
import service.BairroService;
import service.ClienteService;

public class FXMLRelatorioClientePorBairro implements Initializable {

	@FXML
	private TableView<Cliente> tableViewClientes;
	@FXML
	private TableColumn<Cliente, String> tableColumnNomeCliente;
	@FXML
	private TableColumn<Cliente, String> tableColumnRuaCliente;
	@FXML
	private ComboBox<Bairro> comboBoxBairro;

	private final ClienteService clienteService = new ClienteService();
	private final BairroService bairroService = new BairroService();

	private List<Cliente> listCliente;
	private ObservableList<Cliente> observableListCliente;

	private List<Bairro> listBairro;
	private ObservableList<Bairro> observableListBairro;

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		tableColumnNomeCliente.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tableColumnRuaCliente.setCellValueFactory(new PropertyValueFactory<>("rua"));

		carregarComboBoxBairro();

	}

	public void carregarTableViewClientes() {
		if (!comboBoxBairro.getSelectionModel().isEmpty()) {

			Bairro bairro = comboBoxBairro.getSelectionModel().getSelectedItem();

			listCliente = (List<Cliente>) clienteService.findByBairro(bairro);
			observableListCliente = FXCollections.observableArrayList(listCliente);
			tableViewClientes.setItems(observableListCliente);
			tableViewClientes.refresh();
		}
	}

	public void carregarComboBoxBairro() {
		listBairro = bairroService.listAll();

		System.out.println(listBairro);

		observableListBairro = FXCollections.observableArrayList(listBairro);
		comboBoxBairro.setItems(observableListBairro);
	}

}