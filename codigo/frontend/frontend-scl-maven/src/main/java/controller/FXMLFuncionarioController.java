package controller;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.util.Base64;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.imageio.stream.IIOByteBuffer;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;

import org.apache.tomcat.util.http.fileupload.FileUtils;

import com.sun.glass.ui.CommonDialogs.ExtensionFilter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
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
	private Button btnFoto;
	
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
	
	private File selectedFile;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			carregarComboBoxBairros();
			carregarTableViewFuncionario();
		} catch (Exception e) {
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("Erro SQL CONECTION ");
			alerta.setContentText("Não foi possível se conectar ao banco de dados SCL \n Algumas funcionalidades estão comprometidas");
			alerta.show();
			
		}
		
		
		 tableViewFuncionario.getSelectionModel().selectedItemProperty().addListener(
	               (observable, oldValue, newValue) -> selecionarItemTableViewFuncionario(newValue));
			
		
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
        	funcionario.setFoto(convertImagetoString(selectedFile));
        	
                String resultado = funcionarioService.update(funcionario);
                exibirMensagemErro("Erro no Update", resultado);
                carregarTableViewFuncionario();
            
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um Funcionário na Tabela!");
            alert.show();
        }
    }

	@FXML
    public void handleButtonDeletar() throws IOException {
		Funcionario funcionario = tableViewFuncionario.getSelectionModel().getSelectedItem();
        if (funcionario != null) {
                String resultado = funcionarioService.delete(funcionario.getId());
                exibirMensagemErro("Erro ao Deletar", resultado);
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
	
	public void selecionarItemTableViewFuncionario(Funcionario funcionario) {
        if (funcionario != null) {
            textFieldCodigo.setText(funcionario.getId().toString());
            textFieldNome.setText(funcionario.getNome());
            textFieldRua.setText(funcionario.getRua());
            textFieldNumero.setText(String.valueOf(funcionario.getNumero()));
            comboBoxBairro.setValue(funcionario.getBairro());
            textFieldCargo.setText(funcionario.getCargo());
            textFieldSalario.setText(String.valueOf(funcionario.getSalario()));
            if(funcionario.getFoto() != null) {
            	imageViewFoto.setImage(convertObjImage(funcionario));
            	imageViewFoto.setVisible(true);
            	return;
            }
        } else {
            textFieldCodigo.setText("");
            textFieldNome.setText("");
            textFieldRua.setText("");
            textFieldNumero.setText("");
            textFieldCargo.setText("");
            textFieldSalario.setText("");
        }
        imageViewFoto.setVisible(false);
        
    }
	
	@FXML
	public void onHandleButtonInserir() {
		System.out.println("Vc clicou em INSERIR !!");
		if(verificarCamposPreenchidos()) {
			try {
				String result = funcionarioService.insert(makeObjFromInputs());
				exibirMensagemErro("Erro ao Inserir", result);
				carregarTableViewFuncionario();
				//System.out.println("Vc inseriu Funcionario" + result);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Erro no Funcionario");
			}
		}
		
	}
	
	private Funcionario makeObjFromInputs() throws NumberFormatException, IOException {
		Funcionario resultado = new Funcionario(null,
				textFieldNome.getText(), 
				textFieldRua.getText(),
				Integer.parseInt(textFieldNumero.getText()),
				comboBoxBairro.getValue(),
				"Login", 
				"12345", 
				textFieldCargo.getText(), 
				Double.parseDouble(textFieldSalario.getText()),
				convertImagetoString(selectedFile));
		System.out.println("Criou Funcionario");
		System.out.println("FUncionario = " + resultado.toString());
		return resultado;
	
		
	}
	
	private boolean verificarCamposPreenchidos() {
		int countPreenchidos = 0;
		if(verificarNome(textFieldNome.getText())) countPreenchidos++;
		if(verificarRua(textFieldRua.getText())) countPreenchidos++;
		if(verificarNumero(textFieldNumero.getText())) countPreenchidos++;
		if(verificarCargo(textFieldCargo.getText())) countPreenchidos++;
		if(verificarSalario(textFieldSalario.getText())) countPreenchidos++;
		if(verificarBairro()) countPreenchidos++;
		System.out.println("COUNT = " + countPreenchidos);
		if(countPreenchidos == 6) {return true;};
		return false;
	}
	
	private boolean verificarNome(String value) {
		if(value != "") {
			if(value.length() >= 1 && value.length() <= 100) {
				return true;
			}
			exibirMensagemErro("Erro no Campo Nome", "Nome deve ter entre 1 a 100 caracteres");
			return false;
		}
		exibirMensagemErro("Erro no Campo Nome", "Nome deve ser preenchido");
		return false;
	}
	
	private boolean verificarRua(String value) {
		if(value != "") {
			if(value.length() >= 1 && value.length() <= 40) {
				return true;
			}
			exibirMensagemErro("Erro no Campo Rua", "Rua deve ter entre 1 a 40 caracteres");
			return false;
		}
		exibirMensagemErro("Erro no Campo Rua", "Rua deve ser preenchido");
		return false;
	}
	
	private boolean verificarNumero(String value) {
		// Obs: valor int não consegue armazenar um numero de telefone
		try {
			int aux = Integer.parseInt(value);
			if(aux != 0) {
				if(aux > 0) {
					return true;
				}
				exibirMensagemErro("Erro no Campo Numero", "Numero deve ter apenas dígitos positivos");
			}
		} catch (Exception e) {
			exibirMensagemErro("Erro no Campo Numero", "Numero deve ser preenchido");	
		}
		return false;
	}
	
	private boolean verificarCargo(String value) {
		if(value != "") {
			if(value.length() >= 2 && value.length() <= 40) {
				return true;
			}
			exibirMensagemErro("Erro no Campo Cargo", "Cargo deve ter entre 2 a 40 caracteres");
			return false;
		}
		exibirMensagemErro("Erro no Campo Cargo", "Cargo deve ser preenchido");
		return false;
	}
	
	private boolean verificarSalario(String value) {
		try {
			double aux = Double.parseDouble(value);
			if(aux != 0) {
				if(aux > 0) {
					return true;
				}
				exibirMensagemErro("Erro no Campo Salário", "Salário deve ter apenas dígitos positivos");
			}
		} catch (Exception e) {
			exibirMensagemErro("Erro no Campo Salário", "Salário deve ser preenchido");
		}
		return false;	
	}
	
	private boolean verificarBairro() {
		if(comboBoxBairro.getSelectionModel().isEmpty()) {
			exibirMensagemErro("Erro no Campo Bairro", "Bairro deve ser preenchido");
			return false;
		}
		return true;
	}
	
	
	
	
	public void exibirMensagemErro(String titulo, String conteudo) {
	    if (!conteudo.equals("")) {
	        Alert alert = new Alert(Alert.AlertType.WARNING);
	        alert.setTitle(titulo);
	        alert.setContentText(conteudo);
	        alert.show();
	    }
	}
	
	@FXML
	public void onBtnFotoClicked() {
		FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg"));
        selectedFile = fileChooser.showOpenDialog(btnFoto.getContextMenu());
        if (selectedFile != null) {
           imageViewFoto.setImage(new Image(selectedFile.toURI().toString()));
           imageViewFoto.setVisible(true);
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
	
	private String convertImagetoString(File file) throws IOException {
		if(file != null) {
			FileInputStream fileInputStreamReader = new FileInputStream(file);
            byte[] bytes = new byte[(int)file.length()];
            fileInputStreamReader.read(bytes);
            String retorno = new String(Base64.getEncoder().encodeToString(bytes));
	        fileInputStreamReader.close();
	        return retorno;
		}
		return null;
	}
	
	
	

}
