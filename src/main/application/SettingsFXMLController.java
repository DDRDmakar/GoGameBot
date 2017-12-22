
package application;

import application.SettingsContainer;
import application.MainWindow;
import javafx.application.Application;
import backend.GoGame;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.*;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class SettingsFXMLController implements Initializable {

	// +====================[VARIABLES]====================+
	
	@FXML private Button fx_button_ok, fx_button_cancel, fx_button_reset;
	
	@FXML private TextField FX_SIZE_X, FX_SIZE_Y, FX_SIZE_CELL;
	
	// +====================[PRIVATE FUNCTIONS]====================+
	
	private void reset() { // Here put into text area default text.
		FX_SIZE_X.setText("12");
		FX_SIZE_Y.setText("12");
		FX_SIZE_CELL.setText("50");
	}
	
	private void initLables() {
		// ...
	}
	
	@FXML
	void startGame(ActionEvent event) {
		
		boolean somethingWrong = false;
		
		try {
			SettingsContainer.width = Integer.parseInt(FX_SIZE_X.getText());
			SettingsContainer.height = Integer.parseInt(FX_SIZE_Y.getText());
			SettingsContainer.cellSize = Integer.parseInt(FX_SIZE_CELL.getText());
		}
		catch (Exception e) { somethingWrong = true; }
		if (
			somethingWrong ||
			SettingsContainer.width < 1 || SettingsContainer.width > 100 ||
			SettingsContainer.height < 1 || SettingsContainer.height > 100 ||
			SettingsContainer.cellSize < 1 || SettingsContainer.width > 1000
		) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
			alert.setTitle("Error!");
			alert.setHeaderText("Incorrect game settings.");
			alert.setContentText("It looks, like your settings contain incorrect data. Please, correct it and retry.");
			alert.showAndWait();
		}
		else {
			try { new MainWindow(); }
			catch (Exception e) { e.printStackTrace(); }
		}
	}
	
	@FXML
	void leaveGame(ActionEvent event) {
		System.exit(0);
	}
	
	@FXML
	void restoreDefaultSettings(ActionEvent event) {
		reset();
	}
	
	// +====================[PUBLIC FUNCTIONS]====================+
	
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		
		// ...
		
		initLables();
		
		reset();
	}
}
