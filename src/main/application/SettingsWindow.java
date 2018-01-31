package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
* Settings window loader class.
*/

public class SettingsWindow extends Application {
	
	/**
	* Loads FXML file of game settings and shows it.
	* @param stage - application parameter
	* (this class extends application)
	* @throws javafx.fxml.LoadException if FXML file is incorrect.
	*/
	
	@Override
	public void start(Stage stage) throws Exception {
		
		/*****************************/
		javafx.scene.Node fl =
			FXMLLoader.load(
				getClass().getClassLoader().getResource("settings.fxml")
			);
		/*****************************/
		
		stage.setTitle("Settings");
		Group root = new Group();
		root.getChildren().add(fl);
		stage.setScene(new Scene(root));
		
		stage.show();
	}
	
	public void showWindow(String[] args) {
		launch(args);
	}
}

 

