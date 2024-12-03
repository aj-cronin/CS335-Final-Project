package com.finalproject;

public class Enums{
  
    // indicates the shade range for the tiles, can be changed to represent different colors based 
    // on the "theme" indicated in the Board class.
    public enum COLOR{
        // eleven shades total for each Tile from 2 to 2048
        SHADE1, SHADE2, SHADE3, SHADE4, SHADE5, SHADE6, SHADE7, SHADE8, SHADE9, SHADE10, SHADE11;
    }

    // Represents the "theme" of the game controlling the shade range of the tiles
    public enum THEME{
        // if not chosen, BASIC is the default THEME
        BASIC, DARK, LIGHT;
    }

    public enum DIRECTION{
        // for Board class
        LEFT, RIGHT, UP, DOWN;
    }

}
