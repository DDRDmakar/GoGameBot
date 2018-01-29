

package application;

import backend.GoGame;
import application.SettingsContainer;

import javafx.application.Application;
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

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class MainWindowFXMLController implements Initializable {

	// +====================[VARIABLES]====================+
	
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
		
		// Create grid
		gpanel = new GridPane();
		
		// Anchor grid to parent node
		AnchorPane.setTopAnchor(gpanel, 0.0);
		AnchorPane.setBottomAnchor(gpanel, 0.0);
		AnchorPane.setLeftAnchor(gpanel, 0.0);
		AnchorPane.setRightAnchor(gpanel, 0.0);
		
		// Place buttons on grid
		int i = 0;
		while (i < SettingsContainer.height) {
			int j = 0;
			while (j < SettingsContainer.width) {
				int ButtonID = i*SettingsContainer.width + j;
				Button currentB = createButton(ButtonID);
				// Add final button to grid pane
				gpanel.add(currentB, i, j);
				// Set graph cell i j the same ID, as button's
				GoGame.grid.grid.get(i).get(j).setID(ButtonID);
				// add button to the map (id -> button)
				GoGame.buttonIDs.put(ButtonID, currentB);
				++j;
			}
			++i;
		}
		
		//mainPane.getStyleClass().add("pane");
		//gpanel.getStyleClass().addAll("pane","grid");
		
		// gpanel.setGridLinesVisible(true);
		gpanel.setStyle("-fx-background-color: palegreen; -fx-padding: 10; -fx-hgap: 10; -fx-vgap: 10;");
		gpanel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		mainPane.getChildren().add(gpanel);
		
		
		initLables();
		
		reset();
	}
	
	private Button createButton(int id) {
		final Button temp = new Button();
		temp.setMaxSize(SettingsContainer.cellSize, SettingsContainer.cellSize);
		temp.setPrefSize(SettingsContainer.cellSize, SettingsContainer.cellSize);
		final int numButton = id;
		temp.setId("" + id);
		
		// prints button id to console, when it's pressed
		temp.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				// DEBUG
				System.out.println("Button pressed. id(" + temp.getId()  + ") =  " + numButton);
				// Set black stone
				if (GoGame.playerSetStone(new Integer(temp.getId()))) {
					// Set button icon
					setIcon(temp, "Black.png");
				}
			}
		});
		
		return temp;
	}
	
	private void setIcon(Button target, String path) {
		double siz = new Double(SettingsContainer.cellSize) * 0.6;
		Image currentImage = new Image(getClass().getClassLoader().getResourceAsStream(path), siz, siz, true, true);
		target.setGraphic(new ImageView(currentImage));
	}
}
