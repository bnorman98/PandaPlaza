package main;

import javafx.application.Platform;
import pGraphics.GraphicalApplication;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Game {
	/**
	 * List, that contains all the Orangutans in the game
	 */
	private ArrayList<Orangutan> orangutans;
	/**
	 * List, that contains all the Pandas in the game
	 */
	private ArrayList<Panda> pandas;
	/**
	 * List, that contains all the Things in the game
	 */
	private ArrayList<Thing> things;
	/**
	 * List, that contains all the Tiles in the game
	 */
	private ArrayList<Tile> tiles;
	/**
	 * List, that contains all the
	 * Ready-to-Remove Pandas in the game
	 */
	private ArrayList<Panda> pandasToRemove;
	/**
	 * List, that contains all the
	 * Ready-to-Remove Orangutans in the game
	 */
	private ArrayList<Orangutan> orangutansToRemove;
	/**
	 * It represents the end of the game
	 * If its true, the game has ended
	 */
	private boolean endgame = false;

	/**
	 * A GraphicalApplication
	 */
	private GraphicalApplication view;
	/**
	 * A Game, that cointais everything
	 */
	private static Game instance;

	/**
	 * Ctor of the game class. Private
	 * because it's singleton
	 *
	 */
	private Game() {
		orangutans = new ArrayList<>();
		pandas = new ArrayList<>();
		things = new ArrayList<>();
		tiles = new ArrayList<>();
		pandasToRemove = new ArrayList<>();
		orangutansToRemove = new ArrayList<>();
	}

	/**
	 *
	 * @return the instance
	 * Game object
	 */
	public static Game getInstance() {
		if (instance == null)
			instance = new Game();
		return instance;
	}

	/**
	 * Sets the view
	 * @param app The view
	 *            that will be set
	 */
	public void setView(GraphicalApplication app){
		view = app;
	}

	/**
	 * A getter method
	 * @return the orangutans list
	 */
	public ArrayList<Orangutan> getOrangutans() {
		return orangutans;
	}
	/**
	 * A getter method
	 * @return the pandas list
	 */
	public ArrayList<Panda> getPandas() {
		return pandas;
	}
	/**
	 * A getter method
	 * @return the things list
	 */
	public ArrayList<Thing> getThings() {
		return things;
	}
	/**
	 * A getter method
	 * @return the tiles list
	 */
	public ArrayList<Tile> getTiles() {
		return tiles;
	}

	/**
	 * Adds a new Orangutan to the
	 * list, that contains them
	 * @param newOrangutan The new Orangutan, that will
	 *                     be added
	 */
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

	/**
	 * Adds a new Panda to the
	 * list, that contains them
	 * @param newPanda The new Panda, that will
	 *                     be added
	 */
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
	/**
	 * Adds a new Thing to the
	 * list, that contains them
	 * @param newThing The new Thing, that will
	 *                     be added
	 */
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
	/**
	 * Adds a new Tile to the
	 * list, that contains them
	 * @param newTile The new Tile, that will
	 *                     be added
	 */
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
	 *
	 * @param ID The ID, we want to get the Orangutan on
	 * @return Orangutan with the given ID
	 */
	public Orangutan getOrangutanContained(int ID) {
		for(Orangutan orang : orangutans) {
			if(orang.getID() == ID)
				return orang;
		}
		return null;
	}
	/**
	 *
	 * @param ID The ID, we want to get the Panda on
	 * @return Panda with the given ID
	 */
	public Panda getPandaContained(int ID) {
		for(Panda panda : pandas) {
			if(panda.getID() == ID)
				return panda;
		}
		return null;
	}
	/**
	 *
	 * @param ID The ID, we want to get the Thing on
	 * @return Thing with the given ID
	 */
	public Thing getThingContained(int ID) {
		for(Thing thing : things) {
			if(thing.getID() == ID)
				return thing;
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
		while(!endgame) {
			Platform.runLater(() -> view.drawGame());
			stepAll();
			
		}
		// End the game
		endGame();
	}
	
	/**
	 * Checks if there is any animal alive in game
	 */
	private boolean isAlive(){
		return !(pandas.size() == 0 || orangutans.size() == 0);
	}

	/**
	 * Manages the end of the game
	 * Waits for the command in the command line
	 * How to saves, or to save the game
	 * Makes the serialization if its the
	 * choosen option
	 */
	public void endGame() {
		String input;
		
		// Asking user to save game's state
		System.out.println("Game over");
		System.out.println("Do you want to save the game's current state? [yes/no]");
		try {
			InputStreamReader isr =	new InputStreamReader(System.in);
			BufferedReader brg = new BufferedReader(isr);
			input = brg.readLine();
			
			// Printing state if user asks to
			if(input.equals("no")) {
				// Redraw the menu
				Platform.runLater(() -> view.drawMenu());
			}
			else if(input.equals("yes")) {
				System.out.println("Write \"console\" or \"file\" to choose where to write game's state");
				
				// Reading users answer
				input = brg.readLine();
				
				// Write state to console
				if(input.equals("console"))
					consoleOutput();
				// Writing state to file
				else if(input.equals("file")) {
					System.out.println("Enter path to file");
					input = brg.readLine();
					serialize(input);
				}
				else
					System.out.println("Invalid input");
			}
			else
				System.out.println("Invalid input");
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

		Iterator<Orangutan> iterO = orangutans.iterator();
		while (iterO.hasNext()) {
			Orangutan orangutan = iterO.next();
			// Reading user input
			String input = readInput(orangutan);
			
			// Setting direction
			if(input.equals("IOError") || input.equals("ParseError"))
				System.out.println("Error occurred");
			else if(input.equals("endgame")) {
				endgame = true;
				return;
			}
			else if(input.equals("letgo"))
				orangutan.letGo();
			else {
				// Setting direction
				orangutan.setDir(input);
				// Moving Orangutan
				orangutan.step();
			}
			
			// Redraw the game
			Platform.runLater(() -> view.drawGame());
		}
		
		// Stepping Pandas
		Iterator<Panda> iterP = pandas.iterator();
		while (iterP.hasNext()) {
			Panda panda = iterP.next();
			panda.step();
		}
		
		// Stepping Things
		for(Thing thing : things) {
			thing.step();
		}

		orangutans.removeAll(orangutansToRemove);
		if (!isAlive())
			endgame = true;

		pandas.removeAll(pandasToRemove);
		if (!isAlive())
			endgame = true;
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
		System.out.println("\nWhat tile do you want to go to?");
		String line = "Enter a number from 0 to ";
		int dirMax = orangutan.getTile().getNeighbours().size() - 1;
		line += dirMax;
		System.out.println(line);
		System.out.println("or enter \"letgo\" to let go of followers.\nEnter \"endgame\" to end the game");
		// Print neighbours
		int i = 0;
		for(Tile neighbour: orangutan.getTile().getNeighbours()) {
			System.out.println("\t" + i + ": " + neighbour.getID());
			i++;
		}
		
		// Reading input
		try {
			String input;
			InputStreamReader isr =	new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			input = br.readLine();
			
			// Testing direction number
			if( !(input.equals("letgo") || input.equals("endgame"))) {
				int dir = Integer.parseInt(input);
				// Read input again if needed
				if(dir < 0 || dir > dirMax) {
					System.out.println("Incorrect direction");
					input = readInput(orangutan);
				}
				if (orangutan.getFollower() == orangutan.getTile().getNeighbourAt(dir).getAnimal()){
					System.out.println("Panda already follows the orangutan");
					input = readInput(orangutan);
				}
			}
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

	/**
	 * Prints the current state
	 * on the console
	 */
	public void consoleOutput() {
		try {
			PrintWriter pw = new PrintWriter(System.out);
			// Printing Orangutans
			for (Orangutan orang : orangutans) {
				orang.writeOut(pw);
			}
			// Printing Pandas
			for (Panda panda : pandas) {
				panda.writeOut(pw);
			}
			// Printing Things
			for (Thing thing : things) {
				thing.writeOut(pw);
			}
			// Printing Tiles
			for (Tile tile : tiles) {
				tile.writeOut(pw);
			}

			pw.close();
		} catch (Exception e) {
			System.out.println("Failed to create PrintWriter");
			e.printStackTrace();
		}


	}
	/**
	 * Serializes all the objects on the map
	 * Calls for the WriteOut method
	 * @param path where the file should be created
	 */
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
	
	/**
	 * Clears all elements of the game
	 */
	public void clearGame() {
		endgame = false;
		orangutans.clear();
		pandas.clear();
		things.clear();
		tiles.clear();
		pandasToRemove.clear();
		orangutansToRemove.clear();
	}
	
	/**
	 * Deserializes all the objects from a file
	 * Loads the objects into the game
	 * Builds the relationships between the deserialized objects
	 * @param path where the deserializing file should be found
	 */
	public void deserialize(String path) {
		clearGame();
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
		for (int i = 0; i < lines.size() - 1; i++) {
			if (lines.get(i).charAt(0) != '-') {
				String[] parts = lines.get(i+1).split(" ");
				switch (lines.get(i)) {
					case "ScaryPanda":
						getPandaContained(Integer.parseInt(parts[1])).readIn(lines, i);
						break;
					case "JumpyPanda":
						getPandaContained(Integer.parseInt(parts[1])).readIn(lines, i);
						break;
					case "SleepyPanda":
						getPandaContained(Integer.parseInt(parts[1])).readIn(lines, i);
						break;
					case "Arcade":
						getThingContained(Integer.parseInt(parts[1])).readIn(lines, i);
						break;
					case "Chair":
						getThingContained(Integer.parseInt(parts[1])).readIn(lines, i);
						break;
					case "ChocolateAutomat":
						getThingContained(Integer.parseInt(parts[1])).readIn(lines, i);
						break;
					case "EndPoint":
						getTileContained(Integer.parseInt(parts[1])).readIn(lines, i);
						break;
					case "Orangutan":
						getOrangutanContained(Integer.parseInt(parts[1])).readIn(lines, i);
						break;
					case "SoftTile":
						getTileContained(Integer.parseInt(parts[1])).readIn(lines, i);
						break;
					case "Tile":
						getTileContained(Integer.parseInt(parts[1])).readIn(lines, i);
						break;
					case "Wardrobe":
						getThingContained(Integer.parseInt(parts[1])).readIn(lines, i);
						break;
					default: break;
				}
			}
		}
	}

	/**
	 * Generates a random number and gives back, that
	 * is it greater or not than the given number
	 * @param chance the number to be compared
	 * @return true if the generated number is greater than the given
	 */
	public boolean generateRandom(int chance) {
		int rand = (int) (Math.random() * 100);
		System.out.println("Randomised: " + rand);
		return rand > chance;
	}

	/**
	 * Kills a Panda
	 * Adds the panda to the "Pandas to remove" list
	 * @param p the Panda to be removed
	 */
	public void killPanda(Panda p){
		pandasToRemove.add(p);
	}

	/**
	 * Kills an Orangutan
	 * Adds the Orangutan to the "Orangutans to remove" list
	 * @param o the Orangutan to be removed
	 */
	public void killOrangutan(Orangutan o){
		orangutansToRemove.add(o);
	}
	
}
