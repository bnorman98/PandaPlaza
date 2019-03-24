package main;

public class Orangutan extends Animal {
	
	@Override
	public void step() {
		Tile myPrevTile = tile;
		goTo(tile.getNeighbourAt(dir));
		if(follower != null)
			follower.follow(myPrevTile);
	}
	
	@Override
	public void addScore(int score) {
		this.score += score;
	}
	
	public int getScore(){
		return score;
	}
	
}
