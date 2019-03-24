package main;

import java.util.Random;

//TODO: This is an abstract class!
public class Panda extends Animal {
	
	@Override
	public void step() {
		try {
			if(influencer!= null)
				return;
			Random rand = new Random();
			dir = rand.nextInt(tile.getNeighbours().size());
			if(tile.getNeighbourAt(dir).getAnimal() == null)
				goTo(tile.getNeighbourAt(dir));
			else step();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			System.out.println("Panda stepped");
		}

	}
	
	public void follow(Tile nextTile){
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
		if(toucher.follower != null){
			follower = toucher.follower;
			follower.influencer = this;
		}
		influencer = toucher;
		toucher.follower = this;
		toucher.goTo(tile);
	}
	
}
