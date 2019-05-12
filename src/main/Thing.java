package main;

import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * The abstract class of all Things in the game
 * for example ChocolateAutomats, ArcadeGameMachines, etc.
 * A Thing can be found on a Tile
 * They usually interacts with Animals, especially with Pandas
 */
public abstract class Thing implements Steppable {
	/**
	 * The Tile under the Thing
	 */
	protected Tile tile;
	/**
	 * The chance value of the Thing
	 */
	protected int chance;
	/**
	 * The Path to the Things graphics representation
	 */
	protected String texturePath;
	/**
	 * The ID of the Ting
	 */
	protected int ID;

	/**
	 * Sets the ID of a Thing
	 * @param id the number, that will be set as the ID of the Ting
	 */
	public void setID(int id) {
		ID = id;
	}

	/**
	 *
	 * @return the ID of the Thing
	 */
	public int getID() {
		return ID;
	}

	/**
	 *
	 * @return the Path of the Thing's texture
	 */
	public String getTexturePath(){
		return texturePath;
	}

	public int getChance(){
		return chance;
	}
	public Tile getTile(){
		return tile;
	}

	/**
	 * Sets the Tile of the Thing
	 * Thing will be found on this Tile
	 */
	public void setTile(Tile tile) {
		System.out.println("Thing.setTile called");
		this.tile = tile;
	}
	/**
	 * Serializes itself into the given pw
	 * @param pw The method serializes the Thing into the given PrintWriter pw
	 * Its always overdefined
	 */
	public void writeOut(PrintWriter pw){}

	/**
	 * An assistant method
	 * Helps the deserialization
	 * Knows the scheme of the deserialization
	 * @param lines The lines that contain the informations about the Thing
	 * @param idx Index of the Thing
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
				default: break;
			}
		}
	}
}