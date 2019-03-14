package main;

public class Wardrobe extends Thing{
	private Wardrobe pair;
	
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
