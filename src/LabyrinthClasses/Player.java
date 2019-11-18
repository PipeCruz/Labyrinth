package LabyrinthClasses;

public class Player {
	//player class stores position of the player
	private Position _curPos;

	public Position getPosition() {
		return _curPos;
	}

	public void setPosition(Position p) {
		_curPos = p;
	}

	public Player() {
		_curPos = new Position(11, 11);
	}

	//moves the player, checks if the move is valid first
	public void move(Direction d, Maze z) {
		if (d == Direction.North && (_curPos.getRow() - 1 >= 0 && !z.getMaze()[_curPos.getRow() - 1][_curPos.getCol()])) {
			_curPos = new Position(_curPos.getRow() - 1, _curPos.getCol());
		} else if (d == Direction.South && (_curPos.getRow() + 1 < z.getMaze().length && !z.getMaze()[_curPos.getRow() + 1][_curPos.getCol()])) {
			_curPos = new Position(_curPos.getRow() + 1, _curPos.getCol());

		} else if (d == Direction.East && (_curPos.getCol() + 1 < z.getMaze()[_curPos.getRow()].length
				&& !z.getMaze()[_curPos.getRow()][_curPos.getCol() + 1])) {
			_curPos = new Position(_curPos.getRow(), _curPos.getCol() + 1);
		} else if (_curPos.getCol() - 1 >= 0 && !z.getMaze()[_curPos.getRow()][_curPos.getCol() - 1]) {
			_curPos = new Position(_curPos.getRow(), _curPos.getCol() - 1);
		}

	}

}