package com.finalproject;

import javafx.scene.paint.Color;

/*
    @authors: ...
    Repreents a Tile piece that contains an integer value with an associated color
    and the ability to combine with a Tile of equal value to create a new Tile.
 */ 
public class Tile{
    private int value;
    private Theme theme;

    // @pre: value must be a power of 2 and > 0
    public Tile(int val){
        this.value = val;
        theme = new Theme("light,d8c644,ff7575,FAF8F0,756452,FFFFFF");
    }

    public Tile(int val, Theme theme){
        this.value = val;
        this.theme = theme;
    }
    
    // gets the associated color to the Tile based on its value
    public Color getColor(){
        // calculates what power of 2 value is
        double power = (Math.log(this.value))/(Math.log(2));
        // finds the associated COLOR enum
        return theme.getColor((int)power - 1);
    }

    public int getValue(){
        return this.value;
    }

    public void setTheme(Theme newTheme){
        theme = newTheme;
    }

    // combines two Tiles into one if they are equal, returns this new Tile
    public Tile combine(Tile other){
        if(this.equals(other)){
            SoundEffects.playCombineSound();
            return new Tile(this.value * 2);
        }
        // return a different value?
        return null;
    }

    // determines if two Tiles are equal
    public boolean equals(Tile other){
        if(this.value == other.getValue()){
            return true;
        }
        return false;
    }

}