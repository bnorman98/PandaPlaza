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
		
		letGo();
	}
	
}
