// KING SAUD UNIVERSITY
// CCIS
// CSC 361

// NAME:  ...................
// ID: ...................

import java.awt.Color;
import java.io.*;
import java.util.*;

public class Agent {
	enum Status {
		EMPTY {
			char nothing = ' ';
			char robot = 'R';
			char treasure = 'T';
			char robotAndTreasure = 'U';
		},
		HOLE {
			char nothing = 'H';
			char robot = 'X';
			char treasure = 'Y';
			char robotAndTreasure = 'Z';
		}
	}

	public static void main(String[] args) throws FileNotFoundException {

		// int n_args = args.length;
		// if (n_args!=4) {
		// System.out.println("ERROR: ILLEGAL NUMBER OF ARGUMENTS.");
		// System.out.println("Number of arguments must be 4");
		// return;
		// }
		// String mapFile = args[0];
		// String commandsFile = args[1];
		// String finalMapFile = args[2];
		// String logFile = args[3];

		// WRITE YOUR CODE DOWN HERE:
		// State inital = new State(mapFile);
		// inital.doCommandAndLog(commandsFile);

		// State s = new State("simple.map.txt");
		File f = new File("simple.map.txt");
		Scanner in = new Scanner(f);
		
		
		// String str = "";
		// while(in.hasNextLine()){
		// str = str+in.nextLine();
		// }
		// System.out.println(str);

		// ....
		// String s[] = Status.valu
		// System.out.println(Status.);
		System.out.println(Status.EMPTY);
	}

}
