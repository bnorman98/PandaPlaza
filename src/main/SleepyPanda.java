package main;

public class SleepyPanda extends Panda {
	
	private boolean isSleeping = false;
	
	@Override
	public void step(){
		if(isSleeping)
			isSleeping = false;
		else goTo(tile.getNeighbourAt(dir));
	}
	
	@Override
	public void sleep(){
		isSleeping = true;
		
		if(influencer != null) {
			influencer.follower = null;
			influencer = null;
		}
		if(follower != null)
			letGo();
	}
	
}
