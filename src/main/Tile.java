package main;

import java.util.ArrayList;

public class Tile {
	
	protected Animal animal;
	private ArrayList<Tile> neighbours = new ArrayList<>();
	
	// Made for Prototype
	protected int ID;
	public void setID(int id) {
		ID = id;
	}
	public int getID() {
		return ID;
	}
	
	// SoftTile konstruktora miatt kell elvileg
	public Tile() {}
	
	public Tile(ArrayList<Tile> neighbours) {
		// Clone the list and it's contents
	}

	public void addNeighbour(Tile neighbour) {
		System.out.println("Tile.setNeighbour called");
		neighbours.add(neighbour);
		neighbour.neighbours.add(this);
	}

	/*
	public void setThing(Thing thing) {
		this.thing = thing;
	}
	*/
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
