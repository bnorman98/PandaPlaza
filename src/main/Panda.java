package main;

import java.util.Random;

public abstract class Panda extends Animal {
	
	@Override
	public void step() {
		System.out.println("Panda.step called");
		try {
			// Checking if Panda can move
			if(influencer!= null)
				return;
			
			// Randomising direction
			Random rand = new Random();
			dir = rand.nextInt(tile.getNeighbours().size());
			
			// Moving Panda
			if(tile.getNeighbourAt(dir).getAnimal() == null)
				goTo(tile.getNeighbourAt(dir));
			else step();
		}
		catch(IllegalArgumentException e) {
			e.printStackTrace();
		}
	}
	
	public void follow(Tile nextTile) {
		System.out.println("Panda.follow called");
		if(influencer == null)
		{
			System.out.println("Error: No one to follow.");
			//Error
		}
		Tile myPrevTile = tile;
		goTo(nextTile);
		if(follower != null)
			follower.follow(myPrevTile);
	}
	
	public void getTouched(Orangutan toucher) {
		System.out.println("Panda.getTouched called");
		
		// Releasing paws if the Panda held any
		letGo();
		
		// Holding paws
		if(toucher.follower != null) {
			follower = toucher.follower;
			follower.influencer = this;
		}
		influencer = toucher;
		toucher.follower = this;
		
		// Switching place
		switchPlace(toucher);
	}
	
}
