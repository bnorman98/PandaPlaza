package main;

import java.io.PrintWriter;

public class SleepyPanda extends Panda {
	
	private boolean isSleeping = false;
	
	@Override
	public void step() {
		System.out.println("SleepyPanda.step called");
		if(isSleeping)
			isSleeping = false;
		else goTo(tile.getNeighbourAt(dir));
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
