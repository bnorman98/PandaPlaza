package main;

import java.io.PrintWriter;

public class SoftTile extends Tile {
	
	public SoftTile() {
		life = 20;
		texturePath = "res/softtile.png";
	}
	public SoftTile(int life) {
		this.life = life;
		texturePath = "res/softtile.png";
	}
	public int getLife() {
		return life;
	}

	@Override
	public void stepped() {
		System.out.println("SoftTile.stepped called");
		if(life > 0)
			life--;
		if(life == 0)
			animal.die();
	}
	
	@Override
	public String toString() {
		return "SoftTile\n\tID: " + ID + "\n\tlife: " + life;
	}

	public void writeOut(PrintWriter pw){
		pw.println("SoftTile");
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
		pw.println("-life: " + this.getLife());
	}
	
	
}
