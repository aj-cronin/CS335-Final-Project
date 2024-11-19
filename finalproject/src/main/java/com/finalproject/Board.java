package finalproject;

import java.util.ArrayList;

public class Board {
	
	private ArrayList<ArrayList<Tile>> board;
	private boardX;
	private boardY;
	
	public Board() {
		this.boardX = 4;
		this.boardY = 4;
		// inicialize board to a certain size
		this.board = new ArrayList<ArrayList<Tile>>(this.boardY);
		for (int ii = 0; ii < this.boardY; ii++){
			this.board.add(new ArrayList<Tile>(this.boardX));
		}
	}
	
	public ArrayList<ArrayList<Tile>> getBoard() {
		// make a copy
		ArrayList<Arraylist<Tile>> temp = new ArrayList<Arraylist<Tile>>(this.boardY);
		for (int ii = 0; ii < boardY; ii++){
			temp.get(ii) = new ArrayList<Tile>(this.board.get(ii));
		}
		return this.board;
	}
	
	public void move(Direction direction) {
		switch (direction) {
		case LEFT:
			this.moveLeft();
			break;
		case RIGHT:
			this.moveLeft();
			break;
		case UP:
			this.moveUp();
			break;
		case DOWN:
			this.moveDown();
			break;
		}
	}
	
	private void moveLeft() {
		for (int ii = 0; ii < this.boardY; ii++){
			// whether or no the tile before this was merged(to prevent excess merging)
			boolean combined = false;
			for (int jj = 1; jj < this.boardX; jj++){
				// empty space
				if (this.board.get(ii).get(jj) == null){
					continue;
				}
				// space with a tile
				// find the tile to the left that has something
				int currentIndex = jj-1;
				Tile left = this.board.get(ii).get(currentIndex)
				while (left == null && currentIndex > 0){	
					currentIndex--;
					if (currentIndex <= 0){
						left = this.board.get(ii).get(currentIndex);
					}
				}

				if (left != null){
					// check if they can combine and left was not combined this round
					if (left.equals(this.board.get(ii).get(jj)) && !combined){
						// combine the two tiles
						left.combine(this.board.get(ii).get(jj));
						combined = true;
					// dont merge tiles, just put the current next to the next tile to the left
					} else {
						this.board.get(ii).set(currentIndex, this.board.get(ii).get(jj));
						this.board.get(ii).set(jj, null);
						combined = false;
					}
				} else {
					// dont merge tiles, just put the current next to the next tile to the left or the wall
					this.board.get(ii).set(currentIndex, this.board.get(ii).get(jj));
					this.board.get(ii).set(jj, null);
					combined = false;
				}
				
			}
		}
	}
	
	private void moveRight() {
		for (int ii = 0; ii < this.boardY; ii++){
			// whether or no the tile before this was merged(to prevent excess merging)
			boolean combined = false;
			for (int jj = this.boardX - 2; jj > 0; jj--){
				// empty space
				if (this.board.get(ii).get(jj) == null){
					continue;
				}
				// space with a tile
				// find the tile to the right that has something
				int currentIndex = jj+1;
				Tile right = this.board.get(ii).get(currentIndex)
				while (right == null && currentIndex < boardX - 1){	
					currentIndex++;
					if (currentIndex <= boardX - 1){
						right = this.board.get(ii).get(currentIndex);
					}
				}

				if (right != null){
					// check if they can combine and right was not combined this round
					if (right.equals(this.board.get(ii).get(jj)) && !combined){
						// combine the two tiles
						right.combine(this.board.get(ii).get(jj));
						combined = true;
					// dont merge tiles, just put the current next to the next tile to the right
					} else {
						this.board.get(ii).set(currentIndex, this.board.get(ii).get(jj));
						this.board.get(ii).set(jj, null);
						combined = false;
					}
				} else {
					// dont merge tiles, just put the current next to the next tile to the right or the wall
					this.board.get(ii).set(currentIndex, this.board.get(ii).get(jj));
					this.board.get(ii).set(jj, null);
					combined = false;
				}
				
			}
		}
	}
	
	private void moveUp() {
		for (int ii = 0; ii < this.boardX; ii++){
			// whether or no the tile before this was merged(to prevent excess merging)
			boolean combined = false;
			for (int jj = 1; jj < this.boardY; jj++){
				// empty space
				if (this.board.get(jj).get(ii) == null){
					continue;
				}
				// space with a tile
				// find the tile to the right that has something
				int currentIndex = jj-1;
				Tile up = this.board.get(currentIndex).get(ii)
				while (up == null && currentIndex > 0){	
					currentIndex--;
					if (currentIndex >= 0){
						up = this.board.get(currentIndex).get(ii);
					}
				}

				if (up != null){
					// check if they can combine and right was not combined this round
					if (up.equals(this.board.get(jj).get(ii)) && !combined){
						// combine the two tiles
						up.combine(this.board.get(jj).get(ii));
						combined = true;
					// dont merge tiles, just put the current next to the next tile to the right
					} else {
						this.board.get(currentIndex).set(ii, this.board.get(jj).get(ii));
						this.board.get(jj).set(ii, null);
						combined = false;
					}
				} else {
					// dont merge tiles, just put the current next to the next tile to the right or the wall
					this.board.get(currentIndex).set(ii, this.board.get(jj).get(ii));
					this.board.get(jj).set(ii, null);
					combined = false;
				}
				
			}
		}
	}
	
	private void moveDown() {
		for (int ii = 0; ii < this.boardX; ii++){
			// whether or no the tile before this was merged(to prevent excess merging)
			boolean combined = false;
			for (int jj = this.boardY - 2; jj >= 0; jj--){
				// empty space
				if (this.board.get(jj).get(ii) == null){
					continue;
				}
				// space with a tile
				// find the tile to the right that has something
				int currentIndex = jj+1;
				Tile down = this.board.get(currentIndex).get(ii)
				while (down == null && currentIndex < this.boardY-1){	
					currentIndex++;
					if (currentIndex < this.boardY){
						down = this.board.get(currentIndex).get(ii);
					}
				}

				if (down != null){
					// check if they can combine and right was not combined this round
					if (down.equals(this.board.get(jj).get(ii)) && !combined){
						// combine the two tiles
						down.combine(this.board.get(jj).get(ii));
						combined = true;
					// dont merge tiles, just put the current next to the next tile to the right
					} else {
						this.board.get(currentIndex).set(ii, this.board.get(jj).get(ii));
						this.board.get(jj).set(ii, null);
						combined = false;
					}
				} else {
					// dont merge tiles, just put the current next to the next tile to the right or the wall
					this.board.get(currentIndex).set(ii, this.board.get(jj).get(ii));
					this.board.get(jj).set(ii, null);
					combined = false;
				}
				
			}
		}
	}

	public String toString(){
		String out = "┌" + "----".repeat(this.boardX - 1) + "---┐";
		for (int ii = 0; ii < this.boardY; ii++){
			out += "\n|";
			for (int jj = 0; jj < this.boardX; jj++){
				out += " " + this.board.get(ii).get(jj).getValue() + " |";
			}
			if (ii != this.boardY - 1){
    			out += "\n|" + "---+".repeat(this.boardX - 1) + "---|";
			}
		}
		out += "\n└" + "----".repeat(this.boardX - 1) + "---┘";
		return out;
	}
	
}
