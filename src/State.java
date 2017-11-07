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

	private int x; // index R at row
	private int y; // index R at column
	private char[][] map; // THE MAP
	private int N;
	private int M;

	enum Status {
		empty, 
		hole,
	}

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
			int sizeArray = x * y;
			int k = 0;
			for (int i = 0; i < x; i++) {
				for (int j = 0; j < y; j++) {
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
		if(Status.empty )
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

	//this method return void, it can return map after change it
	public void move_N(){
		if(x != 0){
			if(map[x][y] != 'H'){
				x--;
				write on log file {DONE}
			}
			else
				write on log file {HOLE}
		}
		else
			write on log file {FAIL}
	}
	//this method return void, it can return map after change it
	public void move_S(){
		if(x != N)
			if(map[x][y] != 'H'){
				x++;
				write on log file {DONE}
			}
			else
				write on log file {HOLE}
		else
			write on log file {FAIL}
	}
	//this method return void, it can return map after change it
	public void move_E(){
		if(y != M){
			if(map[x][y] != 'H'){
				y++;
				write on log file {DONE}
			}else{
				write on log file {HOLE}
			}
		}else{
			write on log file {FAIL}
		}
	}
	//this method return void, it can return map after change it
	public void move_W(){
		if(y != 0){
			if(map[x][y] != 'H'){
				y--;
				write on log file {DONE}
			}else{
				write on log file {HOLE}
			}
		}else
			write on log file {FAIL}
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
		// ...
	}

	// THIS METHOD WILL DO the GIVEN COMMAND
	// AND WILL RETURN THE LOG MESSAGE
	public String doCommandAndLog(String cmd) {
		String log = "ERROR";
		switch (cmd) {
		 case "move-N" : move_N(); break;
		 case "move-S" : move_S(); break;
		 case "move-E" : move_E(); break;
		 case "move-W" : move_W(); break;
		}
		return log;
	}

	// THIS METHOD WILL WRITE THE GIVEN LOGS INTO A FILE
	public void writeLogs(String logsFilename, String logs) throws FileNotFoundException {
		File f = new File(logsFilename);
		FileInputStream fs = new FileInputStream(f);
		BufferedInputStream b = new BufferedInputStream(fs);
		
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
