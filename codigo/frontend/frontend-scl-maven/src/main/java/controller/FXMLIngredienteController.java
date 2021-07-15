package controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Ingrediente;
import service.IngredienteService;

public class FXMLIngredienteController implements Initializable{
	
	@FXML
	private TableView<Ingrediente> tableViewIngrediente;

	@FXML
	private TableColumn<Ingrediente, Integer> tableColumnCodigo;

	@FXML
	private TableColumn<Ingrediente, String> tableColumnNome;

	@FXML
	private TableColumn<Ingrediente, Double> tableColumnValor;
	
	@FXML
    private TextField textFieldCodigo;
	
	@FXML
    private TextField textFieldNome;
	
	@FXML
    private TextField textFieldValor;
	
	@FXML
    private Button buttonConfirmar;
	
	@FXML
    private Button buttonAlterar;
	
	@FXML
    private Button buttonDeletar;
	
	private final IngredienteService ingredienteService = new IngredienteService();
	
	private List<Ingrediente> listIngredientes;
	private ObservableList<Ingrediente> observableListIngredientes;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		carregarTableViewIngrediente();
		
		//So aceita no TextField Numeros com 0 a 7, com um '.' e 0 a 2 apos '.';
		textFieldValor.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,6}([\\.]\\d{0,2})?")) {
                	textFieldValor.setText(oldValue);
                }
            }
        });
		
		tableViewIngrediente.getSelectionModel().selectedItemProperty()
		.addListener((observable, oldValue, newValue) -> selecionarItemTableViewIngrediente(newValue));
	}
	
	public void carregarTableViewIngrediente() {
		tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tableColumnValor.setCellValueFactory(new PropertyValueFactory<>("valor"));

		listIngredientes = ingredienteService.listAll();

		observableListIngredientes = FXCollections.observableArrayList(listIngredientes);
		tableViewIngrediente.setItems(observableListIngredientes);
		tableViewIngrediente.refresh();
	}
	
	public void selecionarItemTableViewIngrediente(Ingrediente ingrediente) {
		if (ingrediente != null) {
			textFieldCodigo.setText(ingrediente.getId().toString());
			textFieldNome.setText(ingrediente.getNome());
			textFieldValor.setText(String.valueOf(ingrediente.getValor()));
		} else {
			textFieldCodigo.setText("");
			textFieldNome.setText("");
			textFieldValor.setText("");
		}
	}
	
	@FXML
    public void handleButtonConfirmar() {
		if ((tableViewIngrediente.getSelectionModel().getSelectedItem()) == null) {
			Ingrediente ingrediente = new Ingrediente();
        	ingrediente.setNome(textFieldNome.getText());
        	ingrediente.setValor(Double.parseDouble(textFieldValor.getText()));
        	ingrediente.setEstoque(null);
        	
        	String resultado = ingredienteService.insert(ingrediente); 
        	exibirMensagemErro(resultado);
        	carregarTableViewIngrediente();
        	
        	tableViewIngrediente.getSelectionModel().select(null);
		}
    }
	
	@FXML
	public void handleButtonAlterar() throws IOException {
		Ingrediente ingrediente = tableViewIngrediente.getSelectionModel().getSelectedItem();
		if (ingrediente != null) {
			
			ingrediente.setNome(textFieldNome.getText());
			ingrediente.setValor(textFieldValor.getText() != "" 
        			? Double.valueOf(textFieldValor.getText())
        			: Double.valueOf("0.0"));
			
			String resultado = ingredienteService.update(ingrediente);
			exibirMensagemErro(resultado);
			
			carregarTableViewIngrediente();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("Por favor, escolha um bairro na Tabela!");
			alert.show();
		}
		
	}
	
	@FXML
	public void handleButtonDelete() throws IOException {
		Ingrediente ingrediente = tableViewIngrediente.getSelectionModel().getSelectedItem();
		if (ingrediente != null) {
			String resultado = ingredienteService.delete(ingrediente.getId());
			exibirMensagemErro(resultado);
			
			carregarTableViewIngrediente();
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
