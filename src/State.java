// KING SAUD UNIVERSITY
// CCIS
// CSC 361

// NAME:  ...................
// ID: ...................

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class State {

	// THE FOLLOWING IS AN EXAMPLE OF THE ATTRIBUTES
	// THAT YOU NEED TO PUT IN A STATE.
	// YOU SHOULD CHANGE IT:

	enum Status {
		EMPTY_NOTHING,
		EMPTY_ROBOT,
		EMPTY_TREASURE,
		EMPTY_TREASURE_ROBOT,
		HOLE_NOTHING,
		HOLE_ROBOT,
		HOLE_TREASURE,
		HOLE_ROBOT_TREASURE
	}

	private int x; // index R at row
	private int y; // index R at column
	private char[][] map; // THE MAP
	private int N;
	private int M;
	public Status status;

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
		status = Status.EMPTY_ROBOT;
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
	public boolean action1() {
		if (x = x) // (...)
			// do something
			return false;
		return true;
	}

	// ACTION 2
	public boolean action2() {
		// ...
	}

	// YOU CAN ADD MORE ACTIONS HERE.

	public void changeStatus(Status status){
		switch(status){
		case EMPTY_NOTHING: 
			map[x][y] = ' ';
			break;
		case EMPTY_ROBOT: 
			map[x][y] = 'R';
			break;
		case EMPTY_TREASURE:
			map[x][y] = 'T';
			break;
		case EMPTY_TREASURE_ROBOT:
			map[x][y] = 'U';
			break;
		case HOLE_NOTHING:
			map[x][y] = 'H';
			break;
		case HOLE_ROBOT:
			map[x][y] = 'X';
			break;
		case HOLE_TREASURE:
			map[x][y] = 'Y';
			break;
		case HOLE_ROBOT_TREASURE:
			map[x][y] = 'Z';
			break;
			default:
				System.out.println("Error on change Status");
		}
	}
	public char currentCell(){
		return map[x][y];
	}
	// this method return void, it can return boolean
	public boolean move_N() {
		if (x != 0) {
			if (map[x][y] != 'H') {
				changeStatus(Status.EMPTY_NOTHING);
				x--;
				changeStatus(Status.EMPTY_ROBOT);
				return true;
			} else {
				changeStatus(Status.EMPTY_ROBOT);
				return false;
			}
		} else
			return false;
	}

	// this method return void, it can return boolean
	public boolean move_S() {
		if (x != N) {
			if (map[x][y] != 'H') {
				x++;
				status = Status.EMPTY;
				return true;
			} else {
				status = Status.HOLE;
				return false;
			}
		} else
			return false;
	}

	// this method return void, it can return boolean
	public boolean move_E() {
		if (y != M) {
			if (map[x][y] != 'H') {
				y++;
				status = Status.EMPTY;
				return true;
			} else {
				status = Status.HOLE;
				return false;
			}
		} else
			return false;
	}

	// this method return void, it can return boolean
	public boolean move_W() {
		if (y != 0) {
			if (map[x][y] != 'H') {
				y--;
				status = Status.EMPTY;
				return true;
			} else {
				status = Status.HOLE;
				return false;
			}
		} else
			return false;
	}
	// ...

	// -----------------------------

	// GOAL TESt: THIS WILL
	// TELL WHETHER THE TREASURE WAS FOUND.
	public boolean foundTreasure() {
		// ...
	}

	// -----------------------------

	// DISPLAY THE STATE
	public void display() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}

	// THIS METHOD WILL DO the GIVEN COMMAND
	// AND WILL RETURN THE LOG MESSAGE
	public String doCommandAndLog(String cmd) {
		String log = "ERROR";
		switch (cmd) {
		case "move-N":
			if (move_N()) {
				log = "DONE";
			} else {
				if (status == Status.EMPTY)
					log = "FAIL";
				else
					log = "HOLE";
			}
			break;
		case "move-S":
			if (move_S()) {
				log = "DONE";
			} else {
				if (status == Status.EMPTY)
					log = "FAIL";
				else
					log = "HOLE";
			}
			break;
		case "move-E":
			if (move_E()) {
				log = "DONE";
			} else {
				if (status == Status.EMPTY)
					log = "FAIL";
				else
					log = "HOLE";
			}
			break;
		case "move-W":
			if (move_W()) {
				log = "DONE";
			} else {
				if (status == Status.EMPTY)
					log = "FAIL";
				else
					log = "HOLE";
			}
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
	public State[] successors() {
		State children[] = new State[2]; // we have 2 actions

		// action 1

		children[0] = new State(this);
		if (!children[0].action1())
			children[0] = null;

		// action 2

		children[1] = new State(this);
		if (!children[1].action1())
			children[1] = null;

		return children;
	}

	// -----------------------------

	// ADD EXTRAS HERE ...

}
