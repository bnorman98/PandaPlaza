package skeleton;
import main.*;

public class Commands {
	public void commands(String cmd[]){
		if(cmd[0] != null) {
			switch (cmd[0]) {
				
				case "panda":
					// Panda Step
					if (cmd[1] != null && cmd[1].equals("step")) {
						if (cmd[2].equals("hard")) {
							pandaStep();
						} else if (cmd[2].equals("soft")) {
							if(cmd[3] != null) {
								pandaStepOnSoftTile(Integer.parseInt(cmd[3]));
							}
							else
								pandaStepOnSoftTile(20);
						} else if (cmd[2].equals("endpoint")) {
							pandaStepOnEndPoint();
						}
						
					}
					break;
					
				case "orangutan":
					System.out.println("Leave alone that fucking Orangutan u moron");
					break;
					
				case "ChocolateAutomat":
					System.out.println("U called ChocolateAutomat BITCH");
					break;
					
				case "arcade":
					System.out.println("Arcade is before you,  dickhead. Not in the A B C");
					break;
					
				case "chair":
					System.out.println("Chair would be very sick under you, fatass");
					break;
				
				default:
					System.out.println("Invalid command");
					break;
			}
		}
		
	}

	public void pandaStep(){
		Panda p = new Panda();
		p.step();
	}

	public void pandaStepOnSoftTile(int life){
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
	
	public void pandaStepOnEndPoint(){}
	
	/*
	 * Insert test functions here and call them in commands(cmd:String)
	 */
}
