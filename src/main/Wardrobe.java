package main;

import java.io.PrintWriter;
import java.util.ArrayList;

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
		pw.println("-pairID: " + pair.getID());
		pw.println("-chance: " + this.getChance());
		if (this.getTile() != null){
			pw.println("-tileID: " + this.getID());
		}
	}

	public void readIn(ArrayList<String> lines, int idx){
		for (int i=idx+1;i<lines.size();i++){
			String[] parts = lines.get(i).split(" ");
			if (parts[0].charAt(0) != '-'){
				i = lines.size();
			}
			switch (parts[0]){
				case "-tileID:":
					this.setTile(Game.getInstance().getTileContained(Integer.parseInt(parts[1])));
					break;
				case "-chance:":
					this.chance = Integer.parseInt(parts[1]);
					break;
				case "-pairID:":
					this.setPair((Wardrobe)Game.getInstance().getThingContained(Integer.parseInt(parts[1]))); //szavamat adom, hogy az
					break;
				default: break;
			}
		}
	}
}
