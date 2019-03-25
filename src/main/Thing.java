package main;

public abstract class Thing implements Steppable {
	
	protected Tile tile;
	protected int chance;

	public void setTile(Tile tile) {
		System.out.println("Thing.setTile called");
		this.tile = tile;
	}
}