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

	public void setNeighbours(Tile t) {
		System.out.println("Tile.setNeighbour called");
		neighbours.add(t);
	}

	/*
	public void setThing(Thing thing) {
		this.thing = thing;
	}
	*/
	public ArrayList<Tile> getNeighbours() {
		System.out.println("Tile.getNeighbours called");
		return neighbours;
	}
	
	public Tile getNeighbourAt(int idx) {
		System.out.println("Tile.getNeighbourAt called");
		return neighbours.get(idx);
	}
	
	public void setAnimal(Animal value) {
		System.out.println("Tile.setAnimal called");
		animal = value;
	}
	
	public Animal getAnimal() {
		System.out.println("Tile.getAnimal called");
		return animal;
	}
	
	public void stepped(){}
	
	public boolean canMove() {
		System.out.println("Tile.canMove called");
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
