package LabyrinthClasses;

public class Player {

	private Maze _z = new Maze();
	private Position[] posArr = {new Position(11,11), new Position(2,2), new Position(13,1), new Position(0,8), new Position(5,0)};
	private Position _curPos = posArr[(int) (Math.random()*1)];
	
	private Position[] endArr = {new Position(13,0), new Position(2,0), new Position(13,25), new Position(13,16)};
	//{new Position(13,0), new Position(2,0), new Position(13,25), new Position(13,19)};
	private Position randEnd = endArr[(int)(Math.random()*4)];
	
	
	private boolean _isAlive = true;

	public Position getPosition() {
		return _curPos;
	}

	public boolean isAlive() {
		return _isAlive;
	}

	public void setPosition(Position p) {
		_curPos = p;
	}

	public void killPlayer() {
		_isAlive = false;
	}

	public Position getEnd() {
		return randEnd;
	}

	public Player(Maze z) {
		_curPos = new Position(_curPos.getRow(),_curPos.getCol());
	}

	public void move(Direction d, Maze z) {
		if (d == Direction.North) {
			if (_curPos.getRow() - 1 >= 0 && !z.getMaze()[_curPos.getRow() - 1][_curPos.getCol()] ) {
				_curPos = new Position(_curPos.getRow() - 1, _curPos.getCol());
			}
		} else if (d == Direction.South) {
			if (_curPos.getRow() + 1 < z.getMaze().length && !z.getMaze()[_curPos.getRow() + 1][_curPos.getCol()]) {
				_curPos = new Position(_curPos.getRow() + 1, _curPos.getCol());
			}
		} else if (d == Direction.East) {
			if (_curPos.getCol() + 1 < z.getMaze()[_curPos.getRow()].length
					&& !z.getMaze()[_curPos.getRow()][_curPos.getCol() + 1]) {
				_curPos = new Position(_curPos.getRow(), _curPos.getCol() + 1);
			}
		} else {
			if (_curPos.getCol() - 1 >= 0 && !z.getMaze()[_curPos.getRow()][_curPos.getCol() - 1]) {
				_curPos = new Position(_curPos.getRow(), _curPos.getCol() - 1);
			}
		}

	}
	
	
}