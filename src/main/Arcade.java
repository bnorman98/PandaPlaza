package main;

import java.io.PrintWriter;
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

	public void writeOut(PrintWriter pw){
		pw.println("Arcade");
		pw.println("-ID: " + this.getID());
		pw.println("-chance: " + this.getChance());
		if (this.getTile() != null){
			pw.println("-tileID: " + this.getID());
		}
	}
}
