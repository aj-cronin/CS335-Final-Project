/**
 * @authors Aidan Tucker, AJ Cronin, Brooke Stetson, Nathan Osborne
 * @file Enums.java
 * @purpose Provides the enum used within our classes to avoid primitive obsession. DIRECTION is
 * used to represent the choices of movement the user can choose from to move on the game board.
 */


package com.finalproject;

/**
 * Enums provided to represent game functionality.
 */
public class Enums{

    // Represents movement within the game board
    public enum DIRECTION{
        // The four options for movement
        LEFT, RIGHT, UP, DOWN;
    }
}
