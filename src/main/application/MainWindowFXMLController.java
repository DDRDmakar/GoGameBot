

package application;

import javafx.application.Application;
import backend.GoGame;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.*;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;


public class MainWindowFXMLController implements Initializable {

	// +====================[VARIABLES]====================+
	
	int i;
	GridPane gpanel;
	
	@FXML AnchorPane mainPane;
	
	// +====================[PRIVATE FUNCTIONS]====================+
	
	private void reset() {
		// ...
	}
	
	private void initLables() {
		// ...
	}
	
	// +====================[PUBLIC FUNCTIONS]====================+
	
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		i=0;
		gpanel = new GridPane();
		Pane root = new Pane();
		while(i<3){
			addButton();
		}
		mainPane.getChildren().add(gpanel);
		
		initLables();
		
		reset();
	}
	
	private void addButton() {
		i++;
		final Button temp = new Button("Button " + i);
		final int numButton= i;
		temp.setId("" + i);
		temp.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				System.out.println("id(" + temp.getId()  + ") =  " + numButton);
			}
		});
		gpanel.add(temp, i, 1);
	}
}
