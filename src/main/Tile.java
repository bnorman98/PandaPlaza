package main;

import java.awt.*;
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

	private Point position;
	
	/**
	 * Object's own identifier
	 * Must differ for all the tiles in a game
	 */
	protected int ID;

	protected String texturePath = "res/tile.png";

	public String getTexturePath(){
		return texturePath;
	}

	public Point getPosition(){ return position;}
	public void setPosition(Point p){ position = p;}
	
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
	
	public void setThing(Thing thing) {
		this.thing = thing;
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
		if (value != animal) {
			animal = value;
			if (animal != null)
				animal.setTile(this);
		}
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

	/**
	 * An assistant method
	 * Helps the deserialization
	 * Knows the scheme of the deserialization
	 * @param lines The lines that contain the informations about the Tile
	 * @param idx Index of the Tile
	 */
	public void readIn(ArrayList<String> lines, int idx){
		for (int i=idx+1;i<lines.size();i++){
			String[] parts = lines.get(i).split(" ");
			if (parts[0].charAt(0) != '-'){
				i = lines.size();
			}
			switch (parts[0]){
				case "-NeighbourID:":
					boolean van = false;
					for (int j=0;j<neighbours.size();j++) {
						if (neighbours.get(j).getID() == Integer.parseInt(parts[1]))
							van = true;
					}
					if (!van)
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
