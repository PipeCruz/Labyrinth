package LabyrinthClasses;

public class Maze {
	//the maze, as represented by booleans
	private final boolean[][] maze;

	public boolean[][] getMaze() {
		return maze;
	}

	public Maze() {//true is a wall
		maze = new boolean[][]{
				{true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true},
				{true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true},
				{true, false, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false, true},
				{true, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, true},
				{true, false, true, false, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false, true, false, true},
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
