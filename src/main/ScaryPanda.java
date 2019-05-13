package main;

import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Represents all the ScaryPandas
 * A ScaryPanda, as its name shows, can be scared
 * An ArcadeGameMachine can create a ring voice
 * that scares our little friend
 */
public class ScaryPanda extends Panda {
	
	@Override
	/**
	 * Represents the "Getting scared" of the ScaryPanda
	 */
	public void scare() {
		letGo();
	}

	/**
	 * Serializes itself into the given pw
	 * @param pw The method serializes the ScaryPanda into the given PrintWriter pw
	 */
	public void writeOut(PrintWriter pw){
		pw.println("ScaryPanda");
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
