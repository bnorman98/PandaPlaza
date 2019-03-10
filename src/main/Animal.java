package main;

public abstract class Animal implements Steppable {
	protected int dir = 0;
	protected int score = 0;
	protected Tile tile;
	protected Panda follower;
	protected Animal influencer;
	
	//Szerintem ez kell ide, hogy a tesztben megmondjuk merre lépjenek.
	public void setDir(int newDir) {
		dir = newDir;
	}
	
	public void getTouched(Animal toucher){}
	
	public void goTo(Tile newTile){
		tile.setAnimal(null);
		tile = newTile;
		tile.setAnimal(this);
		tile.stepped();
	}
	
	/*
	 * Ez kell meg?
	 * Az influencer-follower láncot felszabadítja, ettől az állattól kezdve
	 */
	public void disconnect() {
		Animal currentAnimal = this;
		while (currentAnimal.follower != null){
			Animal tmp = currentAnimal.follower;
			currentAnimal.follower = null;
			currentAnimal = tmp;
		}
		while (currentAnimal.influencer != this.influencer){
			Animal tmp = currentAnimal.influencer;
			currentAnimal.influencer = null;
			currentAnimal = tmp;
		}
		this.influencer = null;
	}
	
	public void die(){}
	public void scare(){}
	public void jump(){}
	public void sleep(){}
	//public int countFollowers(){}
	public void killFollowers(){}
	public void addScore(int score){}	// Direkt üres a törzs!

}
