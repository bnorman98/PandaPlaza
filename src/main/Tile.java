package main;

import java.util.ArrayList;

public class Tile {
	protected Animal animal;
	protected Thing thing;
	// Life is -1 if tile is hard, >=0 if soft
	protected int life;
	private ArrayList<Tile> neighbours = new ArrayList<>();
	
	// Made for Prototype
	protected int ID;
	public void setID(int id) {
		ID = id;
	}
	public int getID() {
		return ID;
	}
	public int getLife() {
		return life;
	}
	
	public Tile() {
		life = -1;
	}
	
	public Tile(ArrayList<Tile> neighbours) {
		life = -1;
		// Clone the list and it's contents
		// Set neighbour connection
	}

	public void addNeighbour(Tile neighbour) {
		System.out.println("Tile.setNeighbour called");
		neighbours.add(neighbour);
		neighbour.neighbours.add(this);
	}
	
	public Thing getThing() {
		return thing;
	}
	
	public ArrayList<Tile> getNeighbours() {
		return neighbours;
	}
	
	public Tile getNeighbourAt(int idx) {
		return neighbours.get(idx);
	}
	
	public void setAnimal(Animal value) {
		animal = value;
		animal.setTile(this);
	}
	
	public Animal getAnimal() {
		return animal;
	}
	
	public void stepped(){}
	
	public boolean canMove() {
		for (Tile tile : neighbours) {
			if(tile.getAnimal() == null)
				return true;
		}
		return false;
	}
	
	public String toString() {
		return "Tile\n\tID: " + ID;
	}
	
}
