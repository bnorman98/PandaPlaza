package main;

import java.util.ArrayList;
import java.util.Random;

/**
 * Represents all of the ("basic") Pandas
 */
public abstract class Panda extends Animal {
	
	@Override
	/**
	 * Steps the Panda
	 * Its totally random, moves the Panda from its tile to an another tile
	 * (Ofc, the tiles has to be neighbours)
	 */
	public void step() {
		System.out.println("Panda.step called");
		try {
			// Checking if Panda can move
			if(influencer!= null)
				return;
			
			// Randomising direction
			Random rand = new Random();
			ArrayList<Tile> possibleTiles = new ArrayList<>();
			if (tile == null)
				return;
			for (Tile neighbour : tile.getNeighbours()) {
				if (neighbour.getAnimal() == null){
					possibleTiles.add(neighbour);
				}
			}
			if (possibleTiles.isEmpty())
				return;
			else{
				//Moving Panda
				dir = rand.nextInt(possibleTiles.size());
				goTo(tile.getNeighbourAt(dir));
				tile.stepped();
			}
		}
		catch(IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	/**
	 * It makes the Panda follow someone
	 * @param nextTile The next tile
	 */
	public void follow(Tile nextTile) {
		System.out.println("Panda.follow called");
		if(influencer == null)
		{
			System.out.println("Error: No one to follow.");
			//Error
		}
		if (nextTile.getAnimal() != null)
			return;
		Tile myPrevTile = tile;
		goTo(nextTile);
		tile.stepped();
		if(follower != null)
			follower.follow(myPrevTile);
	}

	/**
	 * If a Panda has been touched by an Orangutan
	 * This method will be called
	 * @param toucher Represents the Orangutan, who touched the animal
	 */
	public void getTouched(Orangutan toucher) {
		System.out.println("Panda.getTouched called");

		// Releasing paws if the Panda held any
		letGo();

		// Switching place
		switchPlace(toucher);
		
		// Holding paws
		if(toucher.follower != null) {
			follower = toucher.follower;
			follower.influencer = this;
		}
		influencer = toucher;
		toucher.follower = this;
	}

	/**
	 * An assistant method
	 * Helps the deserialization
	 * Knows the scheme of the deserialization
	 * @param lines The lines that contain the informations about the Panda
	 * @param idx Index of the Panda
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
				case "-InfluencerID:":
					Orangutan o = Game.getInstance().getOrangutanContained(Integer.parseInt(parts[1]));
					if (o != null){
						this.setInfluencer(o);
						break;
					}
					Panda p = Game.getInstance().getPandaContained(Integer.parseInt(parts[1]));
					if (p != null){
						this.setInfluencer(p);
					}
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
		System.out.println("Panda.die called");
		// Letting go of other animals
		letGo();

		// Remove from tile
		tile.setAnimal(null);
		tile = null;
		Game.getInstance().killPanda(this);
	}
}
