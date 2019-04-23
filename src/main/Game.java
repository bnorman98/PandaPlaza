package main;

import java.io.*;
import java.util.ArrayList;

public class Game {
	private ArrayList<Orangutan> orangutans;
	private ArrayList<Panda> pandas;
	private ArrayList<Thing> things;
	private ArrayList<Tile> tiles;
	
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
	
	/**
	 * True if a Tile with the given ID is a part of the game
	 */
	public boolean isContained(int ID) {
		for(Tile gameTile : tiles) {
			if(gameTile.getID() == ID)
				return true;
		}
		return false;
	}
	
	/**
	  * Getting a Tile by it's ID
	  * Tile must be contained in tiles
	  * else returns null
	  */
	public Tile getTileContained(int ID) {
		for(Tile tile : tiles) {
			if(tile.getID() == ID)
				return tile;
		}
		return null;
	}
	
	/**
	 * The main cycle of the game
	 * Moves the animals while game is running
	 * Then ends the game
	 */
	public void runGame() {
		// Play the game while possible
		while(isAlive()) {
			stepAll();
		}
		// End the game
		endGame();
	}
	
	/**
	 * Checks if there is any animal alive in game
	 */
	private boolean isAlive(){
		return (!(orangutans == null) && !(pandas == null));
	}
	
	public void endGame() {
		String input;
		
		// Asking user to save game's state
		System.out.println("Game over");
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
	
	/**
	 * Steps all steppable objects in the following order:
	 * orangutans, pandas, things
	 * When stepping orangutans asks for user input
	 * User can also let go of followers or end the game
	 */
	public void stepAll() {
		// Stepping Orangutans
		for (Orangutan orangutan : orangutans) {
			// Reading user input
			String input = readInput(orangutan);
			
			// Setting direction
			if(input.equals("IOError") || input.equals("ParseError"))
				System.out.println("Error occurred");
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
	
	/**
	 * Reads user input from console
	 * User can set orangutan's direction,
	 * let go of followers or end the game
	 * @param orangutan This is the orangutan whose direction can be set
	 * @return User's input or error message
	 */
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
			
			// Testing direction number
			if( !(input.equals("letgo") || input.equals("exit"))) {
				int dir = Integer.parseInt(input);
				// Read input again if needed
				if(dir < 0 || dir > dirMax) {
					System.out.println("Incorrect direction");
					readInput(orangutan);
				}
			}
			
			br.close();
			return input;
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
			return "IOError";
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
			return "ParseError";
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


			// Printing Orangutans
			for(Orangutan orang : orangutans) {
				orang.writeOut(pw);
			}
			// Printing Pandas
			for(Panda panda : pandas) {
				panda.writeOut(pw);
			}
			// Printing Things
			for(Thing thing : things) {
				thing.writeOut(pw);
			}
			// Printing Tiles
			for (Tile tile : tiles){
				tile.writeOut(pw);
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
				if (line == null) break; //Ennek
				//	|
				// Saving lines				v
				lines.add(line);        // meg ennek a sorrendj»t nem k»ne felcser»lni?

			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int idx = 0;
		int numofobjects = 0;
		for (int i = 0; i < lines.size() - 1; i++) {
			if (lines.get(i).charAt(0) != '-') {
				String[] parts = lines.get(i+1).split(" ");
				switch (lines.get(i)) {
					case "ScaryPanda":
						ScaryPanda sp = new ScaryPanda();
						sp.setID(Integer.parseInt(parts[1]));
						pandas.add(sp);
						break;
					case "JumpyPanda":
						JumpyPanda jp = new JumpyPanda();
						jp.setID(Integer.parseInt(parts[1]));
						pandas.add(jp);

						break;
					case "SleepyPanda":
						SleepyPanda slp = new SleepyPanda();
						slp.setID(Integer.parseInt(parts[1]));
						pandas.add(slp);
						break;
					case "Arcade":
						Arcade a = new Arcade();
						a.setID(Integer.parseInt(parts[1]));
						things.add(a);
						break;
					case "Chair":
						Chair c = new Chair();
						c.setID(Integer.parseInt(parts[1]));
						things.add(c);
						break;
					case "ChocolateAutomat":
						ChocolateAutomat ca = new ChocolateAutomat();
						ca.setID(Integer.parseInt(parts[1]));
						things.add(ca);
						break;
					case "EndPoint":
						EndPoint ep = new EndPoint();
						ep.setID(Integer.parseInt(parts[1]));
						tiles.add(ep);
						break;
					case "Orangutan":
						Orangutan o = new Orangutan();
						o.setID(Integer.parseInt(parts[1]));
						orangutans.add(o);
						break;
					case "SoftTile":
						SoftTile st = new SoftTile();
						st.setID(Integer.parseInt(parts[1]));
						tiles.add(st);
						break;
					case "Tile":
						Tile t = new Tile();
						t.setID(Integer.parseInt(parts[1]));
						tiles.add(t);
						break;
					case "Wardrobe":
						Wardrobe w = new Wardrobe();
						w.setID(Integer.parseInt(parts[1]));
						things.add(w);
						break;
					default: break;
				}
			}

		}

	}
	
	public boolean generateRandom(int chance) {
		int rand = (int) (Math.random() * 100);
		System.out.println("Randomised: " + rand);
		return rand > chance;
	}
	
}
