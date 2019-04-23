package main;

import java.io.PrintWriter;
import java.util.ArrayList;

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
		animal.addScore(animal.countFollowers());
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

	public void readIn(ArrayList<String> lines, int idx){
		for (int i=idx+1;i<lines.size();i++){
			String[] parts = lines.get(i).split(" ");
			if (parts[0].charAt(0) != '-'){
				i = lines.size();
			}
			switch (parts[0]){
				case "-startID:":
					this.startPoint = Game.getInstance().getTileContained(Integer.parseInt(parts[1]));
					break;
				case "-NeighbourID:":
					this.addNeighbour(Game.getInstance().getTileContained(Integer.parseInt(parts[1])));
					break;
				case "-ThingID:":
					this.setThing(Game.getInstance().getThingContained(Integer.parseInt(parts[1])));
					break;
				case "-AnimalID:":
					Orangutan o = Game.getInstance().getOrangutanContained(Integer.parseInt(parts[1]));
					if (o != null){
						this.setAnimal(o);
						break;
					}
					Panda p = Game.getInstance().getPandaContained(Integer.parseInt(parts[1]));
					if (p != null){
						this.setAnimal(p);
					}
					break;
				case "-life:":
					this.life = Integer.parseInt(parts[1]);
					break;
				default: break;
			}
		}
	}
}
