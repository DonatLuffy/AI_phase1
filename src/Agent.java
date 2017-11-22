// KING SAUD UNIVERSITY
// CCIS
// CSC 361

// NAME:  ...................
// ID: ...................

import java.io.*;

public class Agent {

	public static void main(String[] args) throws FileNotFoundException {

		int n_args = args.length;
		if (n_args != 4) {
			System.out.println("ERROR: ILLEGAL NUMBER OF ARGUMENTS.");
			System.out.println("Number of arguments must be 4");
			return;
		}
		String mapFile = args[0];
		String commandsFile = args[1];
		String finalMapFile = args[2];
		String logFile = args[3];

		// WRITE YOUR CODE DOWN HERE:
		State inital = new State(mapFile);
		inital.run(commandsFile, finalMapFile, logFile);
		
	}

}
