package LabyrinthClasses;

public class Maze {

	private boolean[][] maze;

	public boolean[][] getMaze() { return maze; }

	public Maze() {//true is a wall
		maze = new boolean[][]{
				{true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true},
				{true,  false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true},
				{true,  false, true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  false, true},
				{true,  false, true,  false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true,  false, true},
				{true,  false, true,  false, true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  false, true,  false, true},
				{true,  false, true,  false, true,  false, false, false, false, false, false, false, false, false, false, false, false, false, true,  false, true,  false, true},
				{true,  false, true,  false, true,  false, true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  false, true,  false, true,  false, true},
				{true,  false, true,  false, true,  false, true,  false, false, false, false, false, false, false, false, false, true,  false, true,  false, true,  false, true},
				{true,  false, true,  false, true,  false, true,  false, true,  true,  true,  true,  true,  true,  true,  false, true,  false, true,  false, true,  false, true},
				{true,  false, true,  false, true,  false, true,  false, true,  false, false, false, false, false, true,  false, true,  false, true,  false, true,  false, true},
				{true,  false, true,  false, true,  false, true,  false, true,  false, true,  true,  true,  false, true,  false, true,  false, true,  false, true,  false, true},
				{true,  false, true,  false, true,  false, true,  false, true,  false, true,  false, true,  false, true,  false, true,  false, true,  false, true,  false, true},
				{true,  false, true,  false, true,  false, true,  false, true,  false, true,  false, true,  false, true,  false, true,  false, true,  false, true,  false, true},
				{true,  false, true,  false, true,  false, true,  false, true,  false, true,  false, true,  false, true,  false, true,  false, true,  false, true,  false, true},
				{true,  false, true,  false, true,  false, true,  false, true,  false, true,  false, true,  false, true,  false, true,  false, true,  false, true,  false, true},
				{true,  false, true,  false, true,  false, true,  false, true,  false, true,  false, true,  false, false, false, true,  false, true,  false, true,  false, true},
				{true,  false, true,  false, true,  false, true,  false, true,  false, true,  false, true,  true,  true,  true,  true,  false, true,  false, true,  false, true},
				{true,  false, true,  false, true,  false, false, false, true,  false, true,  false, true,  false, false, false, false, false, true,  false, true,  false, true},
				{true,  false, true,  false, true,  true,  true,  true,  true,  false, true,  false, true,  false, true,  true,  true,  true,  true,  false, true,  false, true},
				{true,  false, true,  false, false, false, false, true,  true,  false, true,  false, true,  false, false, false, false, false, false, false, true,  false, true},
				{true,  false, true,  true,  true,  true,  false, true,  false, false, true,  false, true,  true,  true,  true,  true,  true,  true,  true,  true,  false, true},
				{true,  false, false, false, false, false, false, true,  false, true,  true,  false, false, false, false, false, false, false, false, false, false, false, true},
				{true,  true,  true,  true,  true,  true,  true,  true,  false, true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true}
		};
	}
}
