package main;

import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Represents a tile in the game
 */
public class Tile {
	
	/**
	 * Stores he animal standing on the tile
	 */
	protected Animal animal;
	
	/**
	 * Stores the thing standing on the tile
	 */
	protected Thing thing;
	
	/**
	 * Stores tile's life
	 * -1 if tile is hard,
	 * >=0 if  tile is soft
	 */
	protected int life;
	
	/**
	 * Stores tile's neighbours
	 */
	private ArrayList<Tile> neighbours = new ArrayList<>();
	
	/**
	 * Object's own identifier
	 * Must differ for all the tiles in a game
	 */
	protected int ID;
	
	/**
	 * ID attribute's setter method
	 */
	public void setID(int id) {
		ID = id;
	}
	
	/**
	 * ID attribute's setter method
	 */
	public int getID() {
		return ID;
	}
	
	/**
	 * Life attribute's getter
	 */
	public int getLife() {
		return life;
	}
	
	/**
	 * Default constructor
	 * Creates a hard tile with a life of -1
	 */
	public Tile() {
		life = -1;
	}
	
	/**
	 * Constructor
	 * Creates a hard tile with a life of -1
	 * @param neighbours Contains neighbouring tiles
	 */
	public Tile(ArrayList<Tile> neighbours) {
		life = -1;
		// Clone the list and it's contents
		// Set neighbour connection
	}
	
	/**
	 * Adds a tile to the neighbouring tiles' list
	 * @param neighbour
	 */
	public void addNeighbour(Tile neighbour) {
		neighbours.add(neighbour);
		neighbour.neighbours.add(this);
	}
	
	/**
	 * Thing attribute's getter
	 */
	public Thing getThing() {
		return thing;
	}
	
	/**
	 * Returns the list of neighbouring tiles
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

	public int getNumOfNeighbours(){
		return neighbours.size();
	}
	public Animal getAnimal() {
		return animal;
	}
	
	public void stepped(){}
	
	/**
	 * Returns false if the animal standing on this can not move
	 */
	public boolean canMove() {
		for (Tile tile : neighbours) {
			if(tile.getAnimal() == null)
				return true;
		}
		return false;
	}
	
	/**
	 * Writes out the object to file
	 */
	public void writeOut(PrintWriter pw){
			pw.println("Tile");
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
