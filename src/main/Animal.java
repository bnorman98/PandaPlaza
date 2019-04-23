package main;

import java.io.PrintWriter;

public abstract class Animal implements Steppable {
	protected int dir = 0;
	protected Tile tile;
	protected Panda follower;
	protected Animal influencer;
	
	// Made for Prototype
	private int ID;
	public void setID(int id) {
		ID = id;
	}
	public int getID() {
		return ID;
	}
	
	public void setDir(String newDir) {
		System.out.println("Animal.setDir called");
		try {
			dir = Integer.parseInt(newDir);
		}
		catch(NumberFormatException e) {
			System.out.println("Invalid number format for [direction]");
		}
	}
	public void setDir(int newDir) {
		dir = newDir;
	}
	
	public Animal getInfluencer(){
		System.out.println("Animal.getInfluencer called");
		return influencer;
	}
	public void setTile(Tile newTile) {
		System.out.println("Animal.setTile called");
		tile = newTile;
		tile.setAnimal(this);
	}
	public void setFollower(Panda newPanda) {
		System.out.println("Animal.setFollower called");
		follower = newPanda;
		if(newPanda != null)
			newPanda.influencer = this;
	}
	public Animal getFollower() {
		System.out.println("Animal.getFollower called");
		return follower;
	}
	public Tile getTile() {
		return tile;
	}
	
	public void goTo(Tile newTile) {
		System.out.println("Animal.goTo called");
		
		tile.setAnimal(null);
		newTile.setAnimal(this);
		tile = newTile;
		tile.stepped();
	}
	
	public int countFollowers() {
		System.out.println("Animal.countFollowers called");
		int cnt = 0;
		Panda tempFollower = follower;
		while(tempFollower != null) {
			cnt++;
			tempFollower = tempFollower.follower;
		}
		return cnt;
	}
	
	public void killFollowers() {
		System.out.println("Animal.killFollowers called");
		if(follower == null) {
			return;
		}
		
		Animal last = follower;
		
		while(last.follower != null) {
			last = last.follower;
		}
		
		while(last != this) {
			Animal temp = last.influencer;
			last.die();
			last = temp;
		}
	}
	
	public void letGo() {
		System.out.println("Animal.letGo called");
		if(influencer != null) {
			influencer.follower = null;
			influencer = null;
		}
		if(follower != null) {
			follower.letGo();
			//follower = null; Ez nem kell, mert az influencer.follower = null-ban már beállítjuk
			
		}
	}
	
	public void die() {
		System.out.println("Animal.die called");
		// Letting go of other animals
		letGo();
		
		// Remove from tile
		tile.setAnimal(null);
		tile = null;
	}
	
	protected void switchPlace(Orangutan toucher) {
		Tile temp = new Tile();
		Tile myTile = tile;
		goTo(temp);
		Tile toucherTile = toucher.tile;
		toucher.goTo(myTile);
		goTo(toucherTile);
	}
	
	// Function bodies empty on purpose from here
	public void getTouched(Orangutan toucher) {}
	public void scare(){}
	public void jump(){}
	public void sleep(){}
	public void addScore(int score){}
	public void writeOut(PrintWriter pw){}
	
}
