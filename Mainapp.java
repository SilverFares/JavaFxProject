package app;
	

import com.sun.glass.ui.Application;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;



public class Mainapp  extends javafx.application.Application {
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
			
			Scene scene= new Scene(root);
			primaryStage.setScene(scene);
			//primaryStage.initStyle(StageStyle.UNDECORATED);
			//primaryStage.setResizable(false);
			primaryStage.setTitle("Product List");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	
}

	
}
