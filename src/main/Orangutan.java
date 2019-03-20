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
	
	@Override
	public void die(){
		// Letting go of pandas
		if(follower != null)
			follower.letGo();
		
		// Remove from tile
		tile.setAnimal(null);
		tile = null;
	}
	
	@Override
	public void letGo(){
		if(follower != null)
			follower.letGo();
	}
	
	@Override
	public int countFollowers(){
		int cnt = 0;
		Panda tempFollower = follower;
		while(tempFollower != null){
			cnt++;
			tempFollower = tempFollower.follower;
		}
		return cnt;
	}
}
