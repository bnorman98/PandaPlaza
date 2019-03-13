package main;

public class Panda extends Animal {
	
	@Override
	public void step() {
		try{
			if(influencer!= null)
				return;
			for (Tile t: tile.getNeighbours()) {
				if (t.getAnimal() == null){
					goTo(t);
					break;
				}
			}
		}
		catch(Exception e) {}
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
		Tile tmp = toucher.tile;
		toucher.goTo(tile);
		tile = tmp;
		tile.setAnimal(this);
		tile.stepped();
	}
	
	@Override
	public void die(){
		// Letting go of other animals
		if(influencer != null)
			influencer.follower = null;
		if(follower != null)
			follower.letGo();
		
		// Remove from tile
		tile.setAnimal(null);
		tile = null;
	}
	
	@Override
	public void letGo() {
		if(influencer != null)
			influencer.setFollower(null);
		if(follower != null)
			follower.letGo();
	}
}
