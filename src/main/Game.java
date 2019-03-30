package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class Game {
	private ArrayList<Orangutan> orangutans;
	private ArrayList<Panda> pandas;
	private ArrayList<Thing> things;
	private ArrayList<Tile> tiles;
	
	private static Game instance;
	
	// Adding elements to the lists
	public void addOrangutan(Orangutan newOrangutan) {
		orangutans.add(newOrangutan);
	}
	public void addPanda(Panda newPanda) {
		pandas.add(newPanda);
	}
	public void addThing(Thing newThing) {
		things.add(newThing);
	}
	public void addTile(Tile newTile) {
		// Checking if the new Tile is really new
		boolean isNew = true;
		for(Tile tile : tiles) {
			if(tile.getID() == newTile.getID()) {
				isNew = false;
				break;
			}
		}
		if(isNew)
			tiles.add(newTile);
	}
	
	public ArrayList<Tile> getTiles() {
		return tiles;
	}
	
	public boolean isContained(int ID) {
		for(Tile gameTile : tiles) {
			if(gameTile.getID() == ID) {
				return true;
			}
		}
		return false;
	}
	
	public Tile getTileContained(int ID) {
		for(Tile tile : tiles) {
			if(tile.getID() == ID)
				return tile;
		}
		return null;
	}
	
	public static Game getInstance() {
		if (instance == null)
			instance = new Game();
		return instance;
	}
	
	private Game() {}
	public void startGame() {
		stepAll();
	}
	public void endGame() {}
	
	public void stepAll() {
		System.out.println("Game.stepAll called");
		
		// Stepping Orangutans
		for (Orangutan orangutan : orangutans) {
			// Reading user input
			String input = readInput();
			
			// Setting direction
			try {
				if(input.equals("IOError"))
					System.out.println("Read error occurred");
				else
					orangutan.setDir(Integer.parseInt(input));
			}
			catch(NumberFormatException e) {
				System.out.println("Invalid number format for [direction]");
			}
			
			// Moving Orangutan
			orangutan.step();
		}
		
		// Stepping Pandas
		for(Panda panda : pandas) {
			panda.step();
		}
		
		// Stepping Things
		for(Thing thing : things) {
			thing.step();
		}
	}
	
	private String readInput() {
		System.out.println("What direction do you want to go?");
		
		try {
			InputStreamReader isr =	new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			return br.readLine();
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
			return "IOError";
		}
	}
	
	public static void addScore(Animal animal) {
		System.out.println("Game.addScore called");
		animal.addScore(animal.countFollowers());
	}
	
	public boolean generateRandom(int chance) {
		System.out.println("Game.generateRandom called");
		int rand = (int) (Math.random() * 100);
		System.out.println("Randomised: " + rand);
		return rand > chance;
	}
}
