package main;

public class EndPoint extends Tile {
	
	private Tile startPoint;
	
	public EndPoint(Tile startPoint) {
		this.startPoint = startPoint;
	}
	
	public void stepped() {
		// Ez ebben a formaban nem jo, mivel egy panda is ra tud lepni
		getAnimal().goTo(startPoint);
		//Game.getReward(getAnimal.followerCount());
		//getAnimal.destroyFollowers();
	}
}
