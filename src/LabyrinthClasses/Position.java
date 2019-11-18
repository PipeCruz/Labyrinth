package LabyrinthClasses;


public class Position {
	//instance vars
	private final int _row;
	private final int _col;

	//getters
	public int getRow() {
		return _row;
	}

	public int getCol() {
		return _col;
	}

	public Position(int r, int c) {
		_row = r;
		_col = c;
	}

	@Override
	public String toString() {
		return "Position Row: " + _row + " Position Column: " + _col;
	}

	public boolean equals(Position other) {
		return other.getRow() ==_row && other.getCol()==_col;
	}
}
