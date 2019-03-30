package main;

public abstract class Animal implements Steppable {
	protected int dir = 0;
	protected int score = 0;
	protected Tile tile;
	protected Panda follower;
	protected Animal influencer;
	
	// Made for Prototype
	protected int ID;
	public void setID(int id) {
		ID = id;
	}
	public int getID() {
		return ID;
	}
	
	public void setDir(int newDir) {
		System.out.println("Animal.setDir called");
		dir = newDir;
	}
	public void setTile(Tile newTile) {
		System.out.println("Animal.setTile called");
		tile = newTile;
	}
	public void setFollower(Panda newPanda) {
		System.out.println("Animal.setFollower called");
		follower = newPanda;
		newPanda.influencer = this;
	}
	public Animal getFollower() {
		System.out.println("Animal.getFollower called");
		return follower;
	}
	
	public void goTo(Tile newTile) {
		System.out.println("Animal.goTo called");
		tile.setAnimal(null);
		newTile.setAnimal(this);
		tile = newTile;
		tile.stepped();
	}
	
	// Returns 0 for default, overridden in Orangutan
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
	
	// Function bodies empty on purpose from here
	public void getTouched(Orangutan toucher) {}
	public void scare(){}
	public void jump(){}
	public void sleep(){}
	public void addScore(int score){}
	public String toString(){
		return "Animal";
	}
	
}
