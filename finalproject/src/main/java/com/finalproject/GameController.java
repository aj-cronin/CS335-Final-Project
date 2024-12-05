/**
 * @authors Aidan Tucker, AJ Cronin, Brooke Stetson, Nathan Osborne
 * @file GameController.java
 * @purpose 
 */
package com.finalproject;

import java.util.ArrayList;

/**
 * The controller for the 2048 game 
 */
public class GameController {
    
    private Board board;

    /**
     * Constructor intializing the neccessary variables used throughout the class
     */
    public GameController(){
        this.board = new Board();
    }

    /**
     * Begins the game with the standard two tiles added
     */
    public void start(){
        this.board.addTile();
        this.board.addTile();
        
    }

    /**
     * Moves the tiles in the indicated direction if possible, combining tiles if applicable
     * @param direction indicates which direction the tiles are to be moved
     */
    public void move(Enums.DIRECTION direction){
        this.board.move(direction);
    }

    /**
     * Retrieves the score of the game
     * @return int represnting the current game score
     */
    public int getScore(){
        return this.board.getScore();
    }

    /**
     * Retrieves the highest tile value on the board
     * @return int representing the current highest tile on the board
     */
    public int getHighest(){
        return this.board.getHighest();
    }

    /**
     * Retrieves the string repsentation of the game board
     * @return String representing the game board
     */
    public String getBoard(){
        return this.board.toString();
    }

    /**
     * Retrieves the 2-d list representation of the game board
     * @return ArrayList<ArrayList<Tile>> - 2-d ArrayList representing the game board
     */
    public ArrayList<ArrayList<Tile>> getBoardList(){
        return board.getBoard();
    }

    /**
     * Sets the theme of the board
     * @param newTheme a theme for the display
     */
    public void setBoardTheme(Theme newTheme){
        board.setTheme(newTheme);
    }

    /**
     * Retrieves if the game is over due to the board being full with no movements to be
     * made or if the 2048 tile is reached
     * @return boolean - true is the game is over and false if it is not
     */
    public boolean isOver(){
        return (this.board.isFull() && !tilesCanMove()) || getHighest() == 2048;
    }

    /**
     * 
     * @return 
     */
    private boolean tilesCanMove(){
        boolean canMove = false;
        for (int ii = 0; ii < this.board.getBoard().size(); ii ++){
            for (int jj = 0; jj < this.board.getBoard().get(ii).size(); jj++){
                boolean tmp = false;
                // check left
                if (jj == 0){
                    // out of bounds
                    tmp = tmp || false;
                } else {
                    tmp = tmp || checkNextTo(0, -1, ii, jj);
                }
                // check right
                if (jj == this.board.getBoard().get(ii).size() - 1){
                    // out of bounds
                    tmp = tmp || false;
                } else {    
                    tmp = tmp || checkNextTo(0, 1, ii, jj);
                }
                // check up
                if (ii == 0){
                    // out of bounds
                    tmp = tmp || false;
                } else {
                    tmp = tmp || checkNextTo(-1, 0, ii, jj);
                }
                // check down
                if (ii == this.board.getBoard().size() - 1){
                    // out of bounds
                    tmp = tmp || false;
                } else {
                    tmp = tmp || checkNextTo(1, 0, ii, jj);
                }
                canMove = canMove || tmp;
            }
        }
        return canMove;
    }

    private boolean checkNextTo(int y, int x, int ii, int jj){
        return this.board.getBoard().get(ii + y).get(jj + x).combine(this.board.getBoard().get(ii).get(jj)) != null || this.board.getBoard().get(ii + y).get(jj + x) == null;
    }

    // Returns the "theme" of the game (the range of colors chosen).
    public Theme getTheme(){
        return this.board.getTheme();
    }

    public void setRandomSeed(long seed){
        board.setRandomSeed(seed);
    }

}
