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
				System.out.println("SoftTile broke and Panda died\nTest succeeded");
			else System.out.println(" > Test failed");
		}
		
	}
	
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

	private void pandaFollow() {
		// Initialise
	    Orangutan orang = new Orangutan();
	    Panda panda = new Panda();
	    Tile orangTile = new Tile();
	    Tile pandaTile = new Tile();
	    Tile newTile = new Tile();

	    orang.setDir(0);

	    //setting tiles and neighbours
	    orangTile.setNeighbours(newTile);
	    orangTile.setNeighbours(pandaTile);
	    pandaTile.setNeighbours(orangTile);
	    newTile.setNeighbours(orangTile);

	    //setting animals on tiles
	    orang.setTile(pandaTile);
	    pandaTile.setAnimal(orang);
	    panda.setTile(orangTile);
	    orangTile.setAnimal(panda);
	    panda.getTouched(orang); // catch panda and switch tiles

	    // Action
        orang.step();

        // Test results
        if (newTile.getAnimal() == orang){
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
		if (orang.getFollower() != null){
			System.out.println("Panda did not scare\n > Test failed");
		} else {
			System.out.println("Panda scared successfully\n > Test succeeded");
		}

	}
	private void pandaSleep() {
	    // Initialise
		SleepyPanda slPanda = new SleepyPanda();
		Chair chair = new Chair();
		Tile tile = new Tile();
		Tile tile2 = new Tile();
		Tile tileChair = new Tile();

		slPanda.setTile(tile2);
		tile2.setAnimal(slPanda);
		chair.setTile(tileChair);

		tile.setNeighbours(tile2);
		tile2.setNeighbours(tile);
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
        } else if (tile2.getAnimal() == slPanda) {
            System.out.println("SleepyPanda did not sleep\n > Test failed");
        } else if (tile.getAnimal() == slPanda) {
            System.out.println("SleepyPanda slept but not in chair\n > Test failed");
        }
	}
	private void pandaLetGo() {

		Tile tOrangutan = new Tile();
		Tile tPanda1 = new Tile();
		Tile tLetgoPanda = new Tile();

		tOrangutan.setNeighbours(tLetgoPanda);
		tLetgoPanda.setNeighbours(tOrangutan);
		tPanda1.setNeighbours(tLetgoPanda);
		tLetgoPanda.setNeighbours(tPanda1);

		Orangutan orangutan = new Orangutan();
		Panda panda1 = new Panda();
		Panda letgoPanda = new Panda();

		orangutan.setTile(tOrangutan);
		panda1.setTile(tPanda1);
		letgoPanda.setTile(tLetgoPanda);

		orangutan.getTouched(letgoPanda);
		orangutan.getTouched(panda1);
		letgoPanda.letGo();

		if (letgoPanda.getFollower() == null && orangutan.getFollower() == null)
			System.out.println("Panda released paws\n >Test succeeded");
		else
			System.out.println("Panda didn't released paws \n >Test failed");
	}
	
	// Orangutan functions
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
	
	private  void orangutanStepOnSoftTile(int life) {
		//Initialization
		Orangutan orangutan = new Orangutan();
		Tile tile = new Tile();
		SoftTile softtile = new SoftTile(life);
		orangutan.setTile(tile);
		tile.setAnimal(orangutan);
		tile.setNeighbours(softtile);
		softtile.setNeighbours(tile);

		//Tested action
		orangutan.goTo(softtile);

		// Test results
		if(tile.getAnimal() == orangutan)
			System.out.println("Orangutan did not move\n > Test failed");
		if(softtile.getAnimal() == orangutan)
			System.out.println("Orangutan moved, but still alive\n > Test succeeded");
		if(tile.getAnimal() == null && softtile.getAnimal() == null)
			System.out.println("Orangutan moved and died\n > Test succeeded");



	}
	
	private  void orangutanStepOnBrokenTile() {
		// Initialising
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
	
	private  void orangutanStepOnExit() {
		System.out.println("orangutanStepOnExit called");
		Orangutan o = new Orangutan();
		Tile start = new Tile();
		Tile tile = new Tile();
		EndPoint endPoint = new EndPoint(start);

		o.setTile(tile);
		tile.setAnimal(o);
		tile.setNeighbours(endPoint);
		endPoint.setNeighbours(tile);

		// Action
		o.goTo(endPoint);

		// Test results
		if(tile.getAnimal() == o)
			System.out.println("Orangutan did not move\n > Test failed");
		else if(endPoint.getAnimal() == o)
			System.out.println("Orangutan stuck az EndPoint\n > Test failed");
		else if(start.getAnimal() == o)
			System.out.println("Orangutan got to the start\n > Test succeeded");
	}
	
	private void orangutanCatchPanda() {
	    // Initialise
		Orangutan orang = new Orangutan();
		Panda oldPanda = new Panda();
		Panda newPanda = new Panda();
		Tile oTile = new Tile();
		Tile opTile = new Tile();
		Tile npTile = new Tile();

		// setting tiles and neighbours
		opTile.setNeighbours(oTile);
		oTile.setNeighbours(opTile);
		oTile.setNeighbours(npTile);
		npTile.setNeighbours(oTile);

		//setting animals on tiles
		opTile.setAnimal(orang);
		orang.setTile(opTile);
		oTile.setAnimal(oldPanda);
		oldPanda.setTile(oTile);
		npTile.setAnimal(newPanda);
		newPanda.setTile(npTile);

		oldPanda.getTouched(orang); //sets a follower for the orangutan, they switch tiles

		// Action
        newPanda.getTouched(orang);

        //Test results
        if (orang.getFollower() == newPanda){
            System.out.println("New panda caught");
            if (npTile.getAnimal() == orang && oTile.getAnimal() == newPanda){
                System.out.println("Switched tiles correctly\n > Test succeeded");
            } else if (npTile.getAnimal() == newPanda && oTile.getAnimal() == orang){
                System.out.println("Did not switch tiles\n > Test failed");
            } else {
                System.out.println("Moved somehow, not correctly\n > Test failed");
            }
        } else {
            System.out.println("No new pandas caught\n > Test failed");
        }
	}
	
	// Thing functions
	private void wardrobeStep() {
		//Init
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

		//Action
		p.step();
		wardrobe1.step();

		//Test results
		if (t2.getAnimal() == p)
			System.out.println("Panda went through the wardrobes\n > Test succeeded");
		else
			System.out.println("Panda didn't arrive at the end of the wardrobe\n > Test failed");

		//System.out.println("wardrobeStep called");
	}
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
