package main;

import java.util.ArrayList;
import java.util.Random;

public class Game {
	private ArrayList<Orangutan> orangutans;
	private ArrayList<Panda> pandas;
	private ArrayList<Thing> things;
	private ArrayList<Tile> tiles;
	
	private static Game instance;
	
	public static Game getInstance() {
		if (instance == null)
			instance = new Game();
		return instance;
	}
	
	private Game() {}
	public void startGame() {}
	public void endGame() {}
	
	public void stepAll() {
		for (Orangutan orangutan : orangutans) {
			orangutan.step();
		}
		for(Panda panda : pandas) {
			panda.step();
		}
		for(Thing thing : things) {
			thing.step();
		}
	}
	public static void addScore(Animal animal) {
		animal.addScore(animal.countFollowers());
	}
	
	public boolean generateRandom(int chance) {
		int rand = (int) (Math.random() * 100);
		System.out.println("Randomised: " + rand);
		return rand > chance;
	}
}
