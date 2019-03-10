package main;

public class Orangutan extends Animal {
	
	@Override
	public void step() {}
	
	@Override
	public void addScore(int score) {
		this.score += score;
	}
	
	public void die(){}
}
