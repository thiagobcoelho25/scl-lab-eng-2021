package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Bairro;
import model.enums.EntregavelStatus;
import service.BairroService;



public class FXMLListarBairrosController implements Initializable {
	@FXML
	private TableView<Bairro> tableViewBairros;

	@FXML
	private TableColumn<Bairro, String> tableColumnNome;

	@FXML
	private TableColumn<Bairro, Integer> tableColumnId;
	
	@FXML
	private TableColumn<Bairro, String> tableColumnEntregavel;
	
	@FXML
	private CheckBox checkbox;
	

	private final BairroService bairroService = new BairroService();
	
	private List<Bairro> listBairros;
	private ObservableList<Bairro> observableListBairros;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		carregarTableViewPagamento();
		
	}
	
	public void carregarTableViewPagamento() {
		tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnEntregavel.setCellValueFactory(new PropertyValueFactory<>("entregavel"));
		
		
		verificaMarcacao();
	}
	
	@FXML
	public void verificaMarcacao() {
		listBairros = bairroService.findByEntregavel(checkbox.isSelected() == true ? EntregavelStatus.sim.toString() : EntregavelStatus.nao.toString());
		
		System.out.println(listBairros);
		
		observableListBairros = FXCollections.observableArrayList(listBairros);
		tableViewBairros.setItems(observableListBairros);
		tableViewBairros.refresh();
	}
}
