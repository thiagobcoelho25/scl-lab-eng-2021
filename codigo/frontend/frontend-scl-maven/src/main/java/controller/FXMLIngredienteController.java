package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Ingrediente;
import service.IngredienteService;

public class FXMLIngredienteController implements Initializable{
	
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	@FXML
    public void handleButtonConfirmar() {
        	Ingrediente ingrediente = new Ingrediente();
        	ingrediente.setNome(textFieldNome.getText());
        	ingrediente.setValor(Double.parseDouble(textFieldValor.getText()));
        	ingrediente.setEstoque(null);
        	String resultado = ingredienteService.insert(ingrediente); 
        	exibirMensagemErro(resultado);
    }
	
	public void exibirMensagemErro(String resultado) {
        if (!resultado.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(resultado);
            alert.show();
        }
    }
	
}
