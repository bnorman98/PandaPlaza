package main;

public class Orangutan extends Animal {
	
	@Override
	public void step() {}
	
	@Override
	public void addScore(int score) {
		this.score += score;
	}
	
	@Override
	public void die(){}
	
	@Override
	public void letGo(){
		if(follower != null)
			follower.letGo();
	}
	
	@Override
	public int countFollowers(){
		/*
		 * Returns 1 to compile
		 * Not ready
		 */
		return 1;
	}
}
