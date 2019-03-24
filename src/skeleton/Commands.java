package skeleton;
import main.*;

public class Commands {
	public boolean commands(String cmd[]) {
		if(cmd[0] != null) {
			switch (cmd[0]) {
				
				case "panda":
					try {
						// Panda Step Tests
						if(cmd[1].equals("step")) {
							
							// PandaStepOnTile
							if(cmd[2].equals("hard")) {
								pandaStepOnTile();
							}
							// PandaStepOnSoftTile
							else if(cmd[2].equals("soft")) {
								pandaStepOnSoftTile(Integer.parseInt(cmd[3]));
							}
							// PandaStepOnBrokenTile
							else if(cmd[2].equals("broken")) {
								pandaStepOnBrokenTile();
							}
							// PandaStepOnExit
							else if(cmd[2].equals("exit")) {
								pandaStepOnExit();
							}
							
						}
						// PandaFollow
						else if(cmd[1].equals("follow")) {
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
						else System.out.println("Invalid command");
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
						if(cmd[1].equals("step")) {
							
							// OrangutanStepOnTile
							if(cmd[2].equals("hard")) {
								orangutanStepOnTile();
							}
							// OrangutanStepOnSoftTile
							else if(cmd[2].equals("soft")) {
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
							else if(cmd[2].equals("exit")) {
								orangutanStepOnExit();
							}
							else System.out.println("Invalid command");
						}
						else if(cmd[1].equals("catch")) {
							orangutanCatchPanda();
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
	
	/*
	 * Realizes PandaStepOnTile use case
	 * Creates 2 Tiles and a Panda
	 * Steps Panda from one to the other
	 * Checks if Panda stepped successfully
	 */
	private void pandaStepOnTile() {
		// Initialising
		Panda panda = new Panda();
		Tile tile = new Tile();
		Tile nextTile = new Tile();
		tile.setNeighbours(nextTile);
		tile.setAnimal(panda);
		panda.setTile(tile);
		
		// Action
		panda.goTo(nextTile);
		
		// Test results
		if(tile.getAnimal() == null && nextTile.getAnimal() == panda)
			System.out.println("Panda stepped\n > Test succeeded");
		else if(tile.getAnimal() == panda)
			System.out.println("Panda did not step\n > Test failed");
		else System.out.println("Panda moved unexpectedly\n > Test failed");
	}

	/*
	 * Realizes PandaStepOnSoftTile use case
	 * Creates a Tile, a SoftTile with given life and a Panda
	 * Steps Panda form Tile To SoftTile
	 * Checks if SoftTile's life decreased and if Panda stepped successfully
	 */
	private void pandaStepOnSoftTile(int life) {
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
		
		if (tile.getAnimal() == panda && softTile.getAnimal() != panda) {
			System.out.println("Panda stayed on Tile");
			System.out.println(" > Test failed");
		}
		else if (tile.getAnimal() != panda) {
			System.out.println("Panda left Tile");
			if(softTile.getLife() != 0 && softTile.getAnimal() == panda)
				System.out.println("Panda arrived to SoftTile\n > Test succeeded");
			else if(softTile.getLife() == 0 && softTile.getAnimal() == null)
				System.out.println("SoftTile broke and Panda died\n > Test succeeded");
			else System.out.println(" > Test failed");
		}
		
	}
	
	/*
	 * Realises PandaStepOnBrokenTile use case
	 * Creates a Tile, a broken SoftTile (with a life of 0) and a Panda
	 * Steps Panda form Tile To SoftTile
	 * Checks if Panda moved and died
	 */
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
	
	/*
	 * Realises PandaStepOnExit use case
	 * Creates 2 Tiles, an EndPoint and a Panda
	 * Steps Panda on Endpoint
	 * Checks if Panda stepped on EndPoint and teleported to starting Tile
	 */
	private void pandaStepOnExit() {
		// Initialising
		Panda panda = new Panda();
		Tile start = new Tile();
		Tile tile = new Tile();
		EndPoint endPoint = new EndPoint(start);
		
		panda.setTile(tile);
		tile.setAnimal(panda);
		tile.setNeighbours(endPoint);
		endPoint.setNeighbours(tile);
		
		// Action
		panda.goTo(endPoint);
		
		// Test results
		if(tile.getAnimal() == panda)
			System.out.println("Panda did not move\n > Test failed");
		else if(endPoint.getAnimal() == panda)
			System.out.println("Panda stuck at EndPoint\n > Test failed");
		else if(start.getAnimal() == panda)
			System.out.println("Panda got to the start\n > Test succeeded");
		
	}
	
	/*
	 * Realises PandaFollow use case
	 * Creates 3 Tiles, an Orangutan and a Panda following it
	 * Steppes the Orangutan
	 * Checks if the Panda followed the Orangutan
	 */
	private void pandaFollow() {
		// Initialise
	    Orangutan orangutan = new Orangutan();
	    Panda panda = new Panda();
	    Tile orangTile = new Tile();
	    Tile pandaTile = new Tile();
	    Tile newTile = new Tile();

	    orangutan.setDir(0);

	    // Setting tiles and neighbours
	    orangTile.setNeighbours(newTile);
	    orangTile.setNeighbours(pandaTile);
	    pandaTile.setNeighbours(orangTile);
	    newTile.setNeighbours(orangTile);

	    // Setting animals on tiles
	    orangutan.setTile(pandaTile);
	    pandaTile.setAnimal(orangutan);
	    panda.setTile(orangTile);
	    orangTile.setAnimal(panda);
	    panda.getTouched(orangutan); // Catch panda and switch tiles

	    // Action
        orangutan.step();

        // Test results
        if (newTile.getAnimal() == orangutan){
            System.out.println("Orangutan stepped");
            if (orangTile.getAnimal() == panda){
                System.out.println("Panda followed\n > Test succeeded");
            } else {
                System.out.println("Panda did not follow\n > Test failed");
            }
        } else {
            System.out.println("Orangutan not stepped, Panda could not follow\n > Test failed");
        }
	}
	
	/*
	 * Realises PandaJump use case
	 * Creates a SoftTile and a JumpyPanda on it
	 * Makes JumpyPanda jump
	 * Checks if softTile's life decreased and JumpyPanda stayed on it
	 */
	private void pandaJump() {
		// Initialising
		JumpyPanda jPanda = new JumpyPanda();
		SoftTile softTile = new SoftTile();
		jPanda.setTile(softTile);
		softTile.setAnimal(jPanda);
		int initLife = softTile.getLife();
		
		// Action
		jPanda.jump();
		
		// Test results
		if(softTile.getAnimal() == jPanda) {
			System.out.println("JumpyPanda stayed on SoftTile");
			
			if(softTile.getLife() == initLife - 1)
				System.out.println("JumpyPanda jumped\n > Test succeeded");
			else System.out.println("JumpyPanda did not jump\n > Test failed");
		}
		else System.out.println("JumpyPanda left SoftTile\n > Test failed");
		
	}
	
	/*
	 * Realises PandaScare use case
	 * Creates 2 Tiles, an Orangutan and a ScaryPanda
	 * Scares ScaryPanda
	 * Checks if ScaryPanda let go of Orangutan
	 */
	private void pandaScare() {
		//Initialising
		ScaryPanda sPanda = new ScaryPanda();
		Orangutan orang = new Orangutan();
		Tile tPanda = new Tile();
		Tile tOrang = new Tile();

		sPanda.setTile(tOrang); //they switch tiles on getting touched
		orang.setTile(tPanda);
		tOrang.setAnimal(sPanda);
		tPanda.setAnimal(orang);

		tPanda.setNeighbours(tOrang);
		tOrang.setNeighbours(tPanda);

		sPanda.getTouched(orang); //sets follower and influencer, animals switch tiles

		// Action
		sPanda.scare();

		// Test results
		if (orang.getFollower() != null) {
			System.out.println("Panda did not scare\n > Test failed");
		} else {
			System.out.println("Panda scared successfully\n > Test succeeded");
		}

	}
	
	/*
	 * Realises PandaSleep use case
	 * Creates 3 Tiles, a Chair and a Panda
	 * Steps Panda next to Chair
	 * Checks if Panda was slept and whether it stepped while sleeping
	 */
	private void pandaSleep() {
	    // Initialise
		SleepyPanda slPanda = new SleepyPanda();
		Chair chair = new Chair();
		Tile tile = new Tile();
		Tile startTile = new Tile();
		Tile tileChair = new Tile();

		slPanda.setTile(startTile);
		startTile.setAnimal(slPanda);
		chair.setTile(tileChair);

		tile.setNeighbours(startTile);
		startTile.setNeighbours(tile);
		//tile.setNeighbours(tileChair);
		tileChair.setNeighbours(tile);

		// Action
        chair.step();
        slPanda.step();
        chair.step();
        slPanda.step();

        // Test results
        if (tileChair.getAnimal() == slPanda){
            System.out.println("SleepyPanda slept well in chair\n > Test succeeded");
        } else if (startTile.getAnimal() == slPanda) {
            System.out.println("SleepyPanda did not sleep\n > Test failed");
        } else if (tile.getAnimal() == slPanda) {
            System.out.println("SleepyPanda slept but not in chair\n > Test failed");
        }
        
	}
	
	/*
	 * Realises PandaLetGo use case
	 * Creates 3 Tiles, an Orangutan and 2 Pandas in a chain
	 * Letting go of influencer and follower
	 * Checks if Panda let go of influencer and follower
	 */
	private void pandaLetGo() {
		// Initialise
		Tile tOrangutan = new Tile();
		Tile tPanda1 = new Tile();
		Tile tLetgoPanda = new Tile();

		tOrangutan.setNeighbours(tLetgoPanda);
		tLetgoPanda.setNeighbours(tOrangutan);
		tPanda1.setNeighbours(tLetgoPanda);
		tLetgoPanda.setNeighbours(tPanda1);

		Orangutan orangutan = new Orangutan();
		Panda panda = new Panda();
		Panda letgoPanda = new Panda();

		orangutan.setTile(tOrangutan);
		panda.setTile(tPanda1);
		letgoPanda.setTile(tLetgoPanda);
		
		letgoPanda.getTouched(orangutan);
		panda.getTouched(orangutan);
		
		// Action
		letgoPanda.letGo();
		
		// Test results
		if (letgoPanda.getFollower() == null && orangutan.getFollower() == null)
			System.out.println("Panda released paws\n > Test succeeded");
		else
			System.out.println("Panda didn't release paws \n > Test failed");
		
	}
	
	// Orangutan functions
	
	/*
	 * Realises OrangutanStepOnTile use case
	 * Creates 2 Tiles and a Orangutan
	 * Steps Orangutan from one to the other
	 * Checks  if Orangutan stepped successfully
	 */
	private  void orangutanStepOnTile() {
		// Initialising
		Orangutan o = new Orangutan();
		Tile actualTile = new Tile();
		Tile nextTile = new Tile();
		actualTile.setNeighbours(nextTile);
		actualTile.setAnimal(o);
		o.setTile(actualTile);

		// Action
		o.goTo(nextTile);

		// Test results
		if(actualTile.getAnimal() == null && nextTile.getAnimal() == o)
			System.out.println("Orangutan stepped\n > Test succeeded");
		else if(actualTile.getAnimal() == o)
			System.out.println("Orangutan did not step\n > Test failed");
		else System.out.println("Orangutan moved unexpectedly\n > Test failed");
		
	}
	
	/*
	 * Realises OrangutanStepOnSoftTile use case
	 * Creates a Tile, a SoftTile with given life and an Orangutan
	 * Steps Orangutan form Tile To SoftTile
	 * Checks if SoftTile's life decreased and if Orangutan stepped successfully
	 */
	private  void orangutanStepOnSoftTile(int life) {	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ E R R O R  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		// Initialise
		Orangutan orangutan = new Orangutan();
		Tile tile = new Tile();
		SoftTile softtile = new SoftTile(life);
		orangutan.setTile(tile);
		tile.setAnimal(orangutan);
		tile.setNeighbours(softtile);
		softtile.setNeighbours(tile);

		// Tested action
		orangutan.goTo(softtile);

		// Test results
		if(tile.getAnimal() == orangutan)
			System.out.println("Orangutan did not move\n > Test failed");
		if(softtile.getAnimal() == orangutan)
			System.out.println("Orangutan moved, but still alive\n > Test succeeded");
		if(tile.getAnimal() == null && softtile.getAnimal() == null)
			System.out.println("Orangutan moved and died\n > Test succeeded");
		
	}
	
	/*
	 * Realises OrangutanStepOnBrokenTile use case
	 * Creates a Tile, a broken SoftTile (with a life of 0) and an Orangutan
	 * Steps Orangutan form Tile To SoftTile
	 * Checks if Orangutan moved and died
	 */
	private  void orangutanStepOnBrokenTile() {
		// Initialise
		Orangutan o = new Orangutan();
		Tile hard = new Tile ();
		SoftTile broken = new SoftTile(0);

		o.setTile(hard);
		broken.setAnimal(o);
		hard.setNeighbours(broken);
		broken.setNeighbours(hard);
		
		//Perform tested action
		o.goTo(broken);
		
		//Test results
		if(hard.getAnimal() == o)
		    System.out.println("Step failed \n > Test Failed");
        if(hard.getAnimal() == null && broken.getAnimal() == null)
            System.out.println("Step completed\nOrangutan died \n > Test succeeded");
		if(hard.getAnimal() == null && broken.getAnimal() == o)
			System.out.println("Step completed\nOrangutan is still alive \n > Test Failed");
	}
	
	/*
	 * Realises OrangutanStepOnExit use case
	 * Creates 4 Tiles, an EndPoint and an Orangutan with 2 follower Pandas
	 * Steps Orangutan on Endpoint
	 * Checks if Orangutan stepped on EndPoint and teleported to starting Tile
	 * Checks if Orangutan got equal amount of score to the number of its followers
	 */
	private  void orangutanStepOnExit() {
		System.out.println("orangutanStepOnExit called");
		Orangutan orangutan = new Orangutan();
		Panda panda = new Panda();
		Panda panda2 = new Panda();
		Tile inTile = new Tile();
		Tile oTile = new Tile();
		Tile pTile = new Tile();
		Tile p2Tile = new Tile();
		EndPoint endPoint = new EndPoint(inTile);

		orangutan.setTile(oTile);
		orangutan.setFollower(panda);
		panda.setFollower(panda2);
		panda.setTile(pTile);
		panda2.setTile(p2Tile);
		oTile.setAnimal(orangutan);
		oTile.setNeighbours(endPoint);
		pTile.setAnimal(panda);
		p2Tile.setAnimal(panda2);
		endPoint.setNeighbours(oTile);
		
		int initScore = orangutan.getScore();
		int initFollowerCnt = orangutan.countFollowers();

		// Action
		orangutan.goTo(endPoint);

		// Test results
		if(oTile.getAnimal() == orangutan)
			System.out.println("Orangutan did not move\n > Test failed");
		else if(endPoint.getAnimal() == orangutan)
			System.out.println("Orangutan stuck az EndPoint\n > Test failed");
		else if(inTile.getAnimal() == orangutan) {
			System.out.println("Orangutan got to the start");
			if(orangutan.getScore() == initScore + initFollowerCnt)
				System.out.println("Orangutan got its score\n > Test succeeded");
			else
				System.out.println("Orangutan didn't get its score\n > Test failed");
		}
		
	}
	
	/*
	 * Realises OrangutanCatchPanda use case
	 * Creates
	 * _
	 * Checks _
	 */
	private void orangutanCatchPanda() {
	    // Initialise
		Orangutan orang = new Orangutan();
		Panda oldPanda = new Panda();
		Panda newPanda = new Panda();
		Tile orangTile = new Tile();
		Tile oldpTile = new Tile();
		Tile newpTile = new Tile();

		// Setting tiles and neighbours
		oldpTile.setNeighbours(orangTile);
		orangTile.setNeighbours(oldpTile);
		orangTile.setNeighbours(newpTile);
		newpTile.setNeighbours(orangTile);

		// Setting animals on tiles
		oldpTile.setAnimal(orang);
		orang.setTile(oldpTile);
		orangTile.setAnimal(oldPanda);
		oldPanda.setTile(orangTile);
		newpTile.setAnimal(newPanda);
		newPanda.setTile(newpTile);

		oldPanda.getTouched(orang); // Sets a follower for the orangutan, they switch tiles

		// Action
        newPanda.getTouched(orang);

        // Test results
        if (orang.getFollower() == newPanda){
            System.out.println("New panda caught");
            if (newpTile.getAnimal() == orang && orangTile.getAnimal() == newPanda){
                System.out.println("Switched tiles correctly\n > Test succeeded");
            } else if (newpTile.getAnimal() == newPanda && orangTile.getAnimal() == orang){
                System.out.println("Did not switch tiles\n > Test failed");
            } else {
                System.out.println("Moved somehow, not correctly\n > Test failed");
            }
        } else {
            System.out.println("No new pandas caught\n > Test failed");
        }
	}
	
	// Thing functions
	
	/*
	 * Realises WardrobeStep use case
	 * Creates
	 * _
	 * Checks _
	 */
	private void wardrobeStep() {
		// Initialise
		Wardrobe wardrobe1 = new Wardrobe();
		Wardrobe wardrobe2 = new Wardrobe();

		Tile t1 = new Tile();
		Tile t2 = new Tile();
		wardrobe1.setTile(t1);
		wardrobe2.setTile(t2);
		wardrobe1.setPair(wardrobe2);
		wardrobe2.setPair(wardrobe1);

		Tile tStartPanda = new Tile();
		tStartPanda.setNeighbours(t1);
		t1.setNeighbours(tStartPanda); //Not necessary

		Panda p = new Panda();
		p.setTile(tStartPanda);
		tStartPanda.setAnimal(p);

		// Action
		p.step();
		wardrobe1.step();

		// Test results
		if (t2.getAnimal() == p)
			System.out.println("Panda went through the wardrobes\n > Test succeeded");
		else
			System.out.println("Panda didn't arrive at the end of the wardrobe\n > Test failed");

		//System.out.println("wardrobeStep called");
	}
	
	/*
	 * Realises ArcadeRing use case
	 * Creates
	 * _
	 * Checks _
	 */
	private void arcadeRing() {
		JumpyPanda jPanda = new JumpyPanda();
		Arcade arc = new Arcade();
		SoftTile softTile = new SoftTile();
		Tile tile = new Tile();
		jPanda.setTile(softTile);
		softTile.setAnimal(jPanda);
		arc.setTile(tile);
		tile.setNeighbours(softTile);
		softTile.setNeighbours(tile);
		int initLife = softTile.getLife();

		// Action
		arc.step();

		// Test results
		if (tile.getNeighbourAt(0).getAnimal() == jPanda) {
			if (softTile.getLife() == initLife - 1)
				System.out.println("JumpyPanda found, made it jump\n > Test succeeded");
			else System.out.println("JumpyPanda found, could not make is jump\n > Test failed");
		}
		else {
			System.out.print("Did not find JumpyPanda\n > Test failed");
		}
	}
	
	/*
	 * Realises ChocolateAutomatBeep use case
	 * Creates
	 * _
	 * Checks _
	 */
	private void chocolateAutomatBeep() {
		//Initialising
		ScaryPanda sPanda = new ScaryPanda();
		Orangutan orang = new Orangutan();
		ChocolateAutomat chocoAutomat = new ChocolateAutomat();
		Tile tPanda = new Tile();
		Tile tOrang = new Tile();
		Tile tAutomat = new Tile();

		sPanda.setTile(tOrang); //they switch tiles on getting touched
		orang.setTile(tPanda);
		chocoAutomat.setTile(tAutomat);
		tOrang.setAnimal(sPanda);
		tPanda.setAnimal(orang);

		tPanda.setNeighbours(tOrang);
		tPanda.setNeighbours(tAutomat);
		tOrang.setNeighbours(tPanda);
		tAutomat.setNeighbours(tPanda);

		sPanda.getTouched(orang); //sets follower and influencer, animals switch tiles

		// Action
		chocoAutomat.step();

		// Test results
		System.out.println("ChocloateAutomat beeped successfully");
		if (orang.getFollower() != null){
			System.out.println("Panda found, could not scare\n > Test failed");
		} else {
			System.out.println("Panda found, scared successfully\n > Test succeeded");
		}
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
