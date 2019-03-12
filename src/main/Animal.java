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
	public void setFollower(Panda newPanda) { follower = newPanda; }
	
	public void getTouched(Animal toucher){}
	
	public void goTo(Tile newTile){
		tile.setAnimal(null);
		tile = newTile;
		tile.setAnimal(this);
		tile.stepped();
	}
	
	public void die(){}
	public void scare(){}
	public void jump(){}
	public void sleep(){}
	public int countFollowers(){
		return 0;
	}	// Returns 0 for default, overridden in Orangutan
	public void killFollowers(){}
	public void addScore(int score){}	// Function body empty on purpose!
	public void letGo(){}				// Function body empty on purpose!
}
