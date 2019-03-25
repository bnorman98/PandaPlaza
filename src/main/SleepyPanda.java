package main;

public class SleepyPanda extends Panda {
	
	private boolean isSleeping = false;
	
	@Override
	public void step() {
		System.out.println("SleepyPanda.step called");
		if(isSleeping)
			isSleeping = false;
		else goTo(tile.getNeighbourAt(dir));
	}
	
	@Override
	public void sleep() {
		System.out.println("SleepyPanda.sleep called");
		
		// Falling asleep
		isSleeping = true;
		letGo();
	}
	
}
