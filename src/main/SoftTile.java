package main;

public class SoftTile extends Tile {
	
	private int life;
	
	public SoftTile() {
		life = 20;
	}
	public SoftTile(int life) {
		this.life = life;
	}
	public int getLife() {return life;}
	
	public void stepped() {
		if (life > 0)
			life--;
		else{
			getAnimal().die();
			setAnimal(null);
		}
	}
}
