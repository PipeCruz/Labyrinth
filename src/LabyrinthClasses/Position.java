package LabyrinthClasses;


public class Position {
	private int _row, _col;
	
	int getRow() { return _row; }
	int getCol() { return _col; }
	
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
