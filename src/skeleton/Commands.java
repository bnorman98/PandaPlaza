package skeleton;
import main.*;

import java.awt.*;
import java.text.ParseException;

public class Commands {
	private Manual manual = new Manual();
	
	public boolean commands(String cmd[]) {
		
		switch(cmd[0]) {
			// Creating parts of the game
			case "create":
				switch(cmd[1]) {
					
					// create tile [ID] [type] [life]
					// life is optional
					case "tile":
						Tile newTile = createTile(cmd);
						
						// Testing creation
						try {
							System.out.println(newTile.toString());
						}
						catch (NullPointerException e) {
							System.out.println("Creation failed");
						}
						
						break;
						
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
				
			// Deleting parts of the game
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
				
				break;
			
			case "endgame":
				
				break;
			
			case "results":
				
				break;
			
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
}
