package main;

public class Wardrobe extends Thing{
	
	private Wardrobe pair;
	
	// Azert kell ures kostruktor és setPair, hogy elso peldanyt letre lehessen hozni a parbol
	public Wardrobe(){}
	
	public Wardrobe(Wardrobe pair) {
		this.pair = pair;
	}
	
	public void setPair(Wardrobe pair) {
		this.pair = pair;
	}
	
	@Override
	public void step() {
		if(tile.getAnimal() != null) {
			tile.getAnimal().goTo(pair.tile);
		}
	}
}
