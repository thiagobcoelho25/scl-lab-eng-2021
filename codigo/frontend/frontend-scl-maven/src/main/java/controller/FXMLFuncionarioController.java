package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import model.Bairro;
import model.Funcionario;
import service.BairroService;
import service.FuncionarioService;

public class FXMLFuncionarioController implements Initializable {

	@FXML
    private TextField textFieldCodigo;
	
	@FXML
    private TextField textFieldNome;
	
	@FXML
    private TextField textFieldRua;
	
	@FXML
    private TextField textFieldNumero;
	
	@FXML
    private TextField textFieldCPF;
	
	@FXML
    private TextField textFieldCargo;
	
	@FXML
    private TextField textFieldSalario;
	
	@FXML
	private ComboBox<Bairro> comboBoxBairro;
	
	@FXML
	private ImageView imageViewFoto;
	
	@FXML
	private Button btnDeletar;
	
	@FXML
	private Button btnAlterar;

	@FXML
	private Button btnInserir;
	
	
	private final FuncionarioService funcionarioService = new FuncionarioService();
	private final BairroService bairroService = new BairroService();
	
	private ObservableList<Bairro> observableBairro;
	private List<Bairro> listaBairro;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		try {
			listaBairro = bairroService.listAll();
			observableBairro = FXCollections.observableArrayList(listaBairro);
			comboBoxBairro.setItems(observableBairro);
			
			Bairro b = comboBoxBairro.getValue();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Erro no bairro");
		}
		
		
		
		
	}
	
	@FXML
	public void onHandleButtonInserir() {
		System.out.println("Vc clicou em INSERIR !!");
		try {
			String result = funcionarioService.insert(makeObjFromInputs());
			System.out.println("Vc inseriu Funcionario" + result);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Erro no Funcionario");
		}
	}
	
	private Funcionario makeObjFromInputs() {
		System.out.println("Makke Object");
		if(verificarCamposPreenchidos()) {
			Funcionario resultado = new Funcionario(null,
					textFieldNome.getText(), 
					textFieldRua.getText(),
					Integer.parseInt(textFieldNumero.getText()),
					comboBoxBairro.getValue(),
					"Login", 
					"12345", 
					textFieldCargo.getText(), 
					Double.parseDouble(textFieldSalario.getText()));
			System.out.println("Criou Funcionario");
			System.out.println("FUncionario = " + resultado.toString());
			return resultado;
		}
		return null;
		
	}
	
	private boolean verificarCamposPreenchidos() {
		int countPreenchidos = 0;
		if(!textFieldNome.getText().isEmpty()) countPreenchidos++;
		if(!textFieldRua.getText().isEmpty()) countPreenchidos++;
		if(!textFieldNumero.getText().isEmpty()) countPreenchidos++;
		if(!textFieldCargo.getText().isEmpty()) countPreenchidos++;
		if(!textFieldSalario.getText().isEmpty()) countPreenchidos++;
		if(!comboBoxBairro.getSelectionModel().isEmpty()) countPreenchidos++;
		System.out.println("COUNT = " + countPreenchidos);
		if(countPreenchidos == 6) {return true;};
		return false;
	}
	

}
