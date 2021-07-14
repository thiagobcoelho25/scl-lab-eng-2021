package frontend_scl.frontend_scl_maven;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
	
	@Override
    public void start(Stage primaryStage) throws Exception {
       
        Parent root = FXMLLoader.load(getClass().getResource("/views/FXMLMain.fxml"));
        Scene scene = new Scene(root);
        
        primaryStage.setScene(scene);
        primaryStage.setWidth(1000);
        //primaryStage.setWidth(739);
        //primaryStage.setHeight(486);
        primaryStage.setHeight(950);
        primaryStage.setResizable(false);
        primaryStage.show();
         
    }

    public static void main(String[] args) {
        launch(args);
    }
}
