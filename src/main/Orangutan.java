package main;

import java.io.PrintWriter;
import java.util.ArrayList;
/**
 * Represents all the Orangutans
 * They have to catch the Pandas and take them to the exit point
 * They are controlled by the user
 */
public class Orangutan extends Animal {
	/**
	 * The score of the Orangutan
	 * It incr if it takes a Panda to the exit
	 */
	private int score = 0;
	/**
	 * The penalty of the Orangutan
	 */
	private int penalty = 0;

	/**
	 *
	 * @return the penalty of the Orangutan
	 */
	public int getPenalty() {
		return penalty;
	}

	/**
	 * Steps the orangutan
	 * reduces its penalty if its greater than zero
	 *
	 */
	@Override
	public void step() {
		System.out.println("Orangutan.step called");
		if (penalty > 0){
			penalty--;
		}
		if (tile != null && tile.getNumOfNeighbours() != 0) {
			boolean folBeforeStep = false;
			if (follower != null)
				folBeforeStep = true;
			Tile myPrevTile = tile;
			goTo(tile.getNeighbourAt(dir));
			tile.stepped();
			if (folBeforeStep && follower != null)
				follower.follow(myPrevTile);
		}
	}

	/**
	 * Increases the score of the orangutan
	 * @param score the number, will be added to the Orangutans score
	 */
	@Override
	public void addScore(int score) {
		this.score += score;
	}

	/**
	 *
	 * @return the score of the Orangutan
	 */
	public int getScore() {
		return score;
	}

	/**
	 * A helper method for the serialization
	 * Follows the scheme of the serialization
	 * @return Ready-to-serialize string with the datas of the Orangutan
	 */
	//@Override
	public String toString() {
		return "Orangutan\n\tTile: " + tile.toString();	// Itt még a behúzást meg kell javítani
	}

	/**
	 * It manages the touching by an another Orangutan
	 * Orangutans can steal one another's follower pandas
	 * @param toucher Represents the Orangutan, who touched the Orangutan
	 */
	@Override
	public void getTouched(Orangutan toucher) {
		// Checking if toucher Orangutan can steal Pandas
		if(toucher.countFollowers() != 0)
			return;
		
		// Saving original number of followers
		int followerCnt = this.countFollowers();
		
		// Switching place
		switchPlace(toucher);
		
		// Giving Pandas to toucher
		toucher.setFollower(follower);
		this.follower = null;
		
		// Can't step for 3 turns if
		if(followerCnt != 0)
		penalty = 3;
		
	}

	/**
	 * It can navigate the Orangutan to a new Tile
	 * Calls the tile's stepped method, represents that
	 * the someone has been left tha tile
	 * @param newTile Destination tile
	 */
	@Override
	public void goTo(Tile newTile) {
		System.out.println("Orangutan.goTo called");
		
		// Touching Animal found in moving direction if not penalized
		if(newTile.getAnimal() != null) {
			if(penalty != 0)
				return;
			else
				newTile.getAnimal().getTouched(this);
		}
		else {
			tile.setAnimal(null);
			newTile.setAnimal(this);
			tile = newTile;
			tile.stepped();
		}
		
	}

	/**
	 * Serializes itself into the given pw
	 * @param pw The method serializes the Arcade into the given PrintWriter pw
	 */
	public void writeOut(PrintWriter pw){
		pw.println("Orangutan");
		pw.println("-ID: " + this.getID());
		pw.println("-Score: " + this.getScore());
		pw.println("-Penalty: " + this.getPenalty());
		if(this.getTile() != null)
			pw.println("-TileID: " + this.getTile().getID());
		if(this.getFollower() != null)
			pw.println("-FollowerID: " + this.getFollower().getID());
	}

	/**
	 * An assistant method
	 * Helps the deserialization
	 * Knows the scheme of the deserialization
	 * @param lines The lines that contain the informations about the Orangutan
	 * @param idx Index of the Orangutan
	 */
	public void readIn(ArrayList<String> lines, int idx){
		for (int i=idx+1;i<lines.size();i++){
			String[] parts = lines.get(i).split(" ");
			if (parts[0].charAt(0) != '-'){
				i = lines.size();
			}
			switch (parts[0]){
				case "-TileID:":
					this.setTile(Game.getInstance().getTileContained(Integer.parseInt(parts[1])));
					break;
				case "-FollowerID:":
					this.setFollower(Game.getInstance().getPandaContained(Integer.parseInt(parts[1])));
					break;
				case "-Score:":
					this.score = Integer.parseInt(parts[1]);
				case "-Penalty:":
					this.penalty = Integer.parseInt(parts[1]);
					break;
				default: break;
			}
		}
	}

	/**
	 * Represents the death of a Panda
	 * Removes him from the tile, he stands on
	 * Make his followers free
	 */
	public void die(){
		System.out.println("Orangutan.die called");
		// Letting go of other animals
		letGo();

		// Remove from tile
		tile.setAnimal(null);
		tile = null;
		Game.getInstance().killOrangutan(this);
	}
}
