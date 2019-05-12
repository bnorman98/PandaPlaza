package main;

import pGraphics.Graphics;

import java.io.PrintWriter;
import java.util.Random;

/**
 * Széket reprezentálja a játékban
 */
public class Chair extends Thing {
	/**
	 * Szék konstruktora
	 */
	public Chair(){
		chance = 100;
		texturePath = "res/chair.png";
	}

	/**
	 * A szék lép egyet
	 */
	@Override
	public void step() {
		System.out.println("Chair.step called");
		//Az összes cellát végigjárjuk
		for (Tile t: tile.getNeighbours()) {
			Random r = new Random();
			//Ha a véletlen szám kisebb,
			// mint amennyi esélyünk van a csilingelésre, akkor ráül a panda
			if(t.getAnimal() != null && r.nextInt() % 100 <= chance){
				t.getAnimal().goTo(tile);
				tile.getAnimal().sleep();
				break;
			}
		}
		updateGraphics(Graphics.getInstance());
	}

	/**
	 * Szerializálja magát a paraméterként megadott objektumba
	 * @param pw Amibe szerializájuk az objektumot
	 */
	public void writeOut(PrintWriter pw){
		//Println függvénnyel kiírjuk az osztály nevét,
		//tagfüggvényeit és a tagfüggvények értékeit
		pw.println("Chair");
		pw.println("-ID: " + this.getID());
		pw.println("-Chance: " + this.getChance());
		if (this.getTile() != null){
			pw.println("-TileID: " + this.getID());
		}
	}
}
