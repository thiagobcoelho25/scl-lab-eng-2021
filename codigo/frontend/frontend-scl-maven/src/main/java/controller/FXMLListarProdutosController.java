package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Produto;
import model.Produto;
import service.ProdutoService;

public class FXMLListarProdutosController implements Initializable{
	
	@FXML
	private TableView tableProduto;
	@FXML
	private TableColumn<Produto, String> columnNome;
	@FXML
	private TableColumn<Produto, Double> columnValor;
	
	@FXML
	private TextField txtFieldValorMinimo;
	
	@FXML
	private Button btnFiltrar;
	@FXML
	private Button btnLimparFiltros;
	
	private final ProdutoService produtoService = new ProdutoService();
	
	private ObservableList<Produto> observableProduto;
	private List<Produto> listaProduto;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		carregarTableProdutos();
		
	}
	
	private void carregarTableProdutos() {
		columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnValor.setCellValueFactory(new PropertyValueFactory<>("precoFinal"));

        listaProduto = produtoService.listAll();
        
        observableProduto = FXCollections.observableArrayList(listaProduto);
        tableProduto.setItems(observableProduto);
        tableProduto.refresh();
	}
	
	@FXML
	public void handleBtnFiltrarClicked() {
		if(!txtFieldValorMinimo.getText().isEmpty()) {
			try {
				Double valor = Double.parseDouble(txtFieldValorMinimo.getText());
				listaProduto = produtoService.listAllProdutosMaiorQue(valor);
			}catch (Exception e) {
				System.out.println("Error parse = " + e.getMessage());
				Alert alerta = new Alert(AlertType.WARNING);
				alerta.setContentText("Digite um valor numérico valido positivo");
				alerta.setTitle("Erro Parse Number");
				alerta.show();
			}
	        
	        observableProduto = FXCollections.observableArrayList(listaProduto);
	        tableProduto.setItems(observableProduto);
	        tableProduto.refresh();
		}
	}
	
	@FXML
	public void handleBtnLimparFiltroClicked() {
		carregarTableProdutos();
		
	}

}
