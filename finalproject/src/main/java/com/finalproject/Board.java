/**
 * @authors Aidan Tucker, AJ Cronin, Brooke Stetson, Nathan Osborne
 * @file Board.java
 * @purpose Emulates the board used within the 2048 game with a board of size four
 * by four. Represents the board in a 2-d ArrayList with the rows being the nested
 * lists. Has the functionality of moving right, left, up, and down using an Enum
 * to represent the movements as well as stores the score as movements are made as
 * it cannot be calculated from the board alone. This class also stores the current
 * "theme" of the game to change the colors and design of the UI.
 */

package com.finalproject;

import java.util.ArrayList;
import java.util.Random;

/**
 * Represents the a game board of size four by four with tiles that populate the
 * board with movements available of left, right, up, and down. Has a "theme" feature
 * that allows for design changes.
 */
public class Board {
	
	// the 2-d board used for the game
	private ArrayList<ArrayList<Tile>> board;
	// the height of the board to allow for future implementaiton of a dynamically sized board
	private int boardX;
	private int boardY;
	// stores the score since it cannot be calculated soley with the information stored
	private int score;
	private int tileNum;
	private int highest;
	// adding the ability to change the color and design of the board
	private Theme theme;
	// For setting the seed for testing purposes.
	private Random random;
	
	/**
	 * the constructor, sets up the neccessary variables used throughout the class
	 */
	public Board() {
		this.tileNum = 0;
		this.score = 0;
		this.boardX = 4;
		this.boardY = 4;
		// set highest to 4 as default because we only care if the highest value reaches 2048
		this.highest = 4;
		// sets the default "theme" (the color of the tiles) to BASIC
		this.theme = new Theme("light,d8c644,ff7575,FAF8F0,756452,FFFFFF");
		// creating the intiial 2-d board
		this.board = new ArrayList<ArrayList<Tile>>();
		for(int ii = 0; ii < 4; ii++) {
			ArrayList<Tile> row = new ArrayList<Tile>();
			for(int jj = 0; jj < 4; jj++) {
				row.add(null);
			}
			board.add(row);
		}
		random = new Random();
	}
	
	/**
	 * Retrieves the game board
	 * @return ArrayList<ArrayList<Tile>> that represents the game board in a 2-d structure 
	 */
	public ArrayList<ArrayList<Tile>> getBoard() {
		// make a copy to avoid an escaping reference
		return new ArrayList<ArrayList<Tile>>(this.board);
	}
	
	/**
	 * Creates a move for the pieces on the game board in the indicated direction
     * @param direction - an enum DIRECTION to indicate how to move
     */
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
	
	/**
	 * Moves the game board tiles left where possible, combining tiles if applicable
	 * @return boolean - true if a move was able to be made and false if a move was
	 * unable to be made due to a lack of combinations able to be made or a lack of tiles
	 */
	private boolean moveLeft() {
		boolean moved = false;
		for (int ii = 0; ii < this.boardY; ii++){
			// whether or not the tile before this was merged(to prevent excess merging)
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
						score += (2*left.getValue());
						if (2*left.getValue() > this.highest){
							this.highest = 2*left.getValue();
						}
						combined = true;
						this.tileNum--;
						moved = true;
					// dont move the tile
					} else if (currentIndex + 1 == jj){
						combined = false;
					// dont merge tiles, just put the current next to the next tile to the left
					} else {
						this.board.get(ii).set(currentIndex + 1, this.board.get(ii).get(jj));
						this.board.get(ii).set(jj, null);
						combined = false;
						moved = true;
					}
				} else {
					// dont merge tiles, just put the current next to the next tile to the left or the wall
					this.board.get(ii).set(currentIndex, this.board.get(ii).get(jj));
					this.board.get(ii).set(jj, null);
					combined = false;
					if (currentIndex != jj) {
						moved = true;
					}
				}
				
			}
		}
		// if tiles were moved, add a new tile ot the game board
		if (moved) {
			this.addTile();
		}
		return moved;
	}
	
	/**
	 * Moves the game board tiles right if possible, combines tiles if applicable
	 * @return boolean- true if a move was able to be made and false if a move was not able
	 * to be made due to a lack of combinations able to be made or a lack of tiles
	 */
	private boolean moveRight() {
		boolean moved = false;
		for (int ii = 0; ii < this.boardY; ii++){
			// whether or not the tile before this was merged(to prevent excess merging)
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
						score += (2*right.getValue());
						if (2*right.getValue() > this.highest){
							this.highest = 2*right.getValue();
						}
						combined = true;
						this.tileNum--;
						moved = true;
					// dont move the tile
					} else if (currentIndex - 1 == jj){
						combined = false;
					// dont merge tiles, just put the current next to the next tile to the right
					} else {
						this.board.get(ii).set(currentIndex - 1, this.board.get(ii).get(jj));
						this.board.get(ii).set(jj, null);
						combined = false;
						moved = true;
					}
				} else {
					// dont merge tiles, just put the current next to the next tile to the right or the wall
					this.board.get(ii).set(currentIndex, this.board.get(ii).get(jj));
					this.board.get(ii).set(jj, null);
					combined = false;
					if (currentIndex != jj) {
						moved = true;
					}
				}
				
			}
		}
		// if a move was made, add a new tile to the game board
		if (moved) {
			this.addTile();
		}
		return moved;
	}
	
	/**
	 * Moves the game board tiles up where possible, combining tiles if applicable
	 * @return boolean - true if a move was able to be made and false if a move was
	 * unable to be made due to a lack of combinations able to be made or a lack of tiles
	 */
	private boolean moveUp() {
		boolean moved = false;
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
						score += (2*up.getValue());
						if (2*up.getValue() > this.highest){
							this.highest = 2*up.getValue();
						}
						combined = true;
						this.tileNum--;
						moved = true;
					// dont move the tile
					} else if (currentIndex + 1 == jj) {
						combined = false;
					// dont merge tiles, just put the current next to the next tile to the right
					} else {
						this.board.get(currentIndex + 1).set(ii, this.board.get(jj).get(ii));
						this.board.get(jj).set(ii, null);
						combined = false;
						moved = true;
					}
				} else {
					// dont merge tiles, just put the current next to the next tile to the right or the wall
					this.board.get(currentIndex).set(ii, this.board.get(jj).get(ii));
					this.board.get(jj).set(ii, null);
					combined = false;
					if (currentIndex != jj) {
						moved = true;
					}
				}
				
			}
		}
		// if a move was made, add a new tile to the game board
		if (moved) {
			this.addTile();
		}
		return moved;
	}
	
	/**
	 * Moves the game board tiles down where possible, combining tiles if applicable
	 * @return boolean - true if a move was able to be made and false if a move was
	 * unable to be made due to a lack of combinations able to be made or a lack of tiles
	 */
	private boolean moveDown() {
		boolean moved = false;
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
						score += (2*down.getValue());
						if (2*down.getValue() > this.highest){
							this.highest = 2*down.getValue();
						}
						combined = true;
						this.tileNum--;
						moved = true;
					// dont move the tile
					} else if (currentIndex - 1 == jj) {
						combined = false;
					// dont merge tiles, just put the current next to the next tile to the right
					} else {
						this.board.get(currentIndex - 1).set(ii, this.board.get(jj).get(ii));
						this.board.get(jj).set(ii, null);
						combined = false;
						moved = true;
					}
				} else {
					// dont merge tiles, just put the current next to the next tile to the right or the wall
					this.board.get(currentIndex).set(ii, this.board.get(jj).get(ii));
					this.board.get(jj).set(ii, null);
					combined = false;
					if (currentIndex != jj) {
						moved = true;
					}
				}
				
			}
		}
		// if a move was made, add a new tile to the game board
		if (moved) {
			this.addTile();
		}
		return moved;
	}

	/**
	 * Adds a tile (2 or 4) to the game board at a random location, has a lower probability of
	 * adding a 4 tile as opposed to a 2 tile
	 */
	public void addTile(){
		int x = (int) Math.floor(random.nextDouble() * (this.boardX));
		int y = (int) Math.floor(random.nextDouble() * (this.boardY));

		// finding an empty space on the game baord
		while(this.board.get(y).get(x) != null){
			x = (int) Math.floor(random.nextDouble() * (this.boardX));
			y = (int) Math.floor(random.nextDouble() * (this.boardY));
		}

		// determines whether a 2 tile or a 4 tile is added, lower chance of adding a 4 tile
		double chanceOf4 = random.nextDouble();
		if (chanceOf4 >= 0.9){
			this.board.get(y).set(x, new Tile(4, theme));
		} else {
			this.board.get(y).set(x, new Tile(2, theme));
		}

		this.tileNum++;

	}

	/**
	 * Retrieves the game score
	 * @return int representing the game score
	 */
	public int getScore(){
		return this.score;
	}

	/**
	 * Retrieves the highest tile
	 * @return int representing the value of the highest tile
	 */
	public int getHighest(){
		return this.highest;
	}

	/**
	 * Retrieves whether the board is full
	 * @return boolean indicating if the baord is full - true if full false if not
	 */
	public boolean isFull(){
		return this.tileNum == 16;
	}

	/**
	 * Retrieves the current theme of the board
	 * @return Theme - the theme of the board
	 */
	public Theme getTheme(){
		return this.theme;
	}

	/**
	 * Sets the theme of the board to the new Theme object
     * @param newTheme - a Theme object indicating how the color and design is
	 * intended to look
     */
	public void setTheme(Theme newTheme) {
		theme = newTheme;
		for(ArrayList<Tile> row: board) {
			for(Tile tile: row) {
				// changes the theme of the tile for coloring purposes, all tiles currently
				// populating the board should have the same theme
				if(tile != null) tile.setTheme(newTheme);
			}
		}
	}

	/**
	 * Creates a string representation of the game board
	 * @return String representing the game board
	 */
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
	
	public void setRandomSeed(long seed) {
		random.setSeed(seed);
	}
}
