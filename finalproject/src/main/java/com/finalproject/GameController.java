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
        return this.board.isFull() || (this.getScore() == 2048);
    }

    // Returns the "theme" of the game (the range of colors chosen).
    public Theme getTheme(){
        return this.board.getTheme();
    }

    

}
