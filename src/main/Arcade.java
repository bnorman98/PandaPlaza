package main;

import java.io.PrintWriter;
import java.util.Random;

/**
 * Represents an Arcade Game machine in the game
 * Can "csilling" - It can ring, scare the Panda, if it is able to be scared
 */
public class Arcade extends Thing {
	/**
	 * The ctor of the Game Machine, it rings always
	 */
	public Arcade(){
		chance = 100;
		texturePath = "res/arcade.png";
	}

	/**
	 * The ArcadeGameMachine steps one
	 * It rings, if it has to
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
	}

	/**
	 * Serializes itself into the given pw
	 * @param pw The method serializes the Arcade into the given PrintWriter pw
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
