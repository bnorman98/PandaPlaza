package main;

public class ScaryPanda extends Panda {
	
	@Override
	public void scare() {
		System.out.println("ScaryPanda.scare called");
		letGo();
	}
	
}
