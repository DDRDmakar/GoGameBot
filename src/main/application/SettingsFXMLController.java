
package application;

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
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class SettingsFXMLController implements Initializable {

	// +====================[VARIABLES]====================+
	
	@FXML private Button fx_button_ok, fx_button_cancel, fx_button_reset;
	
	@FXML private TextField FX_SIZE_X, FX_SIZE_Y, FX_SIZE_CELL;
	
	// +====================[PRIVATE FUNCTIONS]====================+
	
	private void reset() { // Here put into text area default text.
		FX_SIZE_X.getText();
		FX_SIZE_Y.getText();
		FX_SIZE_CELL.getText();
	}
	
	private void initLables() {
		// ...
	}
	
	@FXML
	void startGame(ActionEvent event) {
		try { new MainWindow(); }
		catch (Exception e) { e.printStackTrace(); }
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
