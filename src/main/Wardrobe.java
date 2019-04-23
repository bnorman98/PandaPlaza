package main;

import java.io.PrintWriter;

/**
 * Represents a wardrobe in the game
 */
public class Wardrobe extends Thing{
	
	/**
	 * Wardrobe's pair
	 * Who enters the wardrobe appears at this wardrobe
	 */
	private Wardrobe pair;
	
	/**
	 * Default constructor
	 */
	public Wardrobe() {}
	
	/**
	 * Constructor
	 * @param pair Wardrobe's pair
	 */
	public Wardrobe(Wardrobe pair) {
		this.pair = pair;
	}
	
	/**
	 * Pair attribute's setter
	 */
	public void setPair(Wardrobe newPair) {
		System.out.println("Wardrobe.setPair called");
		this.pair = newPair;
		newPair.pair = this;
	}
	
	/**
	 * Teleports the animal standing in this wardrobe to it's pair
	 */
	@Override
	public void step() {
		System.out.println("Wardrobe.step called");
		if(tile.getAnimal() != null)
			tile.getAnimal().goTo(pair.tile);
	}
	
	/**
	 * Writes out the object to file
	 */
	public void writeOut(PrintWriter pw){
		pw.println("Wardrobe");
		pw.println("-ID: " + this.getID());
		pw.println("-chance: " + this.getChance());
		if (this.getTile() != null){
			pw.println("-tileID: " + this.getID());
		}
	}
}
