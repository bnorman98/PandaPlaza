package skeleton;
import main.*;

public class Commands {
	

	public void commands(String cmd[]){

		switch (cmd[0]) {
			default: System.out.println("You cant test this object. It hates you, just like your mom does!");
			case "panda":
				if (cmd[1].equals("step")) {
					PandaStep();
				}
				else if (cmd[1].equals("S"))
					PandaStepOnSoftTile();
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
		}

		
	}

	public void PandaStep(){
		Panda p = new Panda();
		p.step();
	}

	public void PandaStepOnSoftTile(){
		Panda p = new Panda();
		Tile t1 = new Tile();
		SoftTile t2 = new SoftTile();
		t1.setNeighbours(t2);
		t2.setNeighbours(t1);
		p.step();

		if (t1.getAnimal() != null) t1.stepped();
		if(t2.getAnimal() != null) t2.stepped();

		System.out.println(t2.getlife());
		if (t1.getAnimal() != p)
			System.out.println("Panda Peter went the tile");
		if (t2.getAnimal() == p)
			System.out.println("Panda Peter arrived on your SoftTile");

		/*t1.setAnimal(p);

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
			System.out.println("Panda Peter arrived on your SoftTile"); */
	}
	/*
	 * Insert test functions here and call them in commands(cmd:String)
	 */
}
