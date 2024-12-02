package com.finalproject;

import java.util.ArrayList;

public class Board {
	
	private ArrayList<ArrayList<Tile>> board;
	private int boardX;
	private int boardY;
	private int score;
	private int tileNum;
	// adding the ability to change the color theme
	private Enums.THEME theme;
	
	public Board() {
		this.tileNum = 0;
		this.score = 0;
		this.boardX = 4;
		this.boardY = 4;
		// sets the default "theme" (the color of the tiles) to BASIC
		this.theme = Enums.THEME.BASIC;
		// "inicialize" (as spelled by Nathan) board to a certain size
		this.board = new ArrayList<ArrayList<Tile>>();
		for(int ii = 0; ii < 4; ii++) {
			ArrayList<Tile> row = new ArrayList<Tile>();
			for(int jj = 0; jj < 4; jj++) {
				row.add(null);
			}
			board.add(row);
		}
	}
	
	public ArrayList<ArrayList<Tile>> getBoard() {
		// make a copy
		return new ArrayList<ArrayList<Tile>>(this.board);
	}
	
	public void move(Enums.DIRECTION direction) {
		switch (direction) {
		case LEFT:
			this.moveLeft();
			break;
		case RIGHT:
			this.moveRight();
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
				Tile left = this.board.get(ii).get(currentIndex);
				while (left == null && currentIndex > 0){	
					currentIndex--;
					if (currentIndex >= 0){
						left = this.board.get(ii).get(currentIndex);
					}
				}

				if (left != null){
					// check if they can combine and left was not combined this round
					if (left.equals(this.board.get(ii).get(jj)) && !combined){
						// combine the two tiles
						this.board.get(ii).set(currentIndex, left.combine(this.board.get(ii).get(jj)));
						this.board.get(ii).set(jj, null);
						score += left.getValue();
						combined = true;
						this.tileNum--;
					// dont move the tile
					} else if (currentIndex + 1 == jj){
						combined = false;
					// dont merge tiles, just put the current next to the next tile to the left
					} else {
						this.board.get(ii).set(currentIndex + 1, this.board.get(ii).get(jj));
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
		this.addTile();
	}
	
	private void moveRight() {
		for (int ii = 0; ii < this.boardY; ii++){
			// whether or no the tile before this was merged(to prevent excess merging)
			boolean combined = false;
			for (int jj = this.boardX - 2; jj >= 0; jj--){
				// empty space
				if (this.board.get(ii).get(jj) == null){
					continue;
				}
				// space with a tile
				// find the tile to the right that has something
				int currentIndex = jj+1;
				Tile right = this.board.get(ii).get(currentIndex);
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
						this.board.get(ii).set(currentIndex, right.combine(this.board.get(ii).get(jj)));
						this.board.get(ii).set(jj, null);
						score += right.getValue();
						combined = true;
						this.tileNum--;
					// dont move the tile
					} else if (currentIndex - 1 == jj){
						combined = false;
					// dont merge tiles, just put the current next to the next tile to the right
					} else {
						this.board.get(ii).set(currentIndex - 1, this.board.get(ii).get(jj));
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
		this.addTile();
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
				Tile up = this.board.get(currentIndex).get(ii);
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
						this.board.get(currentIndex).set(ii, up.combine(this.board.get(jj).get(ii)));
						this.board.get(jj).set(ii, null);
						score += up.getValue();
						combined = true;
						this.tileNum--;
					// dont move the tile
					} else if (currentIndex + 1 == jj) {
						combined = false;
					// dont merge tiles, just put the current next to the next tile to the right
					} else {
						this.board.get(currentIndex + 1).set(ii, this.board.get(jj).get(ii));
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
		this.addTile();
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
				Tile down = this.board.get(currentIndex).get(ii);
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
						this.board.get(currentIndex).set(ii, down.combine(this.board.get(jj).get(ii)));
						this.board.get(jj).set(ii, null);
						score += down.getValue();
						combined = true;
						this.tileNum--;
					// dont move the tile
					} else if (currentIndex - 1 == jj) {
						combined = false;
					// dont merge tiles, just put the current next to the next tile to the right
					} else {
						this.board.get(currentIndex - 1).set(ii, this.board.get(jj).get(ii));
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
		this.addTile();
	}

	public void addTile(){
		int x = (int) Math.floor(Math.random() * (this.boardX));
		int y = (int) Math.floor(Math.random() * (this.boardY));

		while(this.board.get(y).get(x) != null){
			x = (int) Math.floor(Math.random() * (this.boardX));
			y = (int) Math.floor(Math.random() * (this.boardY));
		}

		double chanceOf4 = Math.random();
		if (chanceOf4 >= 0.9){
			this.board.get(y).set(x, new Tile(4));
		} else {
			this.board.get(y).set(x, new Tile(2));
		}

		this.tileNum++;

	}

	public int getScore(){
		return this.score;
	}

	public boolean isFull(){
		return this.tileNum == 16;
	}

	// returns the "theme" of the board, default is "basic"
	public Enums.THEME getTheme(){
		return this.theme;
	}

	public String toString(){
		String out = "┌" + "----".repeat(this.boardX - 1) + "---┐";
		for (int ii = 0; ii < this.boardY; ii++){
			out += "\n|";
			for (int jj = 0; jj < this.boardX; jj++){
				if (this.board.get(ii).get(jj) != null){
					out += " " + this.board.get(ii).get(jj).getValue() + " |";
				} else {
					out += " " + " " + " |";
				}
			}
			if (ii != this.boardY - 1){
    			out += "\n|" + "---+".repeat(this.boardX - 1) + "---|";
			}
		}
		out += "\n└" + "----".repeat(this.boardX - 1) + "---┘";
		return out;
	}
	
}
