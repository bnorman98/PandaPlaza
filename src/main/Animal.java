package main;

import java.io.PrintWriter;

/**
 * Represents all the animals
 */
public abstract class Animal implements Steppable {
	
	/**
	 * Moving direction
	 */
	protected int dir = 0;
	
	/**
	 * The tile, where the animal is standing
	 */
	protected Tile tile;
	
	/**
	 * Represents the panda following the animal
	 * Nullable if animal has no follower
	 */
	protected Panda follower;
	
	/**
	 * Stores the animal whose follower this animal is
	 * Nullable if animal has no influencer
	 */
	protected Animal influencer;
	
	/**
	 * Object's own identifier
	 * Must differ for all the animals in a game
	 */
	private int ID;
	
	/**
	 * ID attribute's setter method
	 */
	public void setID(int id) {
		ID = id;
	}
	
	/**
	 * ID attribute's getter method
	 */
	public int getID() {
		return ID;
	}
	
	/**
	 * Sets the animals moving directions
	 * Expects a string
	 * @param newDir New direction to be set
	 */
	public void setDir(String newDir) {
		try {
			dir = Integer.parseInt(newDir);
		}
		catch(NumberFormatException e) {
			System.out.println("Invalid number format for [direction]");
		}
	}
	
	/**
	 * Sets the animals moving directions
	 * Expects an integer
	 * @param newDir
	 */
	public void setDir(int newDir) {
		dir = newDir;
	}
	
	/**
	 * Influencer attribute's getter method
	 */
	public Animal getInfluencer(){
		return influencer;
	}
	
	/**
	 * Tile attribute's setter method
	 * Works both ways
	 * @param newTile
	 */
	public void setTile(Tile newTile) {
		System.out.println("Animal.setTile called");
		tile = newTile;
		tile.setAnimal(this);
	}
	
	/**
	 * Follower attribute's setter method
	 * Works both ways
	 * @param newPanda
	 */
	public void setFollower(Panda newPanda) {
		follower = newPanda;
		if(newPanda != null)
			newPanda.influencer = this;
	}
	
	/**
	 * Follower attribute's getter method
	 */
	public Animal getFollower() {
		return follower;
	}
	
	/**
	 * Tile attribute's getter method
	 */
	public Tile getTile() {
		return tile;
	}
	
	/**
	 * Moves the animal to the given tile
	 * @param newTile Destination tile
	 */
	public void goTo(Tile newTile) {
		tile.setAnimal(null);
		newTile.setAnimal(this);
		tile = newTile;
		tile.stepped();
	}
	
	/**
	 * Counts all the followers behind an animal
	 * @return Number of followers
	 */
	public int countFollowers() {
		int cnt = 0;
		Panda tempFollower = follower;
		while(tempFollower != null) {
			cnt++;
			tempFollower = tempFollower.follower;
		}
		return cnt;
	}
	
	/**
	 * Kills all the followers of the animal
	 */
	public void killFollowers() {
		if(follower == null) {
			return;
		}
		
		// Finding the last follower in the chain
		Animal last = follower;
		while(last.follower != null) {
			last = last.follower;
		}
		
		// Killing followers from end of chain to front
		while(last != this) {
			Animal temp = last.influencer;
			last.die();
			last = temp;
		}
	}
	
	/**
	 * Lets go of all the followers recursively
	 */
	public void letGo() {
		// Letting go of influencer
		if(influencer != null) {
			influencer.follower = null;
			influencer = null;
		}
		// Letting go of followers
		if(follower != null)
			follower.letGo();
	}
	
	/**
	 * Called on animals death
	 * Lets go of the followers and
	 * removes animal from the tile it was standing on
	 */
	public void die() {
		// Letting go of other animals
		letGo();
		
		// Remove from tile
		tile.setAnimal(null);
		tile = null;
	}
	
	/**
	 * The animal and it's toucher switches place
	 * @param toucher Represents the Orangutan, who touched the animal
	 */
	protected void switchPlace(Orangutan toucher) {
		Tile temp = new Tile();
		Tile myTile = tile;
		goTo(temp);
		Tile toucherTile = toucher.tile;
		toucher.goTo(myTile);
		goTo(toucherTile);
	}
	
	// Function below are empty and should be overridden in child classes
	
	/**
	 * The event when an animal is touched by an orangutan
	 * Overridden in child classes
	 * @param toucher Represents the Orangutan, who touched the animal
	 */
	public void getTouched(Orangutan toucher) {}
	
	/**
	 * Scares the animal
	 * Overridden in child classes
	 */
	public void scare(){}
	
	/**
	 * Makes the animal jump
	 * Overridden in child classes
	 */
	public void jump(){}
	
	/**
	 * Animal falls asleep
	 * Overridden in child classes
	 */
	public void sleep(){}
	
	/**
	 * Can increase animal's score
	 * Differs for animal types
	 * Overridden in child classes, where
	 */
	public void addScore(int score){}
	
	/**
	 * Writes out the object to file
	 */
	public void writeOut(PrintWriter pw){}
	
}
