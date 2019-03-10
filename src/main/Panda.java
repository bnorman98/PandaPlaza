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
		catch (Exception e){}
		finally{
			System.out.println("Panda stepped.");
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
	
	/*
	 * Ezt full at kell nezni!
	 * Atirtam valahogy gyorsan, de lehet igy nem jo
	 */
	//Szerintem ez így jó egyébként - Norman
	public void getTouched(Orangutan toucher){
		if(toucher.follower != null){
			follower = toucher.follower;
			follower.influencer = this;
		}
		influencer = toucher;
		toucher.follower = this;
		toucher.goTo(tile);
		goTo(toucher.tile);
	}
	
	public void die(){}
}
