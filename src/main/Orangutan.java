package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Orangutan extends Animal {
	private int penalty = 0;
	
	@Override
	public void step() {
		System.out.println("Orangutan.step called");
		// Can't step if penalized
		if(penalty > 0) {
			penalty--;
			return;
		}
		Tile myPrevTile = tile;
		goTo(tile.getNeighbourAt(dir));
		if(follower != null)
			follower.follow(myPrevTile);
	}
	
	@Override
	public void addScore(int score) {
		System.out.println("Orangutan.addScore called");
		this.score += score;
	}
	
	public int getScore() {
		System.out.println("Orangutan.addScore called");
		return score;
	}
	
	@Override
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
		
		// Touching Animal found in moving direction
		newTile.getAnimal().getTouched(this);
		
		tile.setAnimal(null);
		newTile.setAnimal(this);
		tile = newTile;
		tile.stepped();
	}
	
}
