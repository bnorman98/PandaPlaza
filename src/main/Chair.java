package main;

import java.util.Random;

public class Chair extends Thing {
	
	private int chance = 20;
	
	@Override
	public void step() {
		for (Tile t: tile.getNeighbours()) {
			Random r = new Random();
			if(t.getAnimal() != null && r.nextInt() % 100 >= chance){
				t.getAnimal().goTo(tile);
				t.getAnimal().sleep();
				break;
			}
		}
	}
	
}
