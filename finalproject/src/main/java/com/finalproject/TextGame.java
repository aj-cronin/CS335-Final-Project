package com.finalproject;

import java.util.Scanner;

public class TextGame {
    public static void main(String[] args) {
        GameController game = new GameController();
        game.start();
        Scanner input = new Scanner(System.in);
        while (!game.isOver()) { 
            System.out.println(game.getBoard());
            System.out.println("Direction: ");
            String direction = input.nextLine();
            if (direction.equals("l")){
                game.move(Enums.DIRECTION.LEFT);
            } else if (direction.equals("r")){
                game.move(Enums.DIRECTION.RIGHT);
            } else if (direction.equals("u")){
                game.move(Enums.DIRECTION.UP);
            } else if (direction.equals("d")){
                game.move(Enums.DIRECTION.DOWN);
            } else {
                System.out.println("Not a valid direction");
            }
        }
        input.close();
    }    
}
