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
        primaryStage.setWidth(640);
        primaryStage.setHeight(632);
        //primaryStage.setResizable(false);
        primaryStage.show();
         
    }

    public static void main(String[] args) {
        launch(args);
    }
}
