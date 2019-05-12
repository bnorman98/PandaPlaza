package prototype;

import main.*;

/**
 * The Commands class manages the commands
 * given in the console
 * They can control the game
 */
public class Commands {
	private Game game = Game.getInstance();
	private Manual manual = new Manual();

	/**
	 * It reads the commands
	 * and calls the methods
	 * @param cmd The input command
	 */
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
				System.out.println("The program is empty. StartGame executed.");
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
