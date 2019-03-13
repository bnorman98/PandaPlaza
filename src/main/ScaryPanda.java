package main;

public class ScaryPanda extends Panda {
	
	@Override
	public void scare(){
		if(influencer != null) {
			influencer.follower = null;
			influencer = null;
		}
		if(follower != null)
			letGo();
	}
	
}
