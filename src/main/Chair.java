package main;

import java.util.Random;

public class Chair extends Thing {

	public Chair(){ chance = 100;}

	@Override
	public void step() {
		for (Tile t: tile.getNeighbours()) {
			Random r = new Random();
			if(t.getAnimal() != null && r.nextInt() % 100 <= chance){
				t.getAnimal().goTo(tile);
				tile.getAnimal().sleep();
				break;
			}
		}
	}
	
}
