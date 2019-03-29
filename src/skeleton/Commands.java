package skeleton;
import main.*;

public class Commands {
	private Manual manual = new Manual();
	
	public boolean commands(String cmd[]) {
		
		switch(cmd[0]) {
			// Creating parts of the game
			case "create":
				switch(cmd[1]) {
					case "tile":
						
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
	
}
