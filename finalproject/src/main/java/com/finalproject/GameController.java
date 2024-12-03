package com.finalproject;

import java.util.ArrayList;

public class GameController {
    
    private Board board;

    public GameController(){
        this.board = new Board();
    }

    public void start(){
        this.board.addTile();
        this.board.addTile();
        
    }

    public void move(Enums.DIRECTION direction){
        this.board.move(direction);
    }

    public int getScore(){
        return this.board.getScore();
    }

    public int getHighest(){
        return this.board.getHighest();
    }

    public String getBoard(){
        return this.board.toString();
    }

    public ArrayList<ArrayList<Tile>> getBoardList(){
        return board.getBoard();
    }

    public void setBoardTheme(Theme newTheme){
        board.setTheme(newTheme);
    }

    public boolean isOver(){
        return (this.board.isFull() && !tilesCanMove()) || getHighest() == 2048;
    }

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

    

}
