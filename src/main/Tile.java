package main;

import java.util.ArrayList;

public class Tile {
	
	protected Animal animal;
	private ArrayList<Tile> neighbours = new ArrayList<>();
	
	// SoftTile konstruktora miatt kell elvileg
	public Tile() {}
	
	public Tile(ArrayList<Tile> neighbours) {
		// Clone the list and it's contents
	}

	public void setNeighbours(Tile t) {
		neighbours.add(t);
	}

	/*
	public void setThing(Thing thing){
		this.thing = thing;
	}
	*/
	public ArrayList<Tile> getNeighbours() {
		return neighbours;
	}
	
	public Tile getNeighbourAt(int idx) {
		return neighbours.get(idx);
	}
	
	public void setAnimal(Animal value){
		animal = value;
	}
	
	public Animal getAnimal(){
		return animal;
	}
	
	public void stepped(){}
	
	public boolean canMove(){
		for (Tile tile : neighbours) {
			if(tile.getAnimal() == null)
				return true;
		}
		return false;
	}
}
