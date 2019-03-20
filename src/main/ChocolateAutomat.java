package main;

import java.util.Random;

public class ChocolateAutomat extends Thing {
	
	public ChocolateAutomat() {
		chance = 100;
	}
	
	public ChocolateAutomat(int chance) {
		if(chance <= 100 && chance >= 0)
			this.chance = chance;
		else this.chance = 100;
	}
	
	@Override
	public void step() {
		Random r = new Random();
		for (Tile t: tile.getNeighbours()) {
			if(t.getAnimal() != null && r.nextInt() % 100 <= chance)
				t.getAnimal().scare();
		}
	}
	
}
