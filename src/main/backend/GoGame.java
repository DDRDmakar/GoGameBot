package backend;

import application.SettingsFXMLController;
import backend.field.GameField;
import javafx.scene.control.Button;
import application.SettingsContainer;
import java.util.*;
import backend.field.GoNode;
import java.lang.IllegalStateException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
* Class, which manages all game processes (backend).
* Contains realization of operations and searching on the game graph.
*/

public class GoGame {

// +====================[VARIABLES]====================+
	
	// map (id -> javaFX button)
	public static HashMap<Integer, Button> buttonIDs = new HashMap<Integer, Button>();
	// Game field
	public static GameField grid = new GameField();
	
	// Variables for redraw() function
	static boolean removeResult;
	static boolean removeIsClosed;
	
// +====================[PRIVATE FUNCTIONS]====================+
	
	/**
	* Finds all groups of cells, occupied by given user.
	* Group of cells should be connected with each other vertically or horizontally.
	* @param value - value of GoNode to search for.
	*     0 - empty cell
	*     1 - user
	*     2 - computer
	* @return list of sets of nodes. List of groups. Each set contains nodes from each group.
	*/
	
	// Get list of groups
	private static ArrayList<HashSet<GoNode>> getGroupsList(int value) {
		// Collection of groups
		ArrayList<HashSet<GoNode>> storage = new ArrayList<HashSet<GoNode>>();
		// Already used nodes
		HashSet<GoNode> used = new HashSet<GoNode>();
		
		HashSet<GoNode> currentGroup = new HashSet<GoNode>();
		
		// Check all cells. If its value == value and it was not used before, start searching group from it.
		for (int i = 0; i < SettingsContainer.height; ++i) {
			for (int j = 0; j < SettingsContainer.width; ++j) {
				GoNode startNode = grid.grid.get(i).get(j);
				if (startNode.getValue() == value && !used.contains(startNode)) {
					
					/************************************************************************************/
					
					Stack<GoNode> currentChain = new Stack<GoNode>();     // Chain of nodes, which are being checked at the moment
					
					// Add first node to the used list
					used.add(startNode);
					// Add first node to the chain
					currentChain.push(startNode);
					
					// While we have nodes to check
					while (!currentChain.empty()) {
						// Take next node to check
						GoNode currentNode = currentChain.pop();
						
						currentGroup.add(currentNode);
						
						if (currentNode.getLeft() != null && currentNode.getLeft().getValue() == value && !used.contains(currentNode.getLeft())) {
							currentChain.push(currentNode.getLeft());
						}
						
						if (currentNode.getRight() != null && currentNode.getRight().getValue() == value && !used.contains(currentNode.getRight())) {
							currentChain.push(currentNode.getRight());
						}
						
						if (currentNode.getTop() != null && currentNode.getTop().getValue() == value && !used.contains(currentNode.getTop())) {
							currentChain.push(currentNode.getTop());
						}
						
						if (currentNode.getBottom() != null && currentNode.getBottom().getValue() == value && !used.contains(currentNode.getBottom())) {
							currentChain.push(currentNode.getBottom());
						}
						
						used.add(currentNode);
						
					} // end while
					
					/************************************************************************************/
					
					storage.add(currentGroup);
					currentGroup = new HashSet<GoNode>();
					
				} // end if
				
			} // for 2
		} // for 1
		
		
		return storage;
	}
	
	
// +====================[PUBLIC FUNCTIONS]====================+
	
	/**
	* Manage user's step.
	* Set cell black, if it's free.
	* @param ID - ID of cell to set it black
	* @return true if cell is clear
	* @return false if cell is already used
	*/
	
	public static boolean playerSetStone(int ID) {
		GoNode currentNode = grid.grid.get(ID / SettingsContainer.height).get(ID % SettingsContainer.height);
		if (currentNode.getValue() == 0) {
			currentNode.setValue(1);
			return true;
		}
		else {
			System.out.println("Cell is used.");
			return false;
		}
	}
	
	
	private static class GroupComparator implements Comparator<HashSet<GoNode>> {
		@Override
		public int compare(HashSet<GoNode> item1, HashSet<GoNode> item2) {
			return countDame(item1) - countDame(item2);
		}
		
		private int countDame(HashSet<GoNode> item) {
			int result = 0;
			for (GoNode noda : item) {
				if (noda.getLeft()   != null && noda.getLeft().getValue()   == 0) ++result;
				if (noda.getRight()  != null && noda.getRight().getValue()  == 0) ++result;
				if (noda.getTop()    != null && noda.getTop().getValue()    == 0) ++result;
				if (noda.getBottom() != null && noda.getBottom().getValue() == 0) ++result;
			}
			return result;
		}
	}
	
	/**
	* Function, which chooses, what cell would be better for computer to occupy.
	* No parameters.
	* Get list of all groups, select the weakest group of all and try to kill it.
	* @throws IllegalStateException and sets random cell white, if field is clear.
	* @return link to node, which computer occupied.
	*/
	
	// Signal for computer to make a step
	public static GoNode computerGo() {
		
		ArrayList<HashSet<GoNode>> groups = getGroupsList(1);
		
		// Maybe when user passes first step.
		if (groups.isEmpty()) {
			// Take random cell
			Random rand1 = new Random();
			int randomWidth = rand1.nextInt(SettingsContainer.width);
			Random rand2 = new Random();
			int randomHeight = rand2.nextInt(SettingsContainer.height);
			
			grid.grid.get(randomHeight).get(randomWidth).setValue(2);
			
			throw new IllegalStateException("No user's cell groups found.");
		}
		
		// TODO sort by dame count (free cells around)
		// groups.sort(Comparator.comparing(HashSet::size));
		groups.sort(new GroupComparator());
		
		// Find cell to fill
		
		GoNode chosen = null;
		
		double d = new Random().nextDouble(); // random value in range 0.0 - 1.0
		
		// Find cell to surround user's structure
		
		
		for (int groupPosition = 0; groupPosition < groups.size(); ++groupPosition) {
			HashSet<GoNode> weakGroup = groups.get(groupPosition);
			ArrayList<GoNode> weakGroupAvailableCells = new ArrayList<GoNode>();
			
			// n is node
			weakGroup.forEach((n) -> {
				if (n.getLeft()   != null && n.getLeft().getValue()   == 0) weakGroupAvailableCells.add(n.getLeft());
				if (n.getRight()  != null && n.getRight().getValue()  == 0) weakGroupAvailableCells.add(n.getRight());
				if (n.getTop()    != null && n.getTop().getValue()    == 0) weakGroupAvailableCells.add(n.getTop());
				if (n.getBottom() != null && n.getBottom().getValue() == 0) weakGroupAvailableCells.add(n.getBottom());
			});
			
			// If player placed stone into killzone
			if (weakGroupAvailableCells.size() == 0) {
				chosen = null;
				continue;
			}
			
			Random rand = new Random();
			int randomIndex = rand.nextInt(weakGroupAvailableCells.size());
			
			int goodIndex = randomIndex;
			chosen = weakGroupAvailableCells.get(goodIndex);
			
			int surroundRatio = 4;
			int indexOfThree = -1; // Index of free cell, surrounded by 3 enemies
			// Choose cell, not surrounded by 3 or 4 user's stones
			for (int counter = randomIndex; counter < randomIndex + weakGroupAvailableCells.size(); ++counter) {
				goodIndex = counter % weakGroupAvailableCells.size();
				GoNode currentNode = weakGroupAvailableCells.get(goodIndex);
				surroundRatio = 0;
				if (currentNode.getLeft() == null   || currentNode.getLeft().getValue()   == 1) ++surroundRatio;
				if (currentNode.getRight() == null  || currentNode.getRight().getValue()  == 1) ++surroundRatio;
				if (currentNode.getTop() == null    || currentNode.getTop().getValue()    == 1) ++surroundRatio;
				if (currentNode.getBottom() == null || currentNode.getBottom().getValue() == 1) ++surroundRatio;
				
				if (surroundRatio < 3) break;
				if (surroundRatio == 3) indexOfThree = goodIndex;
			}
			
			if (surroundRatio == 4) {
				if (indexOfThree == -1) {
					chosen = null; // Pass
				}
				else {
					chosen = weakGroupAvailableCells.get(indexOfThree); // Bad choice
				}
			}
			else chosen = weakGroupAvailableCells.get(goodIndex); // Good choice
			
			if (chosen != null) break;
			
		} // end for
		
		// Set computer's step;
		if (chosen != null) chosen.setValue(2);
		
		return chosen;
	}
	
	/**
	* Debug function.
	* Draw game array in console
	*/
	
	public static String show() {
		String result = new String();
		for (int i = 0; i < SettingsContainer.height; ++i) {
			result += "[";
			for (int j = 0; j < SettingsContainer.width; ++j) {
				result += (" " + grid.grid.get(j).get(i).getValue());
			}
			result += "]\n";
		}
		return result;
	}
	
	/**
	* Delete groups, which are killed by other player.
	* (groups, which don't have access to free cells)
	* @param value - GoNode value to search for.
	* @return true, if any group was killed,
	* @return false, if not.
	*/
	
	public static boolean removeClosedGroups(int value) {
		ArrayList<HashSet<GoNode>> groups = getGroupsList(value);
		
		removeResult = false;
		removeIsClosed = true;
		
		// Check each group
		groups.forEach((currentGroup) -> {
			currentGroup.forEach((n) -> {
				// Check, if there are free cells around
				if (n.getLeft()   != null && n.getLeft().getValue()   == 0) removeIsClosed = false;
				if (n.getRight()  != null && n.getRight().getValue()  == 0) removeIsClosed = false;
				if (n.getTop()    != null && n.getTop().getValue()    == 0) removeIsClosed = false;
				if (n.getBottom() != null && n.getBottom().getValue() == 0) removeIsClosed = false;
				if (removeIsClosed == false) return;
			});
			// If group is killed, remove it;
			if (removeIsClosed) {
				removeResult = true;
				currentGroup.forEach((n) -> {
					n.setValue(0);
					// Increment player's score (killed cells)
					switch (value) {
						case 1: { SettingsContainer.computerKilledCells ++;     break; }
						case 2: { SettingsContainer.userKilledCells ++; break; }
					}
				});
			}
			
			removeIsClosed = true;
		});
		
		return removeResult;
	}
}
