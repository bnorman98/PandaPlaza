package main;

import java.util.ArrayList;
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
	public void readIn(ArrayList<String> lines, int idx){
		for (int i=idx+1;i<lines.size();i++){
			String[] parts = lines.get(i).split(" ");
			if (parts[0].charAt(0) != '-'){
				i = lines.size();
			}
			switch (parts[0]){
				case "-tileID:":
					this.setTile(Game.getInstance().getTileContained(Integer.parseInt(parts[1])));
					break;
				case "-followerID:":
					this.setFollower(Game.getInstance().getPandaContained(Integer.parseInt(parts[1])));
					break;
				case "-influencerID:":
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
}
