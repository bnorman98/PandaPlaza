package main;

import java.io.PrintWriter;
/**
 * Represents all the JumpyPandas
 * A JumpyPanda, as its name shows, can jump, that can reduce a SoftTile-s life
 * Make it closer to be broken
 */
public class JumpyPanda extends Panda {
	/**
	 * The JumpyPanda jumps one
	 * Calls the tile's -under the JumpyPanda- step method
	 */
	@Override
	public void jump() {
		System.out.println("JumpyPanda.jump called");
		tile.stepped();
	}

	/**
	 * Serializes itself into the given pw
	 * @param pw The method serializes the JumpyPanda into the given PrintWriter pw
	 */
	public void writeOut(PrintWriter pw){
		pw.println("JumpyPanda");
		pw.println("-ID: " + this.getID());
		if (this.getTile() != null){
			pw.println("-TileID: " + this.getTile().getID());
		}
		if (this.getInfluencer() != null){
			pw.println("-InfluencerID: " + this.getInfluencer().getID());
		}
		if (this.getFollower() != null){
			pw.println("-FollowerID: " + this.getFollower().getID());
		}
	}
}
