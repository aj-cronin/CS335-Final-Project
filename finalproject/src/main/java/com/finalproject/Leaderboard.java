/**
 * Authors: Aidan Tucker, AJ Cronin, Brooke Stetson, Nathan Osborne
 * File: Leaderboard.java
 * Purpose: Represents a Leaderboard for the game of 2048. When using, an object
 * is made containing information about the players and their scores based on a
 * csv file input. The class has the ability to get the players, scores, and write
 * back to the file the object is based on.
 */

package com.finalproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Leaderboard {
    
    private ArrayList<HashMap<String,Integer>> players;
    private int size;
    private String filename;

    /**
     * @param filename - a String that is the name of the leaderboard file being used.
     */
    public Leaderboard(String filename) {
        // Initialize our collection of players.
        // Array of HashMaps so that we can have an ordered collection with multiple values.
        players = new ArrayList<HashMap<String,Integer>>();
        try {
            File lbFile = new File(filename);
            Scanner lbScanner = new Scanner(lbFile);
            
            // We keep track of how many players have been added because we want the board to only hold the top 10 scores.
            int i = 0;
            while (lbScanner.hasNextLine() && i < 10){
                String[] currLine = lbScanner.nextLine().split(",");
                HashMap<String,Integer> currPlayer = new HashMap<String,Integer>();
                currPlayer.put(currLine[0], Integer.parseInt(currLine[1]));
                players.add(currPlayer);
                i++;
            }
            lbScanner.close();
            // We want to catch our possible exceptions of the file either not existing or holding invalid data.
        } catch (FileNotFoundException e) {
            System.out.format("%s not found, place it in the proper directory.\n", filename);
        } catch (NumberFormatException e) {
            System.out.format("%s contains score that is not a number.\n", filename);
        }

        // Store the size and name for easy writeback later.
        size = players.size();
        this.filename = filename;
    }

    /**
     * addPlayer(name, score) -- adds a player to the leaderboard.
     * 
     * @param name - String for the name of the player being added to the leaderboard
     * @param score - The score of the player being added to the leaderboard
     */
    public void addPlayer(String name, int score) {
        HashMap<String,Integer> newPlayer = new HashMap<String,Integer>();
        newPlayer.put(name, score);

        // Determine placement in leaderboard. Stop early if it doesn't make top 10.
        for(int i = 0; i < size; i++) {
            if(getPlayerScore(i) < newPlayer.get(name)) {
                players.add(i, newPlayer);
                break;
            }
        }

        // If the new player doesn't beat any previous players 
        //and our list is small enough, we just add it to the end.
        if(size < 10){
            players.add(newPlayer); 
            size++;
        } 
    }

    /**
     * writeToFile() -- Takes the current leaderboard and writes back the info the leaderboard file so
     * that the new players can be found in future runs of the game.
     */
    public void writeToFile() {
        try{
            FileWriter writer = new FileWriter(filename);
            String result = "";
            for(int i = 0; i < size; i++) {
                result += String.format("%s,%d\n",getPlayerName(i), getPlayerScore(i));
            }
            writer.write(result);
            writer.close();
        // Catch the exceptions when the file may not exist or cannot be written to for some reason.
        } catch (FileNotFoundException e) {
            System.out.format("%s not found, place it in the proper directory.\n", filename);
        } catch (IOException e) {
            System.out.format("%s cannot be written to.\n", filename);
        }
    }

    /**
     * toString() -- returns string representation of leaderboard.
     */
    public String toString() {
        String result = "";
        for(int i = 0; i < size; i++) {
            result += String.format("%d: %s - %d\n", i+1, getPlayerName(i), getPlayerScore(i));
        }
        return result.trim();
    }

    
    /**
     * @param index -- index of the player's name you want to get.
     * @return the name of the player at a specific index
     */
    public String getPlayerName(int index) {
        return players.get(index).keySet().iterator().next();
    }

    /**
     * @param index -- index of the player's score you want to get.
     * @return the score of the player at a specific index
     */
    public int getPlayerScore(int index) {
        return players.get(index).values().iterator().next();
    }

}
