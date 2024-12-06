/**
 * @athors Aidan Tucker, AJ Cronin, Brooke Stetson, Nathan Osborne
 * @file Tile.java
 * @purpose Emulates the 2048 tiles that move around the board and combine
 * with each other. These tiles store their value and their theme which 
 * also calcuates the color of the tile.
 */

package com.finalproject;

import javafx.scene.paint.Color;

 /**
 * Represents the a game tile and the value of it(number that is visable)
 */
public class Tile{
    private int value;
    private Theme theme;

    /**
    * constructor for the tile that sets the theme to the default
    * @pre value must be a power of 2 and > 0
    * @param val(int) the value of the tile
    */
    public Tile(int val){
        this.value = val;
        theme = new Theme("light,d8c644,ff7575,FAF8F0,756452,FFFFFF");
    }

    /**
    * constructor for the tile that sets the theme to the given theme
    * @pre value must be a power of 2 and > 0
    * @pre theme is not null
    * @param val(int) the value of the tile
    * @param theme(Theme) the theme of the tile
    */
    public Tile(int val, Theme theme){
        this.value = val;
        this.theme = theme;
    }
    
    /**
    * gets the associated color to the Tile based on its value
    * @return color of the tile
    */
    public Color getColor(){
        // calculates what power of 2 value is
        double power = (Math.log(this.value))/(Math.log(2));
        // finds the associated COLOR enum
        return theme.getColor((int)power - 1);
    }

    /**
    * gets the associated color to the Tile based on its value
    * @return color of the tile
    */
    public int getValue(){
        return this.value;
    }

    /**
    * sets the theme of the tile
    * @pre newTheme is not null
    * @param newTheme(Theme) is the new theme of the tile
    */
    public void setTheme(Theme newTheme){
        theme = newTheme;
    }

    /**
    * combines two Tiles into one if they are equal, returns this new Tile
    * @pre other is not null
    * @param other(Tile) is the tile to check if it is combined with
    * @return a new tile object with a value that is the combined
    * value of the two tiles that were combined
    */
    public Tile combine(Tile other){
        if(this.equals(other)){
            SoundEffects.playCombineSound();
            return new Tile(this.value * 2);
        }
        // return a different value?
        return null;
    }

    /**
    * determines if two Tiles are equal
    * @pre other is not null
    * @param other(Tile) is the tile to be compared with
    * @return whether the two tiles are equal
    */
    public boolean equals(Tile other){
        if(this.value == other.getValue()){
            return true;
        }
        return false;
    }

}
