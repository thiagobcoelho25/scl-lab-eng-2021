package controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Base64;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Bairro;
import model.Funcionario;
import service.BairroService;
import service.FuncionarioService;

public class FXMLListarFuncionariosController implements Initializable {

	@FXML
	private ComboBox<Bairro> comboBoxBairro;
	
	@FXML
	private TableView<Funcionario> tableFuncionarios;
	
	@FXML
	private TableColumn<String, Funcionario> tableColumnNome;

	@FXML
	private TableColumn<String, Funcionario> tableColumnCargo;
	
	@FXML
	private Label txtLabelNome;
	@FXML
	private Label txtLabelCargo;
	@FXML
	private Label txtLabelSalario;
	@FXML
	private Label txtLabelNumero;
	@FXML
	private Label txtLabelBairro;
	@FXML
	private Label txtLabelRua;
	
	@FXML
	private ImageView imageViewFoto;
	
	@FXML
	private Button btnLimparFiltro;
	
	
	
	
	private final BairroService bairroService = new BairroService();
	private final FuncionarioService funcionarioService = new FuncionarioService();
	
	private ObservableList<Bairro> observableBairro;
	private List<Bairro> listaBairro;
	
	private ObservableList<Funcionario> observableFuncionario;
	private List<Funcionario> listaFuncionario;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		carregarComboBoxBairros();
		carregarTableViewFuncionario();
		
		tableFuncionarios.getSelectionModel().selectedItemProperty().addListener(
	               (observable, oldValue, newValue) -> selecionarItemTableViewFuncionario(newValue));
	}
	
	
	
	public void handleBtnLimparFiltro() {
		comboBoxBairro.getSelectionModel().clearSelection();
		carregarTableViewFuncionario();
	}
	
	public void selecionarBairro() {
		if(comboBoxBairro.getSelectionModel().getSelectedItem() != null) {
			Bairro bairroSelecionado = comboBoxBairro.getSelectionModel().getSelectedItem();
			listaFuncionario = funcionarioService.findByBairro(bairroSelecionado);
			observableFuncionario = FXCollections.observableArrayList(listaFuncionario);
	        tableFuncionarios.setItems(observableFuncionario);
	        tableFuncionarios.refresh();
		}	
	}
	
	public void carregarComboBoxBairros() {
		listaBairro = bairroService.listAll();
		observableBairro = FXCollections.observableArrayList(listaBairro);
		comboBoxBairro.setItems(observableBairro);
    }
	
	public void carregarTableViewFuncionario() {
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnCargo.setCellValueFactory(new PropertyValueFactory<>("cargo"));

        listaFuncionario = funcionarioService.listAll();
        
        observableFuncionario = FXCollections.observableArrayList(listaFuncionario);
        tableFuncionarios.setItems(observableFuncionario);
        tableFuncionarios.refresh();
    }
	
	public void selecionarItemTableViewFuncionario(Funcionario funcionario) {
		if(funcionario != null) {
			txtLabelNome.setText(funcionario.getNome());
			txtLabelCargo.setText(funcionario.getCargo());
			txtLabelSalario.setText(funcionario.getSalario().toString());
			txtLabelNumero.setText(funcionario.getNumero().toString());
			txtLabelBairro.setText(funcionario.getBairro().toString());
			txtLabelRua.setText(funcionario.getRua());
			if(funcionario.getFoto() != null) {
	        	imageViewFoto.setImage(convertObjImage(funcionario));
	        	imageViewFoto.setVisible(true);
	        }else {
	        	imageViewFoto.setVisible(false);
	        }
		}else {
			txtLabelNome.setText("");
			txtLabelCargo.setText("");
			txtLabelSalario.setText("");
			txtLabelNumero.setText("");
			txtLabelBairro.setText("");
			txtLabelRua.setText("");
			imageViewFoto.setVisible(false);
		}
		
	}
	
	private Image convertObjImage(Funcionario funcionario) {
		if(!funcionario.getFoto().isEmpty()) {
			try {
				byte[] imgByteArray = Base64.getDecoder().decode(funcionario.getFoto().getBytes());
		        InputStream in = new ByteArrayInputStream(imgByteArray);
		        return new Image(in);
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Parse String to Image FAIL ");
			}
		}
		return null;
	}

}
