package com.finalproject;

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

    public String getBoard(){
        return this.board.toString();
    }

    public boolean isOver(){
        return this.board.isFull() || (this.getScore() == 2048);
    }

    // Returns the "theme" of the game (the range of colors chosen).
    public Enums.THEME getTheme(){
        return this.board.getTheme();
    }

    

}
