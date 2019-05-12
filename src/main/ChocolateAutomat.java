package main;

import pGraphics.Graphics;

import java.io.PrintWriter;
import java.util.Random;

/**
 * Csokiautomatát reprezentálja a játékban
 */
public class ChocolateAutomat extends Thing {
	/**
	 * Csokiautomata konstruktora
	 */
	public ChocolateAutomat() {
		chance = 100;
		texturePath = "res/chocolateautomat.png";
	}

	/**
	 * Csokiautomata konstruktora
	 * @param chance mennyi eséllyel fog sípolni
	 */
	public ChocolateAutomat(int chance) {
		this.chance = 0 <= chance && chance <= 100 ? chance : 100;
		texturePath = "res/chocolateautomat.png";
	}

	/**
	 * Lép egyet a csokiautomata
	 */
	@Override
	public void step() {
		System.out.println("ChocolateAutomat.step called");
		Random r = new Random();
		//Az összes cellát végigjárjuk
		for (Tile t: tile.getNeighbours()) {
			//Ha a véletlen szám kisebb,
			// mint amennyi esélyünk van a csilingelésre, akkor ráül a panda
			if(t.getAnimal() != null && r.nextInt() % 100 <= chance)
				t.getAnimal().jump();
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
		pw.println("ChocolateAutomat");
		pw.println("-ID: " + this.getID());
		pw.println("-Chance: " + this.getChance());
		if (this.getTile() != null){
			pw.println("-TileID: " + this.getID());
		}
	}
}
