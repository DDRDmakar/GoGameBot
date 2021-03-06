

package application;

import backend.GoGame;
import application.SettingsContainer;
import backend.field.GoNode;

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
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.*;

import java.lang.IllegalStateException;

/**
* FXML controller of main game window.
* Works in GUI thread.
* Event handlers and graphical functions.
*/

public class MainWindowFXMLController implements Initializable {

	// +====================[VARIABLES]====================+
	
	// Cell field
	GridPane gpanel;
	
	// Background pane of game
	@FXML private AnchorPane mainPane;
	
	// Labels
	@FXML private Label compTaken, compUsed, userTaken, userUsed;
	
	@FXML private Button passButton;
	
	// +====================[PRIVATE FUNCTIONS]====================+
	
	/**
	* Updates label contents (game score).
	* No parameters.
	*/
	
	private void updateLabels() {
		compTaken.setText(String.valueOf(SettingsContainer.computerKilledCells));
		userTaken.setText(String.valueOf(SettingsContainer.userKilledCells));
		compUsed.setText(String.valueOf(SettingsContainer.computerUsedStones));
		userUsed.setText(String.valueOf(SettingsContainer.userUsedStones));
	}
	
	// +====================[PUBLIC FUNCTIONS]====================+
	
	/**
	* Adds GridPane onto game window template
	* and button into each cell of grid.
	* @param url, @param resourcebundle - Initializable parameters.
	* (this class implements Initializable)
	*/
	
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		
		// Create grid
		gpanel = new GridPane();
		
		// Anchor grid to parent node
		AnchorPane.setTopAnchor(gpanel, 0.0);
		AnchorPane.setBottomAnchor(gpanel, 100.0);
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
		
		// gpanel.setGridLinesVisible(true);
		gpanel.setStyle("-fx-background-color: palegreen; -fx-padding: 10; -fx-hgap: 10; -fx-vgap: 10;");
		gpanel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		mainPane.getChildren().add(gpanel);
		
		updateLabels();
	}
	
	
	/**
	* Create one button, add style and
	* create event handler for button.
	* Each button associates with element of game array
	* @see backend.field.GameField
	* @param id - ID of new button and of game array element.
	* @return new button object
	*/
	
	private Button createButton(int id) {
		final Button temp = new Button();
		// Size of cell
		temp.setMaxSize(SettingsContainer.cellSize, SettingsContainer.cellSize);
		temp.setPrefSize(SettingsContainer.cellSize, SettingsContainer.cellSize);
		// Olive-oil color
		temp.setBackground(new Background(new BackgroundFill(Color.web("#FF8C00"), CornerRadii.EMPTY, Insets.EMPTY)));
		final int numButton = id;
		temp.setId("" + id);
		
		// prints button id to console, when it's pressed
		temp.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				
				/*********************************************/
				
				// DEBUG
				System.out.println("Button pressed. id(" + temp.getId()  + ") =  " + numButton);
				// Set black stone
				if (GoGame.playerSetStone(new Integer(temp.getId()))) {
					SettingsContainer.userUsedStones++;
					// Set button icon
					setIcon(temp, "Black.png");
					
					GoGame.removeClosedGroups(1);
					GoGame.removeClosedGroups(2);
					
					// Computer sets white stones
					// Exception, if user passes first step
					try {
						GoNode currentComputerStep = GoGame.computerGo();
						if (currentComputerStep != null) {
							SettingsContainer.computerUsedStones++;
							Button computerGuiCell = GoGame.buttonIDs.get(currentComputerStep.getID());
							setIcon(computerGuiCell, "White.png");
						}
						else { System.out.println("Computer passes."); }
					}
					catch(IllegalStateException ex) { }
					
					GoGame.removeClosedGroups(2);
					GoGame.removeClosedGroups(1);
					
					redraw();
					
					updateLabels();
					
					if (gameEnd()) {
						Alert alert = new Alert(AlertType.CONFIRMATION);
						alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
						alert.setTitle("Игра окончена.");
						alert.setHeaderText("Игра окончена.");
						alert.showAndWait();
					}
				}
				
				/*********************************************/
				
			} // end handle
		}); // end setOnAction
		
		return temp;
	}
	
	
	/**
	* Set image onto button.
	* Image size is 1/2 from button size.
	* @param target - button
	* @param path   - path to image file.
	*/
	
	private void setIcon(Button target, String path) {
		double siz = new Double(SettingsContainer.cellSize) * 0.5;
		Image currentImage = new Image(getClass().getClassLoader().getResourceAsStream(path), siz, siz, true, true);
		target.setGraphic(null);
		target.setGraphic(new ImageView(currentImage));
	}
	
	/**
	* Refreshes contents of all game cells (in GUI).
	* Black, if cell is occupied by user
	* White, if cell is occupied by computer
	* Nothing, if cell is free.
	* 
	* No parameters.
	*/
	
	private void redraw() {
		// Iterate through buttons
		for (int i : GoGame.buttonIDs.keySet()) {
			Button b = GoGame.buttonIDs.get(i);
			// Clear node in the graph
			int nCol = i % SettingsContainer.width;
			int nRow = i / SettingsContainer.width;
			int cellValue = GoGame.grid.grid.get(nRow).get(nCol).getValue();
			b.setGraphic(null);
			
			switch (cellValue) {
				case 1: { setIcon(b, "Black.png"); break; }
				case 2: { setIcon(b, "White.png"); break; }
			}
		}
	}
	
	/**
	* Detect end of game.
	* @return true if user or computer used all stones
	* OR if there are no more double-free cells.
	*/
	
	private boolean gameEnd() {
		if (SettingsContainer.userUsedStones >= SettingsContainer.nStones) return true;
		if (SettingsContainer.computerUsedStones >= SettingsContainer.nStones) return true;
		
		for (int i = 0; i < SettingsContainer.height; ++i) {
			for (int j = 0; j < SettingsContainer.width; ++j) {
				GoNode curNode = GoGame.grid.grid.get(j).get(i);
				// if curNode is free cell and has at least one free neighbour, return false;
				if (
					curNode.getValue() == 0 && ( // If cell is free
						(curNode.getLeft()   != null && curNode.getLeft().getValue()   == 0) || // And one of neighbors is free
						(curNode.getRight()  != null && curNode.getRight().getValue()  == 0) ||
						(curNode.getTop()    != null && curNode.getTop().getValue()    == 0) ||
						(curNode.getBottom() != null && curNode.getBottom().getValue() == 0)
					)
				) return false;
			}
		}
		
		return true;
	}
	
	/**
	* User passes step
	* (no arguments)
	*/
	
	@FXML void pass() {
		try {
			GoNode currentComputerStep = GoGame.computerGo();
			if (currentComputerStep != null) {
				SettingsContainer.computerUsedStones++;
				Button computerGuiCell = GoGame.buttonIDs.get(currentComputerStep.getID());
				setIcon(computerGuiCell, "White.png");
			}
			else { System.out.println("Computer passes."); }
		}
		catch(IllegalStateException ex) { }
		
		GoGame.removeClosedGroups(2);
		GoGame.removeClosedGroups(1);
		
		redraw();
		
		updateLabels();
		
		if (gameEnd()) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
			alert.setTitle("Игра окончена.");
			alert.setHeaderText("Игра окончена.");
			alert.showAndWait();
		}
	}
}
