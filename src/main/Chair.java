package main;

import java.io.PrintWriter;
import java.util.Random;

/**
 * Represents a chair
 */
public class Chair extends Thing {
	/**
	 * Ctor of the chair
	 */
	public Chair(){
		chance = 100;
		texturePath = "res/chair.png";
	}

	/**
	 * The chair steps one
	 */
	@Override
	public void step() {
		// Az összes cellát végigjárjuk
		for (Tile t: tile.getNeighbours()) {
			Random r = new Random();
			// Ha a véletlen szám kisebb,
			// mint amennyi esélyünk van a csilingelésre, akkor ráül a panda
			if(t.getAnimal() != null && r.nextInt() % 100 <= chance){
				t.getAnimal().goTo(tile);
				tile.getAnimal().sleep();
				break;
			}
		}
	}

	/**
	 * Szerializálja magát a paraméterként megadott objektumba
	 * @param pw Amibe szerializájuk az objektumot
	 */
	public void writeOut(PrintWriter pw){
		// Println függvénnyel kiírjuk az osztály nevét,
		// tagfüggvényeit és a tagfüggvények értékeit
		pw.println("Chair");
		pw.println("-ID: " + this.getID());
		pw.println("-Chance: " + this.getChance());
		if (this.getTile() != null){
			pw.println("-TileID: " + this.getID());
		}
	}
}
