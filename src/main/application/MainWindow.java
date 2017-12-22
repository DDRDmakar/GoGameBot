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

	private GoGame game;
	
	public MainWindow() throws Exception {
		game = new GoGame();
		
		javafx.scene.Node fxWindow = FXMLLoader.load(getClass().getClassLoader().getResource("main.fxml"));
		
		System.out.println("Gаme field height, width = ");
		System.out.println(game.grid.grid.size());
		System.out.println(game.grid.grid.get(0).size());
		
		// Set game field size
		for (int i = 0; i < SettingsContainer.height; ++i) {
			for (int j = 0; j < SettingsContainer.width; ++j) {
				//@ Write field constructor in GoGame class
				// Make button, set it's size
				// // /// // // // //   /// //  /// // // // Moved into FXML handler
				
				// Add button into game object
				
				// Add image on button (or circle)
				// Set image size on button
				// https://www.quora.com/How-do-I-set-size-of-a-image-inside-button-in-javafx-Is-it-possible-to-do-this-in-css
				// Add button to the pane
			}
		}
		
		Stage stage = new Stage();
		stage.setTitle("GO");
		Group root = new Group();
		root.getChildren().add(fxWindow);
		stage.setScene(new Scene(root));
		stage.show();
	}
}
