package main;

public class Wardrobe extends Thing{
	private Wardrobe pair;
	
	public Wardrobe(){}
	
	public Wardrobe(Wardrobe pair) {
		this.pair = pair;
	}
	
	public void setPair(Wardrobe pair) {
		System.out.println("Wardrobe.setPair called");
		this.pair = pair;
	}
	
	@Override
	public void step() {
		System.out.println("Wardrobe.step called");
		if(tile.getAnimal() != null)
			tile.getAnimal().goTo(pair.tile);
	}
	
}
