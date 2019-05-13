package main;

import java.io.PrintWriter;

/**
 * Represents the SoftTiles in the game
 * SoftTiles have life
 * If someone steps onto that
 * Or jumps on that
 * Its life will be reduced
 * If its life reaches the zero
 * It breaks and the objects on that
 * Will fall down into the daaark deepness, makes them die
 */
public class SoftTile extends Tile {
	/**
	 * The ctor of the SoftTile
	 * The default life value is 20
	 */
	public SoftTile() {
		life = 20;
		texturePath = "res/softtile.png";
	}
	public SoftTile(int life) {
		this.life = life;
		texturePath = "res/softtile.png";
	}

	/**
	 *
	 * @return the life of the SoftTile
	 */
	public int getLife() {
		return life;
	}

	/**
	 * Steps the SoftTile
	 * Reduces its life and when it equals zero
	 * Kills the animal that stands on it
	 */
	@Override
	public void stepped() {
		if(life > 0)
			life--;
		if(life == 0)
			animal.die();
	}
	
	@Override
	public String toString() {
		return "SoftTile\n\tID: " + ID + "\n\tlife: " + life;
	}

	/**
	 * Serializes itself into the given pw
	 * @param pw The method serializes the SoftTile into the given PrintWriter pw
	 */
	public void writeOut(PrintWriter pw){
		pw.println("SoftTile");
		pw.println("-ID: " + this.getID());
		for (int i=0;i<this.getNumOfNeighbours();i++){
			pw.println("-NeighbourID: " + this.getNeighbourAt(i).getID());
		}
		if (this.getAnimal() != null){
			pw.println("-AnimalID: " + this.getAnimal().getID());
		}
		if (this.getThing() != null){
			pw.println("-ThingID: " + this.getThing().getID());
		}
		pw.println("-life: " + this.getLife());
	}
	
}
