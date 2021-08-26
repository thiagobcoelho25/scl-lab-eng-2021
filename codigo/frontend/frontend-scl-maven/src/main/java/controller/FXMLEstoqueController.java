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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Estoque;
import model.Ingrediente;
import service.IngredienteService;

public class FXMLEstoqueController implements Initializable {
	
	@FXML
    private TextField textFieldCodigo;
	
	@FXML
    private TextField textFieldQuantidade;
	
	@FXML
	private ComboBox<Ingrediente> comboBoxIngrediente;
	
	@FXML
	private Button btnDeletar;

	@FXML
	private Button btnInserir;
	
	private final IngredienteService ingredienteService = new IngredienteService();
	
	private ObservableList<Ingrediente> observableIngrediente;
	private List<Ingrediente> listaIngrediente;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			carregarComboBoxIngredientes();
		} catch (Exception e) {
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("Erro SQL CONECTION ");
			alerta.setContentText("Não foi possível se conectar ao banco de dados SCL \n Algumas funcionalidades estão comprometidas");
			alerta.show();	
		}
		
		textFieldQuantidade.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,3}?")) {
                	textFieldQuantidade.setText(oldValue);
                }
            }
        });
		comboBoxIngrediente.getSelectionModel().selectedItemProperty()
		.addListener((observable, oldValue, newValue) -> updateScreen(newValue));
		
	}
	
	public void carregarComboBoxIngredientes() {
		listaIngrediente = ingredienteService.listAll();
		observableIngrediente = FXCollections.observableArrayList(listaIngrediente);
		comboBoxIngrediente.setItems(observableIngrediente);
    }
	
	public void clearScreen() {
		textFieldCodigo.setText("");
		textFieldQuantidade.setText("");
		comboBoxIngrediente.valueProperty().set(null);
	}
	
	public void updateScreen(Ingrediente ingrediente) {
		if(ingrediente != null) {
			textFieldCodigo.setText(String.valueOf(ingrediente.getId()));
			textFieldQuantidade.setText(ingrediente.getEstoque() != null ? String.valueOf(ingrediente.getEstoque().getQuantidade()) : "");
		} else {
			textFieldCodigo.setText("");
			textFieldQuantidade.setText("");
		}
	}
	
	@FXML
    public void handleButtonInserir() throws IOException {
		String resultado = "";
		if(!comboBoxIngrediente.getSelectionModel().isEmpty()){
			Ingrediente ingrediente = comboBoxIngrediente.getValue();
			if(ingrediente.getEstoque() != null) {
				ingrediente.getEstoque().setQuantidade(textFieldQuantidade.getText() == "" ? null : Integer.parseInt(textFieldQuantidade.getText()));
			} else {
				Estoque estoque = new Estoque();
				estoque.setId(null);
				estoque.setQuantidade(textFieldQuantidade.getText() == "" ? null : Integer.parseInt(textFieldQuantidade.getText()));
				ingrediente.setEstoque(estoque);
			}
			resultado = ingredienteService.insertEstoque(ingrediente);
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("Por favor, escolha um Ingrediente!");
			alert.show();
		}
		exibirMensagemErro(resultado);
		clearScreen();
		carregarComboBoxIngredientes();
	}
	
	public void exibirMensagemErro(String resultado) {
		if (!resultado.equals("")) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText(resultado);
			alert.show();
		}
	}

}
