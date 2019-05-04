package main;

import java.io.PrintWriter;
import java.util.ArrayList;

public class ScaryPanda extends Panda {
	
	@Override
	public void scare() {
		System.out.println("ScaryPanda.scare called");
		letGo();
	}

	public void writeOut(PrintWriter pw){
		pw.println("ScaryPanda");
		pw.println("-ID:" + this.getID());
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
