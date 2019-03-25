package main;

public class EndPoint extends Tile {
	
	private Tile startPoint;
	
	public EndPoint(Tile startPoint) {
		this.startPoint = startPoint;
	}
	
	// Double check me please
	public void stepped() {
		System.out.println("Endpoint.stepped called");
		if(animal.countFollowers() == 0)
			return;
		Game.addScore(animal);
		animal.killFollowers();
		animal.goTo(startPoint);
		
	}
}
