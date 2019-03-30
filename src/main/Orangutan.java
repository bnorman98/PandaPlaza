package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Orangutan extends Animal {
	
	@Override
	public void step() {
		System.out.println("Orangutan.step called");
		
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
}
