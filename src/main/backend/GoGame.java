package backend;

import application.SettingsFXMLController;
import backend.field.GameField;
import javafx.scene.control.Button;
import application.SettingsContainer;
import java.util.HashMap;
import backend.field.GoNode;

public class GoGame {

// +====================[VARIABLES]====================+
	
	// map (id -> javaFX button)
	public static HashMap buttonIDs = new HashMap<Integer, Button>();
	// Game field
	public static GameField grid = new GameField();
	
// +====================[PRIVATE FUNCTIONS]====================+
	
	
	
	
	
	
// +====================[PUBLIC FUNCTIONS]====================+
	
	public static boolean playerSetStone(int ID) {
		GoNode currentNode = grid.grid.get(ID / SettingsContainer.height).get(ID % SettingsContainer.height);
		if (currentNode.getValue() == 0) {
			System.out.println("Set value 1");
			currentNode.setValue(1);
			return true;
		}
		else {
			System.out.println("Cell is used.");
			return false;
		}
	}
	
}
