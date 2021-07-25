package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javafx.util.StringConverter;
import model.Ingrediente;
import model.Produto;
import model.ProdutosIngredientes;
import model.enums.TipoIngrediente;
import service.IngredienteService;
import service.ProdutoService;

public class FXMLProdutoController implements Initializable {

	@FXML
	private TableView<Produto> tableViewProduto;

	@FXML
	private TableColumn<Produto, Integer> tableColumnCodigo;

	@FXML
	private TableColumn<Produto, String> tableColumnNome;
	
	@FXML
	private TableColumn<Produto, Double> tableColumnValor;
	
	@FXML
	private TextField textFieldCodigo;

	@FXML
	private TextField textFieldNome;
	
	@FXML
	private TextField textFieldQuantidadePrincipal;
	
	@FXML
	private TextField textFieldQuantidadeSecundario;
	
	@FXML
    private ComboBox<Ingrediente> comboBoxIngredientePrincipal;
	
	@FXML
    private Button botaoIngredientePrincipal;
	
	@FXML
    private ComboBox<Ingrediente> comboBoxIngredienteSecundario;
	
	@FXML
    private Button botaoIngredienteSecundario;
	
	@FXML
	private TableView<ProdutosIngredientes> tableViewIngredientePrincipal;

	@FXML
	private TableColumn<ProdutosIngredientes, Integer> tableColumnQuantidadePricipal;
	
	@FXML
	private TableColumn<ProdutosIngredientes, String> tableColumnNomePrincipal;
	
	@FXML
	private TableView<ProdutosIngredientes> tableViewIngredienteSecundario;

	@FXML
	private TableColumn<ProdutosIngredientes, Integer> tableColumnQuantidadeSecundario;
	
	@FXML
	private TableColumn<ProdutosIngredientes, String> tableColumnNomeSecundario;
	
	@FXML
    private Button buttonConfirmar;
	
	@FXML
    private Button buttonAlterar;
	
	@FXML
    private Button buttonDeletar;
	
	@FXML
    private Button buttonAdicionarPrincipal;
	
	@FXML
    private Button buttonAdicionarSecundario;
	
	private final ProdutoService produtoService = new ProdutoService();
	private final IngredienteService ingredienteService = new IngredienteService();
	
	private List<Produto> listProdutos;
	private List<Ingrediente> listIngredientes;
	private List<ProdutosIngredientes> listProdutosIngredientesPrincipal = new ArrayList<ProdutosIngredientes>();
	private List<ProdutosIngredientes> listProdutosIngredientesSecundario = new ArrayList<ProdutosIngredientes>();
	private ObservableList<Produto> observableListProdutos;
	private ObservableList<Ingrediente> observableListIngredientes;
	private ObservableList<ProdutosIngredientes> observableListProdutosIngredientesPrincipal;
	private ObservableList<ProdutosIngredientes> observableListProdutosIngredientesSecundario;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		carregarTableViewProdutos();
		carregarTableViewAndComboBoxIngredientes();
		carregarTableViewProdutosIngredientes();
		
		tableViewProduto.getSelectionModel().selectedItemProperty()
		.addListener((observable, oldValue, newValue) -> selecionarItemTableViewProduto(newValue));
	}
	
	public void carregarTableViewProdutos() {
		//Tabela de Produtos
		tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tableColumnValor.setCellValueFactory(new PropertyValueFactory<>("precoFinal"));
		
		//Prepara tableView de ingredientes primarios e secundarios
		tableColumnQuantidadePricipal.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
		tableColumnNomePrincipal.setCellValueFactory(new Callback<CellDataFeatures<ProdutosIngredientes, String>, ObservableValue<String>>() {
	        @Override
	        public ObservableValue<String> call(CellDataFeatures<ProdutosIngredientes, String> c) {
	            return new SimpleStringProperty(c.getValue().getIngrediente().getNome());                
	        }
		}); 
		
		tableColumnQuantidadeSecundario.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
		tableColumnNomeSecundario.setCellValueFactory(new Callback<CellDataFeatures<ProdutosIngredientes, String>, ObservableValue<String>>() {
	        @Override
	        public ObservableValue<String> call(CellDataFeatures<ProdutosIngredientes, String> c) {
	            return new SimpleStringProperty(c.getValue().getIngrediente().getNome());                
	        }
		});
		
		listProdutos = produtoService.listAll();

		observableListProdutos = FXCollections.observableArrayList(listProdutos);
		tableViewProduto.setItems(observableListProdutos);
		tableViewProduto.refresh();
	}
	
	public void selecionarItemTableViewProduto(Produto produto) {
		if (produto != null) {
			textFieldCodigo.setText(produto.getId().toString());
			textFieldNome.setText(produto.getNome());
			carregarTableViewProdutosIngredientes(produto);
		} else {
			textFieldCodigo.setText("");
			textFieldNome.setText("");
		}
	}
	
	public void carregarTableViewProdutosIngredientes(Produto produto) {
		listProdutosIngredientesPrincipal = produto.getIngredientes()
											.stream().filter(p -> p.getTipo().equals(TipoIngrediente.principal))
											.collect(Collectors.toList());
		observableListProdutosIngredientesPrincipal = FXCollections.observableArrayList(listProdutosIngredientesPrincipal);
		tableViewIngredientePrincipal.setItems(observableListProdutosIngredientesPrincipal);
		tableViewIngredientePrincipal.refresh();
		
		listProdutosIngredientesSecundario = produto.getIngredientes()
											.stream().filter(p -> p.getTipo().equals(TipoIngrediente.secundario))
											.collect(Collectors.toList());
		observableListProdutosIngredientesSecundario = FXCollections.observableArrayList(listProdutosIngredientesSecundario);
		tableViewIngredienteSecundario.setItems(observableListProdutosIngredientesSecundario);
		tableViewIngredienteSecundario.refresh();
	}
	
	public void carregarTableViewProdutosIngredientes() {
		observableListProdutosIngredientesPrincipal = FXCollections.observableArrayList(listProdutosIngredientesPrincipal);
		tableViewIngredientePrincipal.setItems(observableListProdutosIngredientesPrincipal);
		tableViewIngredientePrincipal.refresh();
		
		observableListProdutosIngredientesSecundario = FXCollections.observableArrayList(listProdutosIngredientesSecundario);
		tableViewIngredienteSecundario.setItems(observableListProdutosIngredientesSecundario);
		tableViewIngredienteSecundario.refresh();
	}
	
	public void carregarTableViewAndComboBoxIngredientes() {
		listIngredientes = ingredienteService.listAll();
		
		observableListIngredientes = FXCollections.observableArrayList(listIngredientes);
        comboBoxIngredientePrincipal.setItems(observableListIngredientes);
        comboBoxIngredienteSecundario.setItems(observableListIngredientes);
        //toString para Combobox, sem mudar do model
        comboBoxIngredientePrincipal.setConverter(new StringConverter<Ingrediente>() {
            @Override
            public String toString(Ingrediente ingrediente) {
                return ingrediente.getNome();
            }

            @Override
            public Ingrediente fromString(String string) {
                return comboBoxIngredientePrincipal.getSelectionModel().getSelectedItem();
            }
        });
        comboBoxIngredienteSecundario.setConverter(new StringConverter<Ingrediente>() {
            @Override
            public String toString(Ingrediente ingrediente) {
                return ingrediente.getNome();
            }

            @Override
            public Ingrediente fromString(String string) {
                return comboBoxIngredientePrincipal.getSelectionModel().getSelectedItem();
            }
        });
	}
	
	@FXML
	public void handleButtonInserirIngredientePrincipal() {
		//Para atualização de Produto
		if(!comboBoxIngredientePrincipal.getSelectionModel().isEmpty()
				 && textFieldQuantidadePrincipal.getText() != ""
				 && !tableViewProduto.getSelectionModel().isEmpty()) {
			Produto produto = tableViewProduto.getSelectionModel().getSelectedItem();
			ProdutosIngredientes prodIngred = new ProdutosIngredientes(null,
					comboBoxIngredientePrincipal.getValue(),
					Integer.valueOf(textFieldQuantidadePrincipal.getText()),
					TipoIngrediente.principal);
			
			if(produto.getIngredientes().contains(prodIngred)){
				ProdutosIngredientes toGetQuantity = produto.getIngredientes().stream().filter(p -> 
										p.getTipo().equals(TipoIngrediente.principal) && 
										p.getIngrediente().equals(prodIngred.getIngrediente()))
										.collect(Collectors.toList()).get(0);
				
				toGetQuantity.setQuantidade(toGetQuantity.getQuantidade() + Integer.valueOf(textFieldQuantidadePrincipal.getText()));
			}else {
				produto.getIngredientes().add(prodIngred);
			}
			carregarTableViewProdutosIngredientes(produto);
		//Para Inserção de novo Produto
		} else if(!comboBoxIngredientePrincipal.getSelectionModel().isEmpty() && textFieldQuantidadePrincipal.getText() != ""){
			ProdutosIngredientes prodIngred = new ProdutosIngredientes(null,
					comboBoxIngredientePrincipal.getValue(),
					Integer.valueOf(textFieldQuantidadePrincipal.getText()),
					TipoIngrediente.principal);
			
			if(listProdutosIngredientesPrincipal.contains(prodIngred)) {
				ProdutosIngredientes toGetQuantity = listProdutosIngredientesPrincipal.stream().filter(p -> 
										p.getTipo().equals(TipoIngrediente.principal) && 
										p.getIngrediente().equals(prodIngred.getIngrediente()))
										.collect(Collectors.toList()).get(0);
				
				toGetQuantity.setQuantidade(toGetQuantity.getQuantidade() + Integer.valueOf(textFieldQuantidadePrincipal.getText()));
			}else {
				listProdutosIngredientesPrincipal.add(prodIngred);
			}
			carregarTableViewProdutosIngredientes();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha uma Quantidade!");
            alert.show();
		}
	}
	
	@FXML
	public void handleButtonInserirIngredienteSecundario() {
		// Para atualização de Produto
		if (!comboBoxIngredienteSecundario.getSelectionModel().isEmpty()
				&& textFieldQuantidadeSecundario.getText() != ""
				&& !tableViewProduto.getSelectionModel().isEmpty()) {
			Produto produto = tableViewProduto.getSelectionModel().getSelectedItem();
			ProdutosIngredientes prodIngred = new ProdutosIngredientes(null,
					comboBoxIngredienteSecundario.getValue(),
					Integer.valueOf(textFieldQuantidadeSecundario.getText()),
					TipoIngrediente.secundario);

			if (produto.getIngredientes().contains(prodIngred)) {
				ProdutosIngredientes toGetQuantity = produto.getIngredientes().stream()
						.filter(p -> p.getTipo().equals(TipoIngrediente.secundario)
						 && p.getIngrediente().equals(prodIngred.getIngrediente()))
						.collect(Collectors.toList()).get(0);

				toGetQuantity.setQuantidade(toGetQuantity.getQuantidade() + Integer.valueOf(textFieldQuantidadeSecundario.getText()));
			} else {
				produto.getIngredientes().add(prodIngred);
			}
			carregarTableViewProdutosIngredientes(produto);
			// Para Inserção de novo Produto
		} else if (!comboBoxIngredienteSecundario.getSelectionModel().isEmpty()
				&& textFieldQuantidadeSecundario.getText() != "") {
			ProdutosIngredientes prodIngred = new ProdutosIngredientes(null,
					comboBoxIngredienteSecundario.getValue(),
					Integer.valueOf(textFieldQuantidadeSecundario.getText()),
					TipoIngrediente.secundario);

			if (listProdutosIngredientesSecundario.contains(prodIngred)) {
				ProdutosIngredientes toGetQuantity = listProdutosIngredientesSecundario.stream()
						.filter(p -> p.getTipo().equals(TipoIngrediente.secundario)
						 && p.getIngrediente().equals(prodIngred.getIngrediente()))
						.collect(Collectors.toList()).get(0);

				toGetQuantity.setQuantidade(toGetQuantity.getQuantidade() + Integer.valueOf(textFieldQuantidadeSecundario.getText()));
			} else {
				listProdutosIngredientesSecundario.add(prodIngred);
			}
			carregarTableViewProdutosIngredientes();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("Por favor, escolha uma Quantidade!");
			alert.show();
		}
	}
	
	@FXML
    public void handleButtonConfirmar() {
		if ((tableViewProduto.getSelectionModel().getSelectedItem()) == null) {
			Produto produto = new Produto();
        	produto.setId(null);
        	produto.setNome(textFieldNome.getText());
        	produto.setPrecoFinal(0.0);
        	produto.setIngredientes(Stream.concat(listProdutosIngredientesPrincipal.stream(),
					 listProdutosIngredientesSecundario.stream())
                   .collect(Collectors.toList()));
        	
        	String resultado = produtoService.insert(produto); 
        	exibirMensagemErro(resultado);
        	carregarTableViewProdutos();
        	
        	tableViewProduto.getSelectionModel().select(null);
		}
    }
	
	@FXML
	public void handleButtonAlterar() throws IOException {
		Produto produto = tableViewProduto.getSelectionModel().getSelectedItem();
		if (produto != null) {
			produto.setNome(textFieldNome.getText());
			produto.setPrecoFinal(0.0);
			produto.setIngredientes(Stream.concat(listProdutosIngredientesPrincipal.stream(),
					 listProdutosIngredientesSecundario.stream())
                    .collect(Collectors.toList()));
			
			String resultado = produtoService.update(produto);
			exibirMensagemErro(resultado);
			
			carregarTableViewProdutos();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("Por favor, escolha um Produto na Tabela!");
			alert.show();
		}
		tableViewProduto.getSelectionModel().select(null);
	}
	
	@FXML
    public void handleButtonDeletar() throws IOException {
        Produto produto = tableViewProduto.getSelectionModel().getSelectedItem();
        if (produto != null) {
                String resultado = produtoService.delete(produto.getId());
                exibirMensagemErro(resultado);
                carregarTableViewProdutos();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um Produto na Tabela!");
            alert.show();
        }
        tableViewProduto.getSelectionModel().select(null);
    }
	
	public void exibirMensagemErro(String resultado) {
		if (!resultado.equals("")) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText(resultado);
			alert.show();
		}
	}

}
