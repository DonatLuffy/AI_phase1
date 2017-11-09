// KING SAUD UNIVERSITY
// CCIS
// CSC 361

// NAME:  ...................
// ID: ...................

import java.io.*;
import java.util.*;

public class State {

	// THE FOLLOWING IS AN EXAMPLE OF THE ATTRIBUTES
	// THAT YOU NEED TO PUT IN A STATE.
	// YOU SHOULD CHANGE IT:

	public static int battery = 10;
	public static final char chargeStation = 'S';

	private int x; // index R at row
	private int y; // index R at column
	private char[][] map; // THE MAP
	private int N;
	private int M;

	// -----------------------------

	// THE FOLLOWING ARE THE CONSTRUCTORS
	// YOU CAN CHANGE OR REPALCE THEM.

	// CONSTRUCTOR 1:
	// THIS CONSTRUCTOR WILL CREATE A STATE FROM FILE.
	State(String fileName) throws FileNotFoundException {
		try {
			File f = new File(fileName);
			Scanner in = new Scanner(f);
			N = in.nextInt();
			M = in.nextInt();
			String str = "";
			while (in.hasNextLine()) {
				str = str + in.nextLine();
			}
			map = new char[N][M];
			int sizeArray = N * M;
			int k = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (k <= sizeArray - 1)
						map[i][j] = str.charAt(k++);
				}
			}
			in.close();
		} catch (InputMismatchException e) {
			System.out.println(e.fillInStackTrace());
		}
	}

	// CONSTRUCTOR 2:
	// THIS CONSTRUCTOR WILL CREATE A RANDOM STATE.
	State(int n, int m, int rseed) {
		// ...
	}

	// CONSTRUCTOR 3:
	// COPY CONSTRUCTOR.
	State(State s) {

		x = s.x;
		y = s.y;
		// ...
	}

	// -----------------------------

	// STATE GETTERS AND SETTERS
	// YOU CAN & SHOULD CHANGE THEM.
	// IF YOU HAVE QUESTIONS ASK THE INSTRUCTOR.
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public char[][] getMap() {

		return map;
	}

	// METHOD THAT TELLS WHETHER THIS STATE IS EQUAL
	// TO ANOTHER STATE.
	public boolean equal(State s) {
		return ((x == s.x) && (y == s.y) && (map.equals(s.map)));
	}

	// -----------------------------

	// THE ACTIONS:
	// YOU CAN CHANGE THE ACTIONS CONTENTS,
	// WHAT THE ACTIONS RETURN, ETC.

	// ACTION 1
	// RETURNS BOOLEAN:
	// TRUE MEANS ACTION WAS APPLIED,
	// FALSE MEANS ACTOIN COULD NOT AND WAS NOT APPLIED.
	// public boolean action1() {
	// if (x = x) // (...)
	// // do something
	// return false;
	// return true;
	// }
	//
	// // ACTION 2
	// public boolean action2() {
	// // ...
	// }

	// YOU CAN ADD MORE ACTIONS HERE.

	public void run(String command, String finalMap, String log) {
		try {
			Scanner in = new Scanner(new File(command));
			while (in.hasNext() && battery > 0) {
				writeLogs(log, Booster_moves(in.next()));
				if (fallInHole())
					writeLogs(log, "HOLE");
				if (foundTreasure())
					writeLogs(log, "GOAL");

			}
			in.close();
			FileWriter f = new FileWriter(new File(finalMap));
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					f.write(map[i][j]);
				}
				f.write("\n");
			}
			f.close();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}

	public void chargeStation() {
		if (map[x][y] == 'S')
			battery = 10;
	}

	public boolean move_N_B() {
		battery -= 3;
		if (move_N() && move_N())
			return true;
		else
			return false;
	}

	public boolean move_S_B() {
		battery -= 3;
		if (move_S() && move_S())
			return true;
		else
			return false;
	}

	public boolean move_E_B() {
		battery -= 3;
		if (move_E() && move_E())
			return true;
		else
			return false;
	}

	public boolean move_W_B() {
		battery -= 3;
		if (move_W() && move_W())
			return true;
		else
			return false;
	}

	public String Booster_moves(String cmd) {
		String log = "ERROR";
		switch (cmd) {
		case "move-N":
			if (move_N_B())
				log = "DONE";
			else
				log = "FAIL";
			break;
		case "move-S":
			if (move_S_B())
				log = "DONE";
			else
				log = "FAIL";
			break;
		case "move-E":
			if (move_E_B())
				log = "DONE";
			else
				log = "FAIL";
			break;
		case "move-W":
			if (move_W_B())
				log = "DONE";
			else
				log = "FAIL";
			break;
		}
		return log;
	}

	public void changeCell() {
		switch (map[x][y]) {
		case ' ':
			map[x][y] = 'R';
			break;
		case 'T':
			map[x][y] = 'U';
			break;
		case 'H':
			map[x][y] = 'X';
			break;
		case 'Y':
			map[x][y] = 'Z';
			break;
		case 'X':
			map[x][y] = 'X';
			break;
		}
	}

	public void changPreviousCell() {
		switch (map[x][y]) {
		case 'R':
			map[x][y] = ' ';
			break;
		case 'U':
			map[x][y] = 'T';
			break;
		}
	}

	// this method return void, it can return boolean
	public boolean move_N() {
		if (x == 0)
			return false;
		else if (map[x][y] == 'X' || map[x][y] == 'Z')
			return false;
		else if (map[x - 1][y] == 'B')
			return false;
		else {
			changPreviousCell();
			x--;
			changeCell();
			return true;
		}
	}

	// this method return void, it can return boolean
	public boolean move_S() {
		if (x == N - 1)
			return false;
		else if (map[x][y] == 'X' || map[x][y] == 'Z')
			return false;
		else if (map[x + 1][y] == 'B')
			return false;
		else {
			changPreviousCell();
			x++;
			changeCell();
			return true;
		}
	}

	// this method return void, it can return boolean
	public boolean move_E() {
		if (y == M - 1)
			return false;
		else if (map[x][y] == 'X' || map[x][y] == 'Z')
			return false;
		else if (map[x][y + 1] == 'B')
			return false;
		else {
			changPreviousCell();
			y++;
			changeCell();
			return true;
		}
	}

	// this method return void, it can return boolean
	public boolean move_W() {
		if (y == 0)
			return false;
		else if (map[x][y] == 'X' || map[x][y] == 'Z')
			return false;
		else if (map[x][y - 1] == 'B')
			return false;
		else {
			changPreviousCell();
			y--;
			changeCell();
			return true;
		}
	}
	// ...

	// -----------------------------

	// GOAL TESt: THIS WILL
	// TELL WHETHER THE TREASURE WAS FOUND.
	public boolean foundTreasure() {
		if (map[x][y] == 'U' || map[x][y] == 'Z')
			return true;
		else
			return false;
	}

	public boolean fallInHole() {
		if (map[x][y] == 'X' || map[x][y] == 'Z')
			return true;
		else
			return false;
	}
	// -----------------------------

	// DISPLAY THE STATE
	public void display() {
		System.out.println("===========");
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}

	public int getN() {
		return N;
	}

	public int getM() {
		return M;
	}

	// THIS METHOD WILL DO the GIVEN COMMAND
	// AND WILL RETURN THE LOG MESSAGE
	public String doCommandAndLog(String cmd) {
		String log = "ERROR";
		switch (cmd) {
		case "move-N":
			if (move_N())
				log = "DONE";
			else
				log = "FAIL";
			break;
		case "move-S":
			if (move_S())
				log = "DONE";
			else
				log = "FAIL";
			break;
		case "move-E":
			if (move_E())
				log = "DONE";
			else
				log = "FAIL";
			break;
		case "move-W":
			if (move_W())
				log = "DONE";
			else
				log = "FAIL";
			break;
		}
		return log;
	}

	// THIS METHOD WILL WRITE THE GIVEN LOGS INTO A FILE
	public void writeLogs(String logsFilename, String logs) {
		try {
			FileWriter b = new FileWriter(new File(logsFilename), true);
			b.write(logs + "\n");
			b.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// -----------------------------

	// THIS METHOD WILL RETURN THE SUCCESSOR STATES
	// OF COURSE, YOU CAN & SHOULD CHANGE IT
	// public State[] successors() {
	// State children[] = new State[2]; // we have 2 actions
	//
	// // action 1
	//
	// children[0] = new State(this);
	// if (!children[0].action1())
	// children[0] = null;
	//
	// // action 2
	//
	// children[1] = new State(this);
	// if (!children[1].action1())
	// children[1] = null;
	//
	// return children;
	// }

	// -----------------------------

	// ADD EXTRAS HERE ...

}
