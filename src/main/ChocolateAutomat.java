package main;

import java.io.PrintWriter;
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
		System.out.println("ChocolateAutomat.step called");
		Random r = new Random();
		for (Tile t: tile.getNeighbours()) {
			if(t.getAnimal() != null && r.nextInt() % 100 <= chance)
				t.getAnimal().scare();
		}
	}
	public void writeOut(PrintWriter pw){
		pw.println("ChocolateAutomat");
		pw.println("-ID: " + this.getID());
		pw.println("-chance: " + this.getChance());
		if (this.getTile() != null){
			pw.println("-tileID: " + this.getID());
		}
	}
}
