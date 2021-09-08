package controller.reports;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Ingrediente;
import service.IngredienteService;

public class FXMLListaIngredientesController implements Initializable {

	@FXML
	private TextField textFieldCodigo;

	@FXML
	private TextField textFieldQuantidade;

	@FXML
	private Button buttonConfirmar;

	@FXML
	private TableView<Ingrediente> tableViewIngrediente;

	@FXML
	private TableColumn<Ingrediente, Integer> tableColumnQuantidade;

	@FXML
	private TableColumn<Ingrediente, String> tableColumnNome;

	private ObservableList<Ingrediente> observableIngredientes;
	private List<Ingrediente> listaIngredientes;

	private final IngredienteService ingredienteService = new IngredienteService();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		carregarTableView();

		// So aceita no TextField Numeros Integers com 0 a 3 casas
		textFieldQuantidade.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d{0,3}?")) {
					textFieldQuantidade.setText(oldValue);
				}
			}
		});
	}

	public void carregarTableView() {
		tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tableColumnQuantidade.setCellValueFactory(
				cellData -> new SimpleIntegerProperty(cellData.getValue().getEstoque().getQuantidade()).asObject());

		listaIngredientes = ingredienteService.relatorioIngredinete(0);
		observableIngredientes = FXCollections.observableArrayList(listaIngredientes);
		tableViewIngrediente.setItems(observableIngredientes);
		tableViewIngrediente.refresh();
	}

	@FXML
	public void handleButtonConfirmar() {
		if (textFieldQuantidade.getText() != "") {
			listaIngredientes = ingredienteService.relatorioIngredinete(Integer.valueOf(textFieldQuantidade.getText()));
			observableIngredientes = FXCollections.observableArrayList(listaIngredientes);
			tableViewIngrediente.setItems(observableIngredientes);
			tableViewIngrediente.refresh();
		}
	}

}
