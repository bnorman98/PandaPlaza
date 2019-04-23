package prototype;

import main.*;

public class Commands {
	private Game game = Game.getInstance();
	private Manual manual = new Manual();
	
	public boolean commands(String[] cmd) {
		
		switch(cmd[0]) {
			case "save":
				game.serialize(cmd[1]);
				break;
			
			case "load":
				// Find method to test path
				game.deserialize(cmd[1]);
				break;
			
			case "startgame":
				game.runGame();
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
	
}
