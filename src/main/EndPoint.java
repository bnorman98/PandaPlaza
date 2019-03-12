package main;

public class EndPoint extends Tile {
	
	private Tile startPoint;
	
	public EndPoint(Tile startPoint) {
		this.startPoint = startPoint;
	}
	
	// Double check me please
	public void stepped() {
		Game.addScore(animal.countFollowers(), animal);
		animal.letGo();
		animal.goTo(startPoint);
		// animal = null;
	}
}
