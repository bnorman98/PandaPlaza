package skeleton;
import main.*;

public class Commands {
	public void commands(String cmd[]){
		if(cmd[0] != null) {
			switch (cmd[0]) {
				
				case "panda":
					if(cmd[1] != null) {
						// Panda Step Tests
						if (cmd[1].equals("step")) {
							// PandaStepOnTile
							if (cmd[2].equals("hard")) {
								pandaStep();
							}
							// PandaStepOnSoftTile
							else if (cmd[2].equals("soft")) {
								if(cmd[3] != null) {
									pandaStepOnSoftTile(Integer.parseInt(cmd[3]));
								}
								else
									pandaStepOnSoftTile(20);
							}
							// PandaStepOnBrokenTile
							else if(cmd[2].equals("broken")) {
								pandaStepOnBrokenTile();
							}
							// PandaStepOnExit
							else if (cmd[2].equals("exit")) {
								pandaStepOnExit();
							}
							
						}
						// PandaFollow
						else if (cmd[1].equals("follow")) {
							pandaFollow();
						}
						// PandaJump
						else if(cmd[1].equals("jump")) {
							pandaJump();
						}
						// PandaScare
						else if(cmd[1].equals("scare")) {
							pandaScare();
						}
						// PandaSleep
						else if(cmd[1].equals("sleep")) {
							pandaSleep();
						}
						// PandaLetGo
						else if(cmd[1].equals("letgo")) {
							pandaLetGo();
						}
					}
					break;
					
				case "orangutan":
					System.out.println("Orangutan called");
					break;
					
				case "ChocolateAutomat":
					System.out.println("ChocolateAutomat called");
					break;
					
				case "arcade":
					System.out.println("Arcade called");
					break;
					
				case "chair":
					System.out.println("Chair called");
					break;
				
				default:
					System.out.println("Invalid command");
					break;
			}
		}
		
	}
	
	// This is not ready
	private void pandaStep() {
		Panda p = new Panda();
		p.step();
	}

	private void pandaStepOnSoftTile(int life){
		Panda p = new Panda();
		Tile t1 = new Tile();
		SoftTile t2 = new SoftTile(life);
		t1.setNeighbours(t2);
		t2.setNeighbours(t1);
		p.step();

		if (t1.getAnimal() != null) t1.stepped();
		if(t2.getAnimal() != null) t2.stepped();
		
		if (t1.getAnimal() != p)
			System.out.println("Panda stayed on Tile");
		if (t2.getAnimal() != null) {
			if (t2.getAnimal() == p)
				System.out.println("Panda arrived on SoftTile");
		}
		else
			System.out.println("SoftTile broke and Panda died");
		System.out.println("SoftTile's life = " + t2.getlife());
		

		/*
		t1.setAnimal(p);

		if (t1.getAnimal() != null) t1.stepped();
		if(t2.getAnimal() != null) t2.stepped();

		t1.setAnimal(null);
		t2.setAnimal(p);

		t1.stepped();
		t2.stepped();

		System.out.println(t2.getlife());
		if (t1.getAnimal() != p)
			System.out.println("Panda Peter went the tile");
		if (t2.getAnimal() == p)
			System.out.println("Panda Peter arrived on your SoftTile");
		*/
	}
	
	private void pandaStepOnBrokenTile() {
		// Creating components
		Panda panda = new Panda();
		Tile tile = new Tile();
		SoftTile brokenTile = new SoftTile(0);
		
		// Setting values
		tile.setAnimal(panda);
		tile.setNeighbours(brokenTile);
		brokenTile.setNeighbours(tile);
		panda.setTile(tile);
		
		// Action
		panda.goTo(brokenTile);
		
		// Reviewing results
		if(tile.getAnimal() == panda)
			System.out.println("Test failed - Panda did not move");
		if(brokenTile.getAnimal() == panda)
			System.out.println("Test failed - Panda moved, but still alive");
		if(tile.getAnimal() == null && brokenTile.getAnimal() == null)
			System.out.println("Test succeeded - Panda moved and died");
		
	}
	
	private void pandaStepOnExit() {
		// Creating components
		Panda panda = new Panda();
		Tile start = new Tile();
		Tile inTile = new Tile();
		EndPoint endPoint = new EndPoint(inTile);
		
		// Setting values
		panda.setTile(start);
		start.setAnimal(panda);
		start.setNeighbours(endPoint);
		
		// Action
		panda.goTo(endPoint);
		
		// Reviewing results
		// ...
		
	}
	
	// Empty test functions
	
	private void pandaFollow() {
		System.out.println("pandaFollow called");
	}
	private void pandaJump() {
		System.out.println("pandaJump called");
	}
	private void pandaScare() {
		System.out.println("pandaScare called");
	}
	private void pandaSleep() {
		System.out.println("pandaSleep called");
	}
	private void pandaLetGo() {
		System.out.println("pandaLetGo called");
	}
	
	/*
	 * Insert test functions here and call them in commands(cmd:String)
	 */
}
