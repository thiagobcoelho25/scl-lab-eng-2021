/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author thiag
 */
public class FXMLCadastroBairroController implements Initializable {

    @FXML
    private TextField textFieldCodigo;
    @FXML
    private Button btnAlterar;
    @FXML
    private TextField textFieldNome;
    @FXML
    private TextField textFieldFrete;
    @FXML
    private CheckBox checkBoxEntregavel;
    @FXML
    private Button btnInserir;
    @FXML
    private Button btnRemover;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    public void onHandleButtonInserir() {
        System.out.println("Voce clicou em salvar Bairro");
    }
    
}
