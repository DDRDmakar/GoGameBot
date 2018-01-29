package application;

import application.SettingsContainer;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Group;
import backend.GoGame;
import javafx.scene.layout.GridPane;

public class MainWindow {
	
	public MainWindow() throws Exception {
		
		javafx.scene.Node fxWindow = FXMLLoader.load(getClass().getClassLoader().getResource("main.fxml"));
		
		
		
		System.out.println("Gаme field height, width = ");
		System.out.println(GoGame.grid.grid.size());
		System.out.println(GoGame.grid.grid.get(0).size());
		
		Stage stage = new Stage();
		stage.setTitle("GO");
		Group root = new Group();
		root.getChildren().add(fxWindow);
		
		Scene mainWindowScene = new Scene(root);
		mainWindowScene.getStylesheets().add("MainWindowStyles.css");
		stage.setScene(mainWindowScene);
		
		stage.show();
	}
}
