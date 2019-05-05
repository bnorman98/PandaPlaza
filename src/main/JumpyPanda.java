package main;

import java.io.PrintWriter;

public class JumpyPanda extends Panda {
	
	@Override
	public void jump() {
		System.out.println("JumpyPanda.jump called");
		tile.stepped();
	}

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
