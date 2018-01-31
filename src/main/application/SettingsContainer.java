package application;
import javafx.stage.Stage;

/**
* Storage of important game parameters, which are needed by different classes
*/

public class SettingsContainer {
	// Game field size
	public static int height, width, cellSize;
	// Players score
	public static int userKilledCells, computerKilledCells, userUsedStones, computerUsedStones;
	// Maximum allowed number of stones.
	public static int nStones;
}

 


