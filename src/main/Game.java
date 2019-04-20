package main;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Game {
	private ArrayList<Orangutan> orangutans;
	private ArrayList<Panda> pandas;
	private ArrayList<Thing> things;
	private ArrayList<Tile> tiles;
	private boolean running = false;
	
	private static Game instance;
	
	private Game() {
		orangutans = new ArrayList<>();
		pandas = new ArrayList<>();
		things = new ArrayList<>();
		tiles = new ArrayList<>();
	}
	
	public static Game getInstance() {
		if (instance == null)
			instance = new Game();
		return instance;
	}
	
	// Adding elements to the lists
	public void addOrangutan(Orangutan newOrangutan) {
		boolean isNew = true;
		for(Orangutan orangutan : orangutans) {
			if(orangutan.getID() == orangutan.getID()) {
				isNew = false;
				break;
			}
		}
		if(isNew)
			orangutans.add(newOrangutan);
	}
	public void addPanda(Panda newPanda) {
		boolean isNew = true;
		for(Panda panda : pandas) {
			if(panda.getID() == panda.getID()) {
				isNew = false;
				break;
			}
		}
		if(isNew)
			pandas.add(newPanda);
	}
	public void addThing(Thing newThing) {
		boolean isNew = true;
		for(Thing thing : things) {
			if(thing.getID() == newThing.getID()) {
				isNew = false;
				break;
			}
		}
		if(isNew)
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
		// Adding tile if it's new
		if(isNew)
			tiles.add(newTile);
	}
	
	// Returns if Tile with given ID is contained in tiles
	public boolean isContained(int ID) {
		for(Tile gameTile : tiles) {
			if(gameTile.getID() == ID) {
				return true;
			}
		}
		return false;
	}
	
	/*
	  * Getting a Tile by it's ID
	  * Tile must be contained in tiles
	  */
	public Tile getTileContained(int ID) {
		for(Tile tile : tiles) {
			if(tile.getID() == ID)
				return tile;
		}
		return null;
	}
	
	public void startGame() {
		running = true;
		while(running)
			stepAll();
	}
	
	public void endGame() {
		running = false;
		String input = "none";
		
		// Asking user to save game's state
		System.out.println("Do you want to save the game's current state?");
		try {
			InputStreamReader isr =	new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			input = br.readLine();
			
			
			// Printing state if user asks to
			if(input.equals("no")) {
				// Do nothing
			}
			else if(input.equals("yes")) {
				System.out.println("Write \"console\" or \"file\" to choose where to write game's state");
				
				// Reading users answer
				input = br.readLine();
				
				// Write state to console
				if(input.equals("console"))
					consoleOutput();
				// Writing state to file
				else if(input.equals("file")) {
					System.out.println("Enter path to file");
					input = br.readLine();
					serialize(input);
				}
				else
					System.out.println("Invalid input");
			}
			else
				System.out.println("Invalid input");
			
			br.close();
		}
		catch(IOException ioe) {
			System.out.println("IO error occurred");
		}
		
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
		System.out.println("What tile do you want to go to?");
		String line = "Enter a number from 0 to ";
		int dirMax = orangutan.getTile().getNeighbours().size()-1;
		line += dirMax;
		System.out.println(line);
		System.out.println("or enter \"letgo\" to let go of followers.");
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
	
	// Print current state to console
	public void consoleOutput() {
		// TODO consoleOutput
	}
	
	public void serialize(String path) {
		// TODO Serialization
		try {
			FileWriter fw = new FileWriter(path);
			PrintWriter pw = new PrintWriter(fw);
			
			// Printing Tiles
			for(Tile tile : tiles) {
				if(tile.getLife() >= 0) {
					pw.println("SoftTile");
					pw.println("-life: " + tile.getLife());
				}
				else
					pw.println("Tile");
				pw.println("-ID: " + tile.getID());
				if(tile.getAnimal() != null)
					pw.println("-animalID: " + tile.getAnimal().getID());
				if(tile.getThing() != null)
					pw.println("-thingID: " + tile.getThing().getID());
				if(tile.getNeighbours() != null) {
					for(Tile neighbour : tile.getNeighbours()) {
						pw.println("-neighbourID: " + neighbour.getID());
					}
				}
			}
			// Printing Orangutans
			for(Orangutan orang : orangutans) {
				pw.println("Orangutan");
				pw.println(orang.getID());
				pw.println("-penalty: " + orang.getPenalty());
				if(orang.getTile() != null)
					pw.println("-tileID: " + orang.getTile());
				if(orang.getFollower() != null)
					pw.println("-followerID: " + orang.getFollower());
			}
			// Printing Pandas
			for(Panda panda : pandas) {
			
			}
			// Printing Things
			for(Thing thing : things) {
			
			}
			
			pw.close();
		} catch (IOException e) {
			System.out.println("Probably incorrect path");
			e.printStackTrace();
		}
	}
	
	public void deserialize(String path) {
		// TODO Deserialization
		ArrayList<String> lines = new ArrayList<>();
		
		try {
			FileReader fr = new FileReader(path);
			BufferedReader br = new BufferedReader(fr);
			while (true) {
				String line = br.readLine();
				if (line == null) break;
				
				// Saving lines
				lines.add(line);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
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
