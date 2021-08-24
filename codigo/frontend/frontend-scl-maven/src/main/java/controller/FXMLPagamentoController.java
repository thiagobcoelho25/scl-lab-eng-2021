package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Pagamento;


public class FXMLPagamentoController implements Initializable {
	@FXML
    private TableView<Pagamento> tableViewPagamento;
    
	@FXML
    private TableColumn<Pagamento, Integer> tableColumnCodigo;
    
    @FXML
    private TableColumn<Pagamento, String> tableColumnCliente;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
}
