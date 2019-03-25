package skeleton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		InputStreamReader isr =	new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);

		// Printing basic commands
		System.out.println("Welcome to PandaPlaza");
		System.out.println("Type command:\n > help - to open user manual\n > exit - to close the program");

		Commands skeleton = new Commands();
		boolean exitCondition = false;
		
		while(!exitCondition) {
			String line = br.readLine();
			if (line == null) break;
			String cmd[] = line.split(" ");
			exitCondition = skeleton.commands(cmd);
		}
		br.close();
	}
}
