package main;

import java.io.PrintWriter;
import java.util.ArrayList;
/**
 * Represents all the SleepyPandas
 * A SleepyPanda, as its name shows, is really tired
 * Can fall asleep really quickly
 * If he finds a chair
 * He takes a refreshing nap on that
 * Casues, that this SleepyPanda wont move
 */
public class SleepyPanda extends Panda {
	
	private boolean isSleeping = false;

	/**
	 * Steps the Panda
	 */
	@Override
	public void step() {
		System.out.println("SleepyPanda.step called");
		if (influencer != null)
			return;
		if(isSleeping)
			isSleeping = false;
		else if (tile != null && tile.getNumOfNeighbours() != 0){
			goTo(tile.getNeighbourAt(dir));
			tile.stepped();
		}
	}

	/**
	 * Makes him sleeping
	 * Lets his followers go if he felt asleep
	 */
	@Override
	public void sleep() {
		System.out.println("SleepyPanda.sleep called");
		
		// Falling asleep
		isSleeping = true;
		letGo();
	}
	/**
	 * Serializes itself into the given pw
	 * @param pw The method serializes the SleepyPanda into the given PrintWriter pw
	 */
	public void writeOut(PrintWriter pw){
		pw.println("SleepyPanda");
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
		if (isSleeping)
			pw.println("-IsSleeping: 1");
		else pw.println("-IsSleeping: 0");
	}

	@Override
	/**
	 * An assistant method
	 * Helps the deserialization
	 * Knows the scheme of the deserialization
	 * @param lines The lines that contain the informations about the SleepyPanda
	 * @param idx Index of the SleepyPanda
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
				case "-FollowerID:":
					this.setFollower(Game.getInstance().getPandaContained(Integer.parseInt(parts[1])));
					break;
				case "-InfluencerID:":
					Orangutan o = Game.getInstance().getOrangutanContained(Integer.parseInt(parts[1]));
					if (o != null){
						this.setInfluencer(o);
						break;
					}
					Panda p = Game.getInstance().getPandaContained(Integer.parseInt(parts[1]));
					if (p != null){
						this.setInfluencer(p);
					}
					break;
				case "-IsSleeping: ":
					int s = Integer.parseInt(parts[1]);
					if (s == 0){
						isSleeping = false;
					}else{
						isSleeping = true;
					}
					break;
				default: break;
			}
		}
	}
}
