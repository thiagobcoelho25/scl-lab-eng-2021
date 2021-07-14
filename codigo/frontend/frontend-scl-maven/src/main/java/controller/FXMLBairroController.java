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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Bairro;
import model.enums.EntregavelStatus;
import service.BairroService;

public class FXMLBairroController implements Initializable {

	@FXML
	private TableView<Bairro> tableViewBairro;

	@FXML
	private TableColumn<Bairro, Integer> tableColumnCodigo;

	@FXML
	private TableColumn<Bairro, String> tableColumnNome;

	@FXML
	private TextField textFieldCodigo;

	@FXML
	private TextField textFieldNome;

	@FXML
	private TextField textFieldFrete;

	@FXML
	private CheckBox checkbox;

	@FXML
	private Button buttonConfirmar;

	@FXML
	private Button buttonAlterar;

	@FXML
	private Button buttonDeletar;

	private final BairroService bairroService = new BairroService();

	private List<Bairro> listBairros;
	private ObservableList<Bairro> observableListBairros;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		carregarTableViewBairro();

		tableViewBairro.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> selecionarItemTableViewBairro(newValue));

	}

	public void carregarTableViewBairro() {
		tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

		listBairros = bairroService.listAll();

		observableListBairros = FXCollections.observableArrayList(listBairros);
		tableViewBairro.setItems(observableListBairros);
		tableViewBairro.refresh();
	}

	@FXML
	public void handleButtonConfirmar() {
		if ((tableViewBairro.getSelectionModel().getSelectedItem()) == null) {
			Bairro bairro = new Bairro();
			bairro.setNome(textFieldNome.getText());
			bairro.setFrete(Double.parseDouble(textFieldFrete.getText()));
			bairro.setEntregavel(checkbox.isSelected() ? EntregavelStatus.sim : EntregavelStatus.nao);

			String resultado = bairroService.insert(bairro);
			exibirMensagemErro(resultado);
			carregarTableViewBairro();
		}
	}

	public void selecionarItemTableViewBairro(Bairro bairro) {
		if (bairro != null) {
			textFieldCodigo.setText(bairro.getId().toString());
			textFieldNome.setText(bairro.getNome());
			textFieldFrete.setText(String.valueOf(bairro.getFrete()));
			checkbox.setSelected(bairro.getEntregavel() == EntregavelStatus.sim ? true : false);
		} else {
			textFieldCodigo.setText("");
			textFieldNome.setText("");
			textFieldFrete.setText("");
			checkbox.setSelected(false);
		}
	}

	@FXML
	public void handleButtonAlterar() throws IOException {
		Bairro bairro = tableViewBairro.getSelectionModel().getSelectedItem();
		if (bairro != null) {
			String resultado = bairroService.update(bairro);
			exibirMensagemErro(resultado);
			
			carregarTableViewBairro();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("Por favor, escolha um bairro na Tabela!");
			alert.show();
		}
		
	}
	
	@FXML
	public void handleButtonDelete() throws IOException {
		Bairro bairro = tableViewBairro.getSelectionModel().getSelectedItem();
		if (bairro != null) {
			String resultado = bairroService.delete(bairro.getId());
			exibirMensagemErro(resultado);
			
			carregarTableViewBairro();
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
