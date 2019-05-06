package main;

import pGraphics.Graphics;

import java.io.PrintWriter;
import java.util.ArrayList;

public class Orangutan extends Animal {
	private int score = 0;
	private int penalty = 0;
	
	public int getPenalty() {
		return penalty;
	}
	
	@Override
	public void step() {
		System.out.println("Orangutan.step called");
		if (penalty > 0){
			penalty--;
		}
		if (tile != null && tile.getNumOfNeighbours() != 0) {
			boolean folBeforeStep = false;
			if (follower != null)
				folBeforeStep = true;
			Tile myPrevTile = tile;
			goTo(tile.getNeighbourAt(dir));
			tile.stepped();
			if (folBeforeStep)
				follower.follow(myPrevTile);
		}
		updateGraphics(Graphics.getInstance());
	}
	
	@Override
	public void addScore(int score) {
		this.score += score;
	}
	
	public int getScore() {
		return score;
	}
	
	//@Override
	public String toString() {
		return "Orangutan\n\tTile: " + tile.toString();	// Itt még a behúzást meg kell javítani
	}
	
	@Override
	public void getTouched(Orangutan toucher) {
		// Checking if toucher Orangutan can steal Pandas
		if(toucher.countFollowers() != 0)
			return;
		
		// Saving original number of followers
		int followerCnt = this.countFollowers();
		
		// Switching place
		switchPlace(toucher);
		
		// Giving Pandas to toucher
		toucher.setFollower(follower);
		this.follower = null;
		
		// Can't step for 3 turns if
		if(followerCnt != 0)
		penalty = 3;
		
	}
	
	@Override
	public void goTo(Tile newTile) {
		System.out.println("Orangutan.goTo called");
		
		// Touching Animal found in moving direction if not penalized
		if(newTile.getAnimal() != null) {
			if(penalty != 0)
				return;
			else
				newTile.getAnimal().getTouched(this);
		}
		else {
			tile.setAnimal(null);
			newTile.setAnimal(this);
			tile = newTile;
			tile.stepped();
		}
		
	}

	public void writeOut(PrintWriter pw){
		pw.println("Orangutan");
		pw.println("-ID: " + this.getID());
		pw.println("-Score: " + this.getScore());
		pw.println("-Penalty: " + this.getPenalty());
		if(this.getTile() != null)
			pw.println("-TileID: " + this.getTile().getID());
		if(this.getFollower() != null)
			pw.println("-FollowerID: " + this.getFollower().getID());
	}

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
				case "-Score:":
					this.score = Integer.parseInt(parts[1]);
				case "-Penalty:":
					this.penalty = Integer.parseInt(parts[1]);
					break;
				default: break;
			}
		}
	}

	public void die(){
		System.out.println("Orangutan.die called");
		// Letting go of other animals
		letGo();

		// Remove from tile
		tile.setAnimal(null);
		tile = null;
		Game.getInstance().killOrangutan(this);
	}
}
