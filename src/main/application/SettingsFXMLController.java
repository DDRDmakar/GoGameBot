
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

/**
* FXML controller of settings window.
* Works in GUI thread.
*/

public class SettingsFXMLController implements Initializable {

	// +====================[VARIABLES]====================+
	
	@FXML private Button fx_button_ok, fx_button_cancel, fx_button_reset;
	
	@FXML private TextField FX_SIZE_X, FX_SIZE_CELL, FX_N_STONES;
	
	// +====================[PRIVATE FUNCTIONS]====================+
	
	/**
	* RESET button action
	* Reset text areas contents to defaults.
	* (field size by X, by Y, one cell size in pixels and maximum count of used stones)
	* No parameters
	*/
	
	private void reset() { // Here put into text area default text.
		FX_SIZE_X.setText("18");
		FX_SIZE_CELL.setText("40");
		FX_N_STONES.setText("180");
	}
	
	/**
	* OK button action.
	* Check, if game parameters are correct and load them into SettingsContainer class.
	* @see application.SettingsContainer
	*/
	
	@FXML
	void startGame(ActionEvent event) {
		
		boolean somethingWrong = false;
		
		try {
			SettingsContainer.height = Integer.parseInt(FX_SIZE_X.getText());
			SettingsContainer.width = Integer.parseInt(FX_SIZE_X.getText());
			SettingsContainer.cellSize = Integer.parseInt(FX_SIZE_CELL.getText());
			SettingsContainer.nStones = Integer.parseInt(FX_N_STONES.getText());
		}
		catch (Exception e) { somethingWrong = true; }
		if (
			somethingWrong ||
			SettingsContainer.width < 1 || SettingsContainer.width > 100 ||
			SettingsContainer.height < 1 || SettingsContainer.height > 100 ||
			SettingsContainer.cellSize < 1 || SettingsContainer.width > 1000 ||
			SettingsContainer.nStones < 1
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
	
	/**
	* CANCEL button action
	* Ends all threads.
	*/
	@FXML
	void leaveGame(ActionEvent event) {
		System.exit(0);
	}
	
	/**
	* RESET button event handler
	*/
	
	@FXML
	void restoreDefaultSettings(ActionEvent event) {
		reset();
	}
	
	// +====================[PUBLIC FUNCTIONS]====================+
	
	/**
	* Set default values into text fields.
	* @param url, @param resourcebundle - Initializable parameters.
	* (this class implements Initializable)
	*/
	
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		reset();
	}
}
