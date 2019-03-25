package main;

import java.util.Random;

public class Arcade extends Thing {
	
	public Arcade(){chance = 100;}
	
	@Override
	public void step() {
		System.out.println("Arcade.step called");
		for (Tile t: tile.getNeighbours()) {
			Random r = new Random();
			if(t.getAnimal() != null && r.nextInt() % 100 <= chance)
				t.getAnimal().jump();
		}
	}
	
}
