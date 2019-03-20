package main;

public abstract class Thing implements Steppable {
	
	protected Tile tile;
	protected int chance;

	public void setTile(Tile tile) {
		this.tile = tile;
	}
}