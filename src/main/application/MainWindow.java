package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Group;
import backend.GoGame;

public class MainWindow {

	private GoGame game;
	
	public MainWindow() throws Exception {
	
		javafx.scene.Node fxWindow = FXMLLoader.load(getClass().getClassLoader().getResource("main.fxml"));
		
		Stage stage = new Stage();
		stage.setTitle("GO");
		Group root = new Group();
		root.getChildren().add(fxWindow);
		stage.setScene(new Scene(root));
		stage.show();
	}
}
