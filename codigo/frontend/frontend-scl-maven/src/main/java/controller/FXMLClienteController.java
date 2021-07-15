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
import model.Cliente;
import service.BairroService;
import service.ClienteService;

public class FXMLClienteController implements Initializable {

	@FXML
    private TableView<Cliente> tableViewCliente;
    
	@FXML
    private TableColumn<Cliente, Integer> tableColumnCodigo;
    
    @FXML
    private TableColumn<Cliente, String> tableColumnNome;
	
    @FXML
    private TextField textFieldCodigo;
	
	@FXML
    private TextField textFieldNome;
	
	@FXML
    private TextField textFieldRua;
	
	@FXML
    private TextField textFieldReferencia;
	
	@FXML
    private TextField textFieldNumero;
	
	@FXML
    private ComboBox<Bairro> comboBoxBairro;
	
	@FXML
    private Button buttonConfirmar;
	
	@FXML
    private Button buttonAlterar;
	
	@FXML
    private Button buttonDeletar;
	
	private final ClienteService clienteService = new ClienteService();
	
	private List<Cliente> listClientes;
	private List<Bairro> listBairros;
    private ObservableList<Cliente> observableListClientes;
    private ObservableList<Bairro> observableListBairros;
    
    private final BairroService bairroService = new BairroService();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		carregarTableViewCliente(); 
		
		carregarComboBoxBairros();
		
		 tableViewCliente.getSelectionModel().selectedItemProperty().addListener(
	               (observable, oldValue, newValue) -> selecionarItemTableViewCliente(newValue));
	
	}
	
	 public void carregarTableViewCliente() {
	        tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<>("id"));
	        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

	        listClientes = clienteService.listAll();

	        observableListClientes = FXCollections.observableArrayList(listClientes);
	        tableViewCliente.setItems(observableListClientes);
	        tableViewCliente.refresh();
	    }

	@FXML
    public void handleButtonConfirmar() {
		
		if((tableViewCliente.getSelectionModel().getSelectedItem()) == null) {
			Cliente cliente = new Cliente();
        	cliente.setNome(textFieldNome.getText());
        	cliente.setRua(textFieldRua.getText());
        	cliente.setNumero(Integer.valueOf(textFieldNumero.getText()));
        	cliente.setPontoReferencia(textFieldReferencia.getText());
        	cliente.setBairro(comboBoxBairro.getValue());
        	

        	String resultado = clienteService.insert(cliente); 
        	exibirMensagemErro(resultado);
        	carregarTableViewCliente();
	 }	
    }
	
		@FXML
	    public void handleButtonAlterar() throws IOException {
	        Cliente cliente = tableViewCliente.getSelectionModel().getSelectedItem();
	        if (cliente != null) {
	        		cliente.setBairro(comboBoxBairro.getSelectionModel().getSelectedItem());
	        		cliente.setNome(textFieldNome.getText());
	        		cliente.setNumero(textFieldNumero.getText() != "" 
	        				? Integer.valueOf(textFieldNumero.getText()) 
	        				: Integer.valueOf("0"));
	        		cliente.setPontoReferencia(textFieldReferencia.getText());
	        		cliente.setRua(textFieldRua.getText());
	        		
	                String resultado = clienteService.update(cliente);
	                exibirMensagemErro(resultado);
	                carregarTableViewCliente();
	            
	        } else {
	            Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setContentText("Por favor, escolha um Cliente na Tabela!");
	            alert.show();
	        }
	    }
	
		@FXML
	    public void handleButtonDeletar() throws IOException {
	        Cliente cliente = tableViewCliente.getSelectionModel().getSelectedItem();
	        if (cliente != null) {
	                String resultado = clienteService.delete(cliente.getId());
	                exibirMensagemErro(resultado);
	                carregarTableViewCliente();
	            
	        } else {
	            Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setContentText("Por favor, escolha um Cliente na Tabela!");
	            alert.show();
	        }
	    }
		
		public void carregarComboBoxBairros() {
	        listBairros = bairroService.listAll();
	        observableListBairros = FXCollections.observableArrayList(listBairros);
	        comboBoxBairro.setItems(observableListBairros);
	    }
		
		public void selecionarItemTableViewCliente(Cliente cliente) {
	        if (cliente != null) {
	            textFieldCodigo.setText(cliente.getId().toString());
	            textFieldNome.setText(cliente.getNome());
	            textFieldRua.setText(cliente.getRua());
	            textFieldNumero.setText(String.valueOf(cliente.getNumero()));
	            textFieldReferencia.setText(cliente.getPontoReferencia());
	            comboBoxBairro.setValue(cliente.getBairro());
	        } else {
	            textFieldCodigo.setText("");
	            textFieldNome.setText("");
	            textFieldRua.setText("");
	            textFieldNumero.setText("");
	            textFieldReferencia.setText("");
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