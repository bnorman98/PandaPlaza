package main;

import pGraphics.Graphics;

import java.io.PrintWriter;
import java.util.Random;

/**
 * Játékgépet reprezentálja a játékban, csilingelni tud
 */
public class Arcade extends Thing {
	/**
	 * Játékgép konstruktora, minden esetben fog csilingelni
	 */
	public Arcade(){
		chance = 100;
		texturePath = "res/arcade.png";
	}

	/**
	 * A játékgép lép egyet
	 */
	@Override
	public void step() {
		System.out.println("Arcade.step called");
		//Az összes cellát végigjárjuk
		for (Tile t: tile.getNeighbours()) {
			Random r = new Random();
			//Ha a véletlen szám kisebb, mint amennyi esélyünk van a csilingelésre, akkor csilingel
			if(t.getAnimal() != null && r.nextInt() % 100 <= chance)
				t.getAnimal().scare();
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
		pw.println("Arcade");
		pw.println("-ID: " + this.getID());
		pw.println("-Chance: " + this.getChance());
		if (this.getTile() != null){
			pw.println("-TileID: " + this.getID());
		}
	}
}
