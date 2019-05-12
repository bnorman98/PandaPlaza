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
	private boolean justGot = false;
	
	/**
	 * Default constructor
	 */
	public Wardrobe() {
		texturePath = "res/wardrobe.png";
	}
	
	/**
	 * Constructor
	 * @param pair Wardrobe's pair
	 */
	public Wardrobe(Wardrobe pair) {
		texturePath = "res/wardrobe.png";
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
		if(tile.getAnimal() != null && !justGot) {
			tile.getAnimal().goTo(pair.tile);
			pair.justGot = true;
			System.out.println(this.getTile().getID()+ " pair: "+pair.getTile().getID());
		}
		else if (tile.getAnimal() != null && justGot){
			justGot = false;
		}
	}
	
	/**
	 * Writes out the object to file
	 */
	public void writeOut(PrintWriter pw){
		pw.println("Wardrobe");
		pw.println("-ID: " + this.getID());
		pw.println("-PairID: " + pair.getID());
		pw.println("-Chance: " + this.getChance());
		if (this.getTile() != null){
			pw.println("-TileID: " + this.getTile().getID());
		}
	}

	/**
	 * An assistant method
	 * Helps the deserialization
	 * Knows the scheme of the deserialization
	 * @param lines The lines that contain the informations about the Wardrobe
	 * @param idx Index of the Wardrobe
	 */
	public void readIn(ArrayList<String> lines, int idx){
		for (int i=idx+1;i<lines.size();i++){
			String[] parts = lines.get(i).split(" ");
			if (parts[0].charAt(0) != '-'){
				i = lines.size();
			}
			switch (parts[0]){
				case "-TileID:":
					this.setTile(Game.getInstance().getTileContained(Integer.parseInt(parts[1])));
					break;
				case "-Chance:":
					this.chance = Integer.parseInt(parts[1]);
					break;
				case "-PairID:":
					this.setPair((Wardrobe)Game.getInstance().getThingContained(Integer.parseInt(parts[1]))); //szavamat adom, hogy az
					break;
				default: break;
			}
		}
	}
}
