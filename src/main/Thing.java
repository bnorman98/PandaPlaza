package main;

public abstract class Thing implements Steppable {
	
	protected Tile tile;
	protected int chance;
	
	// Made for Prototype
	protected int ID;
	public void setID(int id) {
		ID = id;
	}
	public int getID() {
		return ID;
	}
	
	public void setTile(Tile tile) {
		System.out.println("Thing.setTile called");
		this.tile = tile;
	}
	
}