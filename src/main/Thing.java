package main;

import java.io.PrintWriter;
import java.util.ArrayList;

public abstract class Thing implements Steppable {
	
	protected Tile tile;
	protected int chance;

	protected String texturePath;
	
	// Made for Prototype
	protected int ID;
	public void setID(int id) {
		ID = id;
	}
	public int getID() {
		return ID;
	}

	public String getTexturePath(){
		return texturePath;
	}

	public int getChance(){
		return chance;
	}
	public Tile getTile(){
		return tile;
	}
	public void setTile(Tile tile) {
		System.out.println("Thing.setTile called");
		this.tile = tile;
	}

	public void writeOut(PrintWriter pw){}

	public void readIn(ArrayList<String> lines, int idx){
		for (int i=idx+1;i<lines.size();i++){
			String[] parts = lines.get(i).split(" ");
			if (parts[0].charAt(0) != '-'){
				i = lines.size();
			}
			switch (parts[0]){
				case "-TileID:":
					this.setTile(Game.getInstance().getTileContained(Integer.parseInt(parts[1])));
					break;
				case "-Chance:":
					this.chance = Integer.parseInt(parts[1]);
					break;
				default: break;
			}
		}
	}
}