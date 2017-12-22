package backend.field;

import java.util.ArrayList;
import backend.field.GoNode;
import application.SettingsContainer;

public class GameField {
// +====================[VARIABLES]====================+
	
	public ArrayList<ArrayList<GoNode>> grid = new ArrayList<ArrayList<GoNode>>();
	
// +====================[PRIVATE FUNCTIONS]====================+
	
	
	
// +====================[PUBLIC FUNCTIONS]====================+
	
	// | Y
	// |
	// | *,0
	// |
	// |
	// |
	// |
	// |
	// |
	// |
	// |                    
	// | 0,0           0,*   X
	// *_______________________
	//
	
	// CONSTRUCTOR
	public GameField() {
		ArrayList currentRow = new ArrayList<GoNode>();
		
		// Create matrix of empty objects
		for (int i = 0; i < SettingsContainer.height; ++i) {
			currentRow = new ArrayList<GoNode>();
			
			for (int j = 0; j < SettingsContainer.width; ++j) {
				currentRow.add(new GoNode());
			}
			
			grid.add(currentRow);
		}
		
		// Make links to neighbours (Graph)
		for (int i = 0; i < SettingsContainer.height; ++i) { // As a vertical (Y)
			for (int j = 0; j < SettingsContainer.width; ++j) { // As a horizontal (X)
				GoNode curNode = grid.get(i).get(j);
				if (j > 0) curNode.setLeft(grid.get(i).get(j-1));
				if (j < SettingsContainer.width - 1) curNode.setRight(grid.get(i).get(j+1));
				if (i > 0) curNode.setBottom(grid.get(i-1).get(j));
				if (i < SettingsContainer.height - 1) curNode.setTop(grid.get(i+1).get(j));
			}
		}
	}
}
