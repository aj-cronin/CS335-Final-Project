package finalproject;

import java.util.ArrayList;

public class Board {
	
	private ArrayList<ArrayList<Integer>> board;
	
	public Board() {
		board = new ArrayList<ArrayList<Integer>>();
	}
	
	public ArrayList<ArrayList<Integer>> getBoard() {
		return board;
	}
	
	public void move(Direction direction) {
		switch (direction) {
		case LEFT:
			moveLeft();
			break;
		case RIGHT:
			moveLeft();
			break;
		case UP:
			moveUp();
			break;
		case DOWN:
			moveDown();
			break;
		}
	}
	
	private void moveLeft() {
		// TODO
	}
	
	private void moveRight() {
		// TODO
	}
	
	private void moveUp() {
		// TODO
	}
	
	private void moveDown() {
		// TODO
	}
	
}
