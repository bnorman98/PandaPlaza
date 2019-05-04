package main;

import java.io.PrintWriter;

public class SleepyPanda extends Panda {
	
	private boolean isSleeping = false;
	
	@Override
	public void step() {
		System.out.println("SleepyPanda.step called");
		if(isSleeping)
			isSleeping = false;
		else if (tile != null && tile.getNumOfNeighbours() != 0) goTo(tile.getNeighbourAt(dir));
	}
	
	@Override
	public void sleep() {
		System.out.println("SleepyPanda.sleep called");
		
		// Falling asleep
		isSleeping = true;
		letGo();
	}

	public void writeOut(PrintWriter pw){
		pw.println("SleepyPanda");
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
