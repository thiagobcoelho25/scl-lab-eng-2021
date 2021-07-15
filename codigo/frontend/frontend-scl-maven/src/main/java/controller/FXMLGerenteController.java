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
import model.Bairro;
import model.Gerente;
import service.BairroService;
import service.GerenteService;

public class FXMLGerenteController implements Initializable {

	@FXML
    private TableView<Gerente> tableViewGerente;
    
	@FXML
    private TableColumn<Gerente, Integer> tableColumnCodigo;
    
    @FXML
    private TableColumn<Gerente, String> tableColumnNome;
	
    @FXML
    private TextField textFieldCodigo;
	
	@FXML
    private TextField textFieldNome;
	
	@FXML
    private TextField textFieldRua;
	
	@FXML
    private TextField textFieldNumero;
	
	@FXML
	private TextField textFieldLogin;
	
	@FXML
	private TextField textFieldSenha;
	
	@FXML
    private ComboBox<Bairro> comboBoxBairro;
	
	@FXML
    private Button buttonConfirmar;
	
	@FXML
    private Button buttonAlterar;
	
	@FXML
    private Button buttonDeletar;
	
	private final GerenteService gerenteService = new GerenteService();
	
	private List<Gerente> listGerentes;
	private List<Bairro> listBairros;
    private ObservableList<Gerente> observableListGerentes;
    private ObservableList<Bairro> observableListBairros;
    
    private final BairroService bairroService = new BairroService();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		carregarTableViewGerente(); 
		
		carregarComboBoxBairros();
		
		 tableViewGerente.getSelectionModel().selectedItemProperty().addListener(
	               (observable, oldValue, newValue) -> selecionarItemTableViewGerente(newValue));
	
	}
	
	 public void carregarTableViewGerente() {
	        tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<>("id"));
	        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

	        listGerentes = gerenteService.listAll();

	        observableListGerentes = FXCollections.observableArrayList(listGerentes);
	        tableViewGerente.setItems(observableListGerentes);
	        tableViewGerente.refresh();
	    }

	@FXML
    public void handleButtonConfirmar() {
		
		if((tableViewGerente.getSelectionModel().getSelectedItem()) == null) {
			Gerente gerente = new Gerente();
			gerente.setNome(textFieldNome.getText());
			gerente.setRua(textFieldRua.getText());
			gerente.setNumero(Integer.valueOf(textFieldNumero.getText()));
			gerente.setBairro(comboBoxBairro.getValue());
			gerente.setLogin(textFieldLogin.getText());
			gerente.setSenha(textFieldSenha.getText());
        	

        	String resultado = gerenteService.insert(gerente); 
        	exibirMensagemErro(resultado);
        	carregarTableViewGerente();
	 }	
    }
	
		@FXML
	    public void handleButtonAlterar() throws IOException {
	        Gerente gerente = tableViewGerente.getSelectionModel().getSelectedItem();
	        if (gerente != null) {
	        	
	        	gerente.setBairro(comboBoxBairro.getSelectionModel().getSelectedItem());
	        	gerente.setLogin(textFieldLogin.getText());
	        	gerente.setNome(textFieldNome.getText());
	        	gerente.setNumero(textFieldNumero.getText() != "" 
    				? Integer.valueOf(textFieldNumero.getText()) 
    				: Integer.valueOf("0"));
	        	gerente.setRua(textFieldRua.getText());
	        	gerente.setSenha(textFieldSenha.getText());
	        	
	                String resultado = gerenteService.update(gerente);
	                exibirMensagemErro(resultado);
	                carregarTableViewGerente();
	            
	        } else {
	            Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setContentText("Por favor, escolha um Gerente na Tabela!");
	            alert.show();
	        }
	    }
	
		@FXML
	    public void handleButtonDeletar() throws IOException {
	        Gerente gerente = tableViewGerente.getSelectionModel().getSelectedItem();
	        if (gerente != null) {
	                String resultado = gerenteService.delete(gerente.getId());
	                exibirMensagemErro(resultado);
	                carregarTableViewGerente();
	            
	        } else {
	            Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setContentText("Por favor, escolha um Gerente na Tabela!");
	            alert.show();
	        }
	    }
		
		public void carregarComboBoxBairros() {
	        listBairros = bairroService.listAll();
	        observableListBairros = FXCollections.observableArrayList(listBairros);
	        comboBoxBairro.setItems(observableListBairros);
	    }
		
		public void selecionarItemTableViewGerente(Gerente gerente) {
	        if (gerente != null) {
	            textFieldCodigo.setText(gerente.getId().toString());
	            textFieldNome.setText(gerente.getNome());
	            textFieldRua.setText(gerente.getRua());
	            textFieldNumero.setText(String.valueOf(gerente.getNumero()));
	            comboBoxBairro.setValue(gerente.getBairro());
	            textFieldLogin.setText(gerente.getLogin());
	            textFieldSenha.setText(gerente.getSenha());
	        } else {
	            textFieldCodigo.setText("");
	            textFieldNome.setText("");
	            textFieldRua.setText("");
	            textFieldNumero.setText("");
	            textFieldLogin.setText("");
	            textFieldSenha.setText("");
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