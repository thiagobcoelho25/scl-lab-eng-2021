/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Israel
 */
public class FXMLMainController implements Initializable {

    @FXML
    private MenuItem Gerente;
    @FXML
    private MenuItem Funcionario;
    @FXML
    private MenuItem Cliente;
    @FXML
    private MenuItem Bairro;
    @FXML
    private MenuItem Produto;
    @FXML
    private MenuItem Ingrediente;
    @FXML
    private MenuItem Estoque;
    @FXML
    private MenuItem ProcessoEstoque;
    @FXML
    private MenuItem ProcessoPagamento;
    @FXML
    private MenuItem RealizarEntrega;
    @FXML
    private MenuItem RealizarPedido;
    @FXML
    private MenuItem ListagemFuncionarios;
    @FXML
    private AnchorPane AnchorPanePrincipal;
    @FXML
    private AnchorPane AnchorPanePai;
    
    
    @FXML
    private MenuItem RelatorioClientePorBairro;
    @FXML
    
    private VBox VBoxPai;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    	
    }    
    
    public void handleCadastroGerente() throws IOException{
        AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/views/FXMLCadastroGerente.fxml"));
        
        AnchorPanePrincipal.getChildren().setAll(anchor);
    
    }
    
    public void handleCadastroFuncionario() throws IOException{
        AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/views/FXMLCadastroFuncionario.fxml"));
        
        AnchorPanePrincipal.getChildren().setAll(anchor);
    
    }
    
    public void handleCadastroCliente() throws IOException{
        AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/views/FXMLCadastroCliente.fxml"));
        
        AnchorPanePrincipal.getChildren().setAll(anchor);
    
    }
    
    public void handleCadastroBairro() throws IOException{
        AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/views/FXMLCadastroBairro.fxml"));
        
        AnchorPanePrincipal.getChildren().setAll(anchor);
    
    }
    
    public void handleCadastroProduto() throws IOException{
        AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/views/FXMLCadastroProdutoWithScroll.fxml"));
        
        AnchorPanePrincipal.getChildren().setAll(anchor);
    
    }
    
    public void handleCadastroIngredientes() throws IOException{
        AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/views/FXMLCadastroIngredientes.fxml"));
        
        AnchorPanePrincipal.getChildren().setAll(anchor);
    
    }
    
    public void handleRealizarPedido() throws IOException{
        AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/views/FXMLPedido.fxml"));
        
        AnchorPanePrincipal.getChildren().setAll(anchor);
    
    }
    
    public void handleRealizarEntrega() throws IOException{
        AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/views/FXMLEntrega.fxml"));
        
        AnchorPanePrincipal.getChildren().setAll(anchor);
    
    }
    
    public void handleRealizarCadastroEstoque() throws IOException{
        AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/views/FXMLCadastroEstoque.fxml"));
        
        AnchorPanePrincipal.getChildren().setAll(anchor);
    
    }
    
    public void handleRealizarPagamento() throws IOException{
        AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/views/FXMLPagamento.fxml"));
        
        AnchorPanePrincipal.getChildren().setAll(anchor);
    
    }
    
    public void handleListarBairros() throws IOException{
        AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/views/FXMLListarBairros.fxml"));
        
        AnchorPanePrincipal.getChildren().setAll(anchor);
    
    }
}
