package main;

import java.io.PrintWriter;

public class EndPoint extends Tile {
	
	private Tile startPoint;
	
	public EndPoint() {
		startPoint = null;
	}
	public EndPoint(Tile startPoint) {
		this.startPoint = startPoint;
	}
	
	public Tile getStartPoint() {
		return startPoint;
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

	public void writeOut(PrintWriter pw){
		pw.println("EndPoint");
		pw.println("-ID: " + this.getID());
		for (int i=0;i<this.getNumOfNeighbours();i++){
			pw.println("-NeighbourID: " + this.getNeighbourAt(i).getID());
		}
		if (this.getAnimal() != null){
			pw.println("-AnimalID: " + this.getAnimal().getID());
		}
		if (this.getThing() != null){
			pw.println("-ThingID: " + this.getThing().getID());
		}
		if (this.getStartPoint() != null){
			pw.println("-startID: " + startPoint.getID());
		}
		pw.println("-life: " + this.getLife());
	}
}
