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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
	
	@FXML
	private TableView<Funcionario> tableViewFuncionario;
	
	@FXML
	private TableColumn<Funcionario, Integer> colunaID;
	
	@FXML
	private TableColumn<Funcionario, String> colunaNome;
	
	
	private final FuncionarioService funcionarioService = new FuncionarioService();
	private final BairroService bairroService = new BairroService();
	
	private ObservableList<Bairro> observableBairro;
	private List<Bairro> listaBairro;
	
	private ObservableList<Funcionario> observableFuncionario;
	private List<Funcionario> listaFuncionario;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		carregarComboBoxBairros();
		
		carregarTableViewFuncionario();
		
		 tableViewFuncionario.getSelectionModel().selectedItemProperty().addListener(
	               (observable, oldValue, newValue) -> selecionarItemTableViewGerente(newValue));
			
		
	}
	
	public void carregarTableViewFuncionario() {
        colunaID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

        listaFuncionario = funcionarioService.listAll();
        
        observableFuncionario = FXCollections.observableArrayList(listaFuncionario);
        tableViewFuncionario.setItems(observableFuncionario);
        tableViewFuncionario.refresh();
    }
	
	@FXML
    public void handleButtonAlterar() throws IOException {
        Funcionario funcionario = tableViewFuncionario.getSelectionModel().getSelectedItem();
        if (funcionario != null) {
        	
        	funcionario.setBairro(comboBoxBairro.getSelectionModel().getSelectedItem());
        	funcionario.setCargo(textFieldCargo.getText());
        	funcionario.setNome(textFieldNome.getText());
        	funcionario.setNumero(textFieldNumero.getText() != "" 
    				? Integer.valueOf(textFieldNumero.getText()) 
    				: Integer.valueOf("0"));
        	funcionario.setRua(textFieldRua.getText());
        	funcionario.setSalario(textFieldSalario.getText() != "" 
        			? Double.valueOf(textFieldSalario.getText())
        			: Double.valueOf("0.0"));
        	
                String resultado = funcionarioService.update(funcionario);
                exibirMensagemErro(resultado);
                carregarTableViewFuncionario();
            
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um Gerente na Tabela!");
            alert.show();
        }
    }

	@FXML
    public void handleButtonDeletar() throws IOException {
		Funcionario funcionario = tableViewFuncionario.getSelectionModel().getSelectedItem();
        if (funcionario != null) {
                String resultado = funcionarioService.delete(funcionario.getId());
                exibirMensagemErro(resultado);
                carregarTableViewFuncionario();
            
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um Gerente na Tabela!");
            alert.show();
        }
    }
	
	public void carregarComboBoxBairros() {
		listaBairro = bairroService.listAll();
		observableBairro = FXCollections.observableArrayList(listaBairro);
		comboBoxBairro.setItems(observableBairro);
    }
	
	public void selecionarItemTableViewGerente(Funcionario funcionario) {
        if (funcionario != null) {
            textFieldCodigo.setText(funcionario.getId().toString());
            textFieldNome.setText(funcionario.getNome());
            textFieldRua.setText(funcionario.getRua());
            textFieldNumero.setText(String.valueOf(funcionario.getNumero()));
            comboBoxBairro.setValue(funcionario.getBairro());
            textFieldCargo.setText(funcionario.getCargo());
            textFieldSalario.setText(String.valueOf(funcionario.getSalario()));
        } else {
            textFieldCodigo.setText("");
            textFieldNome.setText("");
            textFieldRua.setText("");
            textFieldNumero.setText("");
            textFieldCargo.setText("");
            textFieldSalario.setText("");
        }
    }

public void exibirMensagemErro(String resultado) {
    if (!resultado.equals("")) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(resultado);
        alert.show();
    }
}
	
	
	
	@FXML
	public void onHandleButtonInserir() {
		System.out.println("Vc clicou em INSERIR !!");
		try {
			String result = funcionarioService.insert(makeObjFromInputs());
			exibirMensagemErro(result);
			carregarTableViewFuncionario();
			//System.out.println("Vc inseriu Funcionario" + result);
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
