package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import model.Bairro;
import model.enums.EntregavelStatus;
import service.BairroService;

public class FXMLBairroController implements Initializable {

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
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	@FXML
    public void handleButtonConfirmar() {
        	Bairro bairro = new Bairro();
        	bairro.setNome(textFieldNome.getText());
        	bairro.setFrete(Double.parseDouble(textFieldFrete.getText()));
        	bairro.setEntregavel(checkbox.isSelected() ? EntregavelStatus.sim : EntregavelStatus.nao);
        	String resultado = bairroService.insert(bairro); 
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
