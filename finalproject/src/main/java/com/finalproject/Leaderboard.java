package com.finalproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Leaderboard {
    
    ArrayList<HashMap<String,Integer>> players;
    int size;
    String filename;

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
            
        } catch (FileNotFoundException e) {
            System.out.format("%s not found, place it in the proper directory.\n", filename);
        } catch (NumberFormatException e) {
            System.out.format("%s contains score that is not a number.\n", filename);
        }

        size = players.size();
        this.filename = filename;
    }

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

        if(size < 10){
            players.add(newPlayer); 
            size++;
        } 
    }

    public void writeToFile() {
        try{
            FileWriter writer = new FileWriter(filename);
            String result = "";
            for(int i = 0; i < size; i++) {
                result += String.format("%s,%d\n",getPlayerName(i), getPlayerScore(i));
            }
            writer.write(result);
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.format("%s not found, place it in the proper directory.\n", filename);
        } catch (IOException e) {
            System.out.format("%s cannot be written to.\n", filename);
        }
    }

    public ArrayList<HashMap<String,Integer>> getLeaderboard() {
        // Return copy of the list
        return new ArrayList<HashMap<String,Integer>>(players);
    }

    public String toString() {
        String result = "";
        for(int i = 0; i < size; i++) {
            result += String.format("%d: %s - %d\n", i+1, getPlayerName(i), getPlayerScore(i));
        }
        return result.trim();
    }

    public String getPlayerName(int index) {
        return players.get(index).keySet().iterator().next();
    }

    public int getPlayerScore(int index) {
        return players.get(index).values().iterator().next();
    }

}
