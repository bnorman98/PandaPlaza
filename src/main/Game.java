package main;

import java.util.ArrayList;

public class Game {
	
	ArrayList<Orangutan> orangutans;
	ArrayList<Panda> pandas;
	ArrayList<Thing> things;
	ArrayList<Tile> tiles;
	
	private static Game ourInstance = new Game();
	
	public static Game getInstance() {
		return ourInstance;
	}
	
	private Game() {}
	public void startGame() {}
	public void endGame() {}
	public void stepAll() {}
	public static void addScore(int followerCount, Animal animal) {}
	 // public boolean GenerateRandom(int chance) {}
}
