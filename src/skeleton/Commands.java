package skeleton;
import main.*;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.text.ParseException;

public class Commands {
	private Game game;
	private Manual manual = new Manual();
	
	public boolean commands(String[] cmd) {
		
		switch(cmd[0]) {
			// Creating parts of the game
			case "create":
				switch(cmd[1]) {
					case "game":
						game = Game.getInstance();
						System.out.println("Game created");
						break;
					
					// create tile [ID] [type] [life]
					// life is optional
					case "tile":
						// Creating Tile
						Tile newTile = createTile(cmd);
						
						// (Testing creation) and Adding Tile to the Game
						try {
							System.out.println(newTile.toString());
							game.addTile(newTile);
						}
						catch (NullPointerException e) {
							e.printStackTrace();
							System.out.println("Creation failed");
						}
						
						break;
						
					// TODO Implement creations below
					case "orangutan":
						break;
						
					case "sleepy":
						break;
						
					case "scary":
						break;
						
					case "jumpy":
						break;
						
					case "wardrobes":
						break;
						
					case "chair":
						break;
						
					case "automat":
						break;
						
					case "arcade":
						break;
					
					default:
						System.out.println("Invalid command");
						break;
				}
				break;
				
			// Modifying object details
			case "mod":
				switch(cmd[1]) {
					// mod tile [ID] [property] [value]*
					case "tile":
						modifyTile(cmd);
						break;
						
					// TODO Implement modifications below
					case "orangutan":
						break;
						
					case "panda":
						break;
						
					default:
						System.out.println("Invalid argument [object name]");
				}
				break;
				
			// Deleting parts of the game
			// TODO Implement deletions below
			case "delete":
				switch (cmd[1]) {
					case "tile":
						break;
						
					case "animal":
						break;
						
					case "thing":
						break;
					
					default:
						System.out.println("Invalid command");
						break;
				}
				break;
				
			case "startgame":
				game.startGame();
				break;
			
			case "endgame":
				game.endGame();
				break;
			
			// Printing the current state of the game
			// Probably after game ended
			case "results":
				break;
			
			// TODO Update manual - LAST STEP!
			case "help":
				manual.showInstructions();
				break;
			
			case "exit":
				return true;
			
			default:
				System.out.println("Invalid command");
				break;
		}
		return false;
	}
	
	@Nullable
	private Tile createTile(String cmd[]){
		boolean isCreated = false;
		boolean idSet = false;
		Tile tile = null;
		
		try {
			if(cmd[3].equals("hard")) {
				tile = new Tile();
				isCreated = true;
			}
			
			else if(cmd[3].equals("soft")) {
				if(cmd[4] == null)
					tile = new SoftTile();
				else
					tile = new SoftTile(Integer.parseInt(cmd[4]));
				
				isCreated = true;
			}
			else System.out.println("Invalid argument [type]");
			
			// Setting ID
			if (isCreated) {
				tile.setID(Integer.parseInt(cmd[2]));
				idSet = true;
			}
		}
		catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Missing arguments");
		}
		catch(NumberFormatException e) {
			System.out.println("Invalid argument [ID] or [life]");
		}
		
		if(isCreated && idSet)
			return tile;
		else
			return null;
	}
	
	private void modifyTile(String[] cmd) {
		// Finding Tile which is to be modified
		Tile modTile;
		int modID;
		try {
			// Checking if the Game contains the Tile
			modID = Integer.parseInt(cmd[2]);
			if(game.isContained(modID)) {
				// Storing the Tile for further usages
				modTile = game.getTileContained(modID);
			}
			else {
				System.out.println("No Tile found in Game with given ID");
				return;
			}
		}
		catch(NumberFormatException e) {
			System.out.println("Invalid argument [ID]");
			return;
		}
		
		if(cmd[3].equals("neighbours")) {
			// Counting argument list length
			int argCnt;
			for(argCnt = 4; cmd[argCnt] != null; argCnt++);
			
			// Adding neighbours to Tile
			for(int i = 4; i < argCnt; i++) {
				int neighbourID = Integer.parseInt(cmd[i]);
				
				// Checking if the Game contains the neighbour Tile
				if(game.isContained(neighbourID)) {
					// Creating neighbour connection
					modTile.addNeighbour(game.getTileContained(neighbourID));
				}
			}
			
		}
		else if(cmd[3].equals("life")) {
			// TODO Implement changing tile's life
		}
		else
			System.out.println("Invalid argument [property]");
	}
	
}
