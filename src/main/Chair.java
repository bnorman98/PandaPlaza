package main;

import java.io.PrintWriter;
import java.util.Random;

public class Chair extends Thing {

	public Chair(){ chance = 100;}

	@Override
	public void step() {
		System.out.println("Chair.step called");
		for (Tile t: tile.getNeighbours()) {
			Random r = new Random();
			if(t.getAnimal() != null && r.nextInt() % 100 <= chance){
				t.getAnimal().goTo(tile);
				tile.getAnimal().sleep();
				break;
			}
		}
	}
	public void writeOut(PrintWriter pw){
		pw.println("Chair");
		pw.println("-ID: " + this.getID());
		pw.println("-chance: " + this.getChance());
		if (this.getTile() != null){
			pw.println("-tileID: " + this.getID());
		}
	}
}
