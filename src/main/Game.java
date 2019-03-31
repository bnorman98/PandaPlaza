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
	private boolean running = false;
	
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
	
	private Game() {
		orangutans = new ArrayList<>();
		pandas = new ArrayList<>();
		things = new ArrayList<>();
		tiles = new ArrayList<>();
	}
	public void startGame() {
		running = true;
		stepAll();
	}
	public void endGame() {
		running = false;
		String input = "none";
		try {
			InputStreamReader isr =	new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			input = br.readLine();
			br.close();
		}
		catch(IOException ioe) {
			System.out.println("IO error occurred");
		}
		
		// What to do?
		
	}
	
	public void stepAll() {
		System.out.println("Game.stepAll called");
		
		// Stepping Orangutans
		for (Orangutan orangutan : orangutans) {
			// Reading user input
			String input = readInput(orangutan);
			
			// Setting direction
			if(input.equals("IOError"))
				System.out.println("IO error occurred");
			else if(input.equals("endgame"))
				endGame();
			else if(input.equals("letgo"))
				orangutan.letGo();
			else {
				// Setting direction
				orangutan.setDir(input);
				// Moving Orangutan
				orangutan.step();
			}
			
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
	
	private String readInput(Orangutan orangutan) {
		// User interaction
		System.out.println("What tile do you want to go?");
		String line = "Enter a number from 0 to ";
		int dirMax = orangutan.getTile().getNeighbours().size()-1;
		line += dirMax;
		System.out.println(line);
		// Print neighbours
		int i = 0;
		for(Tile neighbour: orangutan.getTile().getNeighbours()) {
			System.out.println("\t" + i + ": " + neighbour.getID());
		}
		
		// Reading input
		try {
			String input;
			InputStreamReader isr =	new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			input = br.readLine();
			br.close();
			return input;
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
			return "IOError";
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
