package main;

public class JumpyPanda extends Panda {
	
	@Override
	public void jump(){
		tile.stepped();
	}
	
}
