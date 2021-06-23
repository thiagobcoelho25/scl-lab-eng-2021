package frontend.scl;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class FrontendScl extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);

        URL arquivoFXML = getClass().getResource("/views/FXMLCadastroPedido.fxml");

        Parent fxmlParente = null;
        try {
            fxmlParente = (Parent) FXMLLoader.load(arquivoFXML);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(fxmlParente, fxmlParente.getLayoutX(), fxmlParente.getLayoutY());
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
