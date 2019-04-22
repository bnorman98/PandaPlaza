package main;

import java.io.PrintWriter;

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
			pw.println("-tileID: " + this.getTile().getID());
		}
		if (this.getInfluencer() != null){
			pw.println("-influencerID: " + this.getInfluencer().getID());
		}
		if (this.getFollower() != null){
			pw.println("-followerID: " + this.getFollower().getID());
		}
	}
}
