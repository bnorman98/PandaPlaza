package main;

import java.io.PrintWriter;

public class Wardrobe extends Thing{
	private Wardrobe pair;
	
	public Wardrobe(){}
	
	public Wardrobe(Wardrobe pair) {
		this.pair = pair;
	}
	
	public void setPair(Wardrobe newPair) {
		System.out.println("Wardrobe.setPair called");
		this.pair = newPair;
		newPair.pair = this;
	}
	
	@Override
	public void step() {
		System.out.println("Wardrobe.step called");
		if(tile.getAnimal() != null)
			tile.getAnimal().goTo(pair.tile);
	}
	public void writeOut(PrintWriter pw){
		pw.println("Wardrobe");
		pw.println("-ID: " + this.getID());
		pw.println("-chance: " + this.getChance());
		if (this.getTile() != null){
			pw.println("-tileID: " + this.getID());
		}
	}
}
