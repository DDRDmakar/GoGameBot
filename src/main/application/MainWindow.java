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

/**
* Main window loader class.
*/
public class MainWindow {
	
	/**
	* Loads FXML file of game field and shows it.
	* No parameters.
	* @throws javafx.fxml.LoadException if FXML file is incorrect.
	*/
	public MainWindow() throws Exception {
		
		// Load FXML basic game window (without cells)
		javafx.scene.Node fxWindow = FXMLLoader.load(getClass().getClassLoader().getResource("main.fxml"));
		
		// Create new stage and add game window there
		Stage stage = new Stage();
		stage.setTitle("GO");
		Group root = new Group();
		root.getChildren().add(fxWindow);
		
		// Add graphics styles
		Scene mainWindowScene = new Scene(root);
		mainWindowScene.getStylesheets().add("MainWindowStyles.css");
		stage.setScene(mainWindowScene);
		
		// Draw
		stage.show();
	}
}
