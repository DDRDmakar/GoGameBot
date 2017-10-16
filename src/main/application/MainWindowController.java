package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainWindowController extends Application {
	
	private Stage primaryStage;
	private SettingsController settings;
	
	public MainWindowController (SettingsController settings) {
		this.settings = settings;
	}
	
	@Override
	public void start(Stage s) throws Exception {
		primaryStage = s;
		
		primaryStage.setTitle("GO");
		
		primaryStage.show();
	}
	
	public void showWindow(String[] args) {
		launch(args);
	}
}
