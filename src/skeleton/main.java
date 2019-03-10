package skeleton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class main {
	public static void main(String[] args) throws IOException {
		InputStreamReader isr =	new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		Commands skeleton = new Commands();
		
		while(true) {
			String line = br.readLine();
			if (line == null) break;
			String cmd[] = line.split(" ");
			skeleton.commands(cmd);
		}
		br.close();
	}
}
