package com.finalproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Leaderboard {
    
    ArrayList<HashMap<String,Integer>> players;
    int size;

    public Leaderboard() {
        // Initialize our collection of players.
        // Array of HashMaps so that we can have an ordered collection with multiple values.
        players = new ArrayList<HashMap<String,Integer>>();
        try {
            File lbFile = new File("leaderboard.csv");
            Scanner lbScanner = new Scanner(lbFile);
            
            // We keep track of how many players have been added because we want the board to only hold the top 10 scores.
            int i = 0;
            while (lbScanner.hasNextLine() && i < 10){
                String[] currLine = lbScanner.nextLine().split(",");
                HashMap<String,Integer> currPlayer = new HashMap<String,Integer>();
                currPlayer.put(currLine[0], Integer.parseInt(currLine[1]));
                i++;
            }
            lbScanner.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("leaderboard.csv not found, place it in the proper directory");
        } catch (NumberFormatException e) {
            System.out.println("leaderboard.csv contains score that is not a number.");
        }

        size = players.size();
    }

    public void addPlayer(String name, int score) {
        HashMap<String,Integer> newPlayer = new HashMap<String,Integer>();
        newPlayer.put(name, score);

        // Determine placement in leaderboard. Stop early if it doesn't make top 10.
        for(int i = 0; i < size; i++) {
            if(getPlayerScore(i) < newPlayer.get(name)) {
                players.add(i, newPlayer);
            }
        }

        if(size < 10){
            players.add(newPlayer); 
            size++;
        } 
    }

    public ArrayList<HashMap<String,Integer>> getLeaderboard() {
        // Return copy of the list
        return new ArrayList<HashMap<String,Integer>>(players);
    }

    public String toString() {
        String result = "";
        for(int i = 0; i < size; i++) {
            result += String.format("%d: %s - %s\n", i+1, getPlayerName(i), getPlayerScore(i));
        }
        return result;
    }

    private String getPlayerName(int index) {
        return players.get(index).keySet().iterator().next();
    }

    private int getPlayerScore(int index) {
        return players.get(index).values().iterator().next();
    }

}
