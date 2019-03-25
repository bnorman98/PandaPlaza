package main;

public class JumpyPanda extends Panda {
	
	@Override
	public void jump() {
		System.out.println("JumpyPanda.jump called");
		tile.stepped();
	}
	
}
