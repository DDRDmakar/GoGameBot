package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SettingsWindow extends Application {
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

 

