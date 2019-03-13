package skeleton;
import main.*;

public class Commands {
	public boolean commands(String cmd[]){
		if(cmd[0] != null) {
			switch (cmd[0]) {
				
				case "panda":
					try {
						// Panda Step Tests
						if (cmd[1].equals("step")) {
							
							// PandaStepOnTile
							if (cmd[2].equals("hard")) {
								pandaStepOnTile();
							}
							// PandaStepOnSoftTile
							else if (cmd[2].equals("soft")) {
								pandaStepOnSoftTile(Integer.parseInt(cmd[3]));
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
					catch(ArrayIndexOutOfBoundsException oob) {
						System.out.println("Missing arguments");
						oob.printStackTrace();
					}
					break;
					
				case "orangutan":
					System.out.println("Orangutan called");
					try {
						// Orangutan Step Tests
						if (cmd[1].equals("step")) {
							
							// OrangutanStepOnTile
							if (cmd[2].equals("hard")) {
								orangutanStepOnTile();
							}
							// OrangutanStepOnSoftTile
							else if (cmd[2].equals("soft")) {
								if(cmd[3] != null) {
									orangutanStepOnSoftTile(Integer.parseInt(cmd[3]));
								}
								else
									orangutanStepOnSoftTile(20);
							}
							// OrangutanStepOnBrokenTile
							else if(cmd[2].equals("broken")) {
								orangutanStepOnBrokenTile();
							}
							// OrangutanStepOnExit
							else if (cmd[2].equals("exit")) {
								orangutanStepOnExit();
							}
						}
						
					}
					catch(ArrayIndexOutOfBoundsException oob) {
						System.out.println("Missing arguments");
						oob.printStackTrace();
					}
					break;
				
				case "wardrobe":
					wardrobeStep();
					break;
				
				case "arcade":
					arcadeRing();
					break;
				
				case "chocolateAutomat":
					chocolateAutomatBeep();
					break;
					
				case "exit":
					return true;
					
				default:
					System.out.println("Invalid command");
					break;
			}
			
		}
		return false;
	}
	
	// Panda functions
	private void pandaStepOnTile() {
		Panda p = new Panda();
		p.step();
	}

	private void pandaStepOnSoftTile(int life){
		// Initialising
		Panda panda = new Panda();
		Tile tile = new Tile();
		SoftTile softTile = new SoftTile(life);
		panda.setTile(tile);
		tile.setAnimal(panda);
		tile.setNeighbours(softTile);
		softTile.setNeighbours(tile);
		
		// Tested action
		panda.goTo(softTile);
		
		// Test results
		System.out.println("SoftTile's life = " + softTile.getLife());
		
		if (tile.getAnimal() == panda && softTile.getAnimal() != panda){
			System.out.println("Panda stayed on Tile");
			System.out.println(" > Test failed");
		}
		else if (tile.getAnimal() != panda){
			System.out.println("Panda left Tile");
			if(softTile.getLife() != 0 && softTile.getAnimal() == panda)
				System.out.println("Panda arrived to SoftTile\n > Test succeeded");
			else if(softTile.getLife() == 0 && softTile.getAnimal() == null)
				System.out.println("SoftTile broke and Panda died\nTest succeeded");
			else System.out.println(" > Test failed");
		}
		
	}
	
	// This is false correct, panda does not die since Animal.die() is not implemented yet
	private void pandaStepOnBrokenTile() {
		// Initialising
		Panda panda = new Panda();
		Tile tile = new Tile();
		SoftTile brokenTile = new SoftTile(0);

		tile.setAnimal(panda);
		tile.setNeighbours(brokenTile);
		brokenTile.setNeighbours(tile);
		panda.setTile(tile);
		
		// Action
		panda.goTo(brokenTile);
		
		// Test results
		if(tile.getAnimal() == panda)
			System.out.println("Panda did not move\n > Test failed");
		if(brokenTile.getAnimal() == panda)
			System.out.println("Panda moved, but still alive\n > Test failed");
		if(tile.getAnimal() == null && brokenTile.getAnimal() == null)
			System.out.println("Panda moved and died\n > Test succeeded");
		
	}
	
	private void pandaStepOnExit() {
		// Initialising
		Panda panda = new Panda();
		Tile start = new Tile();
		Tile tile = new Tile();
		EndPoint endPoint = new EndPoint(start);
		
		panda.setTile(tile);
		tile.setAnimal(panda);
		tile.setNeighbours(endPoint);
		
		// Action
		panda.goTo(endPoint);
		
		// Test results
		if(tile.getAnimal() == panda)
			System.out.println("Panda did not move\n > Test failed");
		else if(endPoint.getAnimal() == panda)
			System.out.println("Panda stuck az EndPoint\n > Test failed");
		else if(start.getAnimal() == panda)
			System.out.println("Panda got to the start\n > Test succeeded");
		
	}
	
	private void pandaFollow() {
		System.out.println("pandaFollow called");
	}
	private void pandaJump() {
		System.out.println("pandaJump called");
		
		// Initialising
		JumpyPanda jPanda = new JumpyPanda();
		SoftTile softTile = new SoftTile();
		jPanda.setTile(softTile);
		softTile.setAnimal(jPanda);
		int initLife = softTile.getLife();
		
		// Action
		jPanda.scare();
		
		// Test results
		if(softTile.getAnimal() == jPanda) {
			System.out.println("JumpyPanda stayed on SoftTile");
			
			if(softTile.getLife() == initLife - 1)
				System.out.println("JumpyPanda jumped\n > Test succeeded");
			else System.out.println("JumpyPanda did not jump\n > Test failed");
		}
		else System.out.println("JumpyPanda left SoftTile\n > Test failed");
		
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
	
	// Orangutan functions
	private  void orangutanStepOnTile() {
		System.out.println("orangutanStepOnTile called");
	}
	private  void orangutanStepOnSoftTile(int life) {
		System.out.println("orangutanStepOnSoftTile called");
	}
	private  void orangutanStepOnBrokenTile() {
		System.out.println("orangutanStepOnBrokenTile called");
	}
	private  void orangutanStepOnExit() {
		System.out.println("orangutanStepOnExit called");
	}
	
	// Thing functions
	private void wardrobeStep() {
		System.out.println("wardrobeStep called");
	}
	private void arcadeRing() {
		System.out.println("arcadeRing called");
	}
	private void chocolateAutomatBeep() {
		System.out.println("chocolateAutomatBeep called");
	}
	
	// Not a real use case
	// Made this just to test it
	private void countFollowersTest(){
		Panda p1 = new Panda();
		Panda p2 = new Panda();
		Panda p3 = new Panda();
		Panda p4 = new Panda();
		Panda p5 = new Panda();
		Orangutan orangutan = new Orangutan();
		orangutan.setFollower(p1);
		p1.setFollower(p2);
		p2.setFollower(p3);
		p3.setFollower(p4);
		p4.setFollower(p5);
		System.out.println("Followers: " + orangutan.countFollowers());
	}
	
	/*
	 * Insert test functions here and call them in commands(cmd:String)
	 */
}
