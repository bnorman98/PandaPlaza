package main;

public abstract class Animal implements Steppable {
	protected int dir = 0;
	protected int score = 0;
	protected Tile tile;
	protected Panda follower;
	protected Animal influencer;
	
	public void setDir(int newDir) {
		dir = newDir;
	}
	public void setTile(Tile newTile) { tile = newTile; }
	public void setFollower(Panda newPanda) {
		follower = newPanda;
		newPanda.influencer = this;
	}
	public Animal getFollower(){
		return follower;
	}
	
	public void goTo(Tile newTile) {
		tile.setAnimal(null);
		newTile.setAnimal(this);
		tile = newTile;
		tile.stepped();
	}
	
	// Returns 0 for default, overridden in Orangutan
	public int countFollowers() {
		int cnt = 0;
		Panda tempFollower = follower;
		while(tempFollower != null) {
			cnt++;
			tempFollower = tempFollower.follower;
		}
		return cnt;
	}
	
	public void killFollowers() {
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
	
	public void letGo(){
		if(influencer != null) {
			influencer.follower = null;
			influencer = null;
		}
		if(follower != null) {
			follower.letGo();
			follower = null;
		}
	}
	
	public void die(){
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
	
}
