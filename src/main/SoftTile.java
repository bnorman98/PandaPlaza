package main;

public class SoftTile extends Tile {
	
	private int life;
	
	public SoftTile() {
		life = 20;
	}
	public SoftTile(int life) {
		this.life = life;
	}
	public int getLife() {
		System.out.println("SoftTile.getLife called");
		return life;
	}
	
	@Override
	public void stepped() {
		System.out.println("SoftTile.stepped called");
		if(life > 0)
			life--;
		if(life == 0)
			animal.die();
	}
	
	@Override
	public String toString() {
		return "SoftTile\n\tID: " + ID + "\n\tlife: " + life;
	}
}
