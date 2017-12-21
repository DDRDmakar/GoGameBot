package backend;

import application.SettingsFXMLController;
import backend.field.GameField;

public class GoGame {

// +====================[VARIABLES]====================+
	
	// Game field
	GameField grid;
	
// +====================[PRIVATE FUNCTIONS]====================+
	
// +====================[PUBLIC FUNCTIONS]====================+
	
	// CONSTRUCTOR
	public GoGame(SettingsFXMLController settings) {
		grid = new GameField();
	}
}
