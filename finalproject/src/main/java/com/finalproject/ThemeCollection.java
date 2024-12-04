/**
 * Authors: AJ Cronin, Aidan Tucker, Brooke Stetson, Nathan Osborne
 * File: ThemeCollection.java
 * Purpose: Represents the collections of themes present in the game of 2048.
 * Takes a themes.csv file with information about all themes, and parses each line
 * to add them as themes in an ArrayList. Also keeps track of the currently "selected theme".
 */
package com.finalproject;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class ThemeCollection {

    private ArrayList<Theme> themes;
    private Theme selectedTheme;
    
    /**
     * @param filename - String that is the name of a file (likely themes.csv)
     * 
     * @pre file follows themes.csv format.
     */
    public ThemeCollection(String filename) {
        themes = new ArrayList<Theme>();
        try {
            Scanner fileScanner = new Scanner(new File(filename));
            fileScanner.nextLine();
            while(fileScanner.hasNextLine()){
                themes.add(new Theme(fileScanner.nextLine()));
            }
            selectedTheme = themes.get(0);
            fileScanner.close();
        // If file is not found we throw the exception.
        } catch (FileNotFoundException e) {
            System.out.format("%s not found, place it in the proper directory.\n", filename);
        }
    }
    
    /**
     * @return selectedTheme
     */
    public Theme getSelectedTheme() {
        return selectedTheme;
    }

    /**
     * @return the list of themes present
     */
    public ArrayList<Theme> getThemes() {
        return new ArrayList<Theme>(themes);
    }

    /**
     * @param name the name of the theme that we want to set as our selected theme.
     */
    public void setSelectedTheme(String name) {
        Theme foundTheme = findTheme(name);
        // If the theme doesn't exist, we just keep the theme the same.
        if(foundTheme != null) selectedTheme = foundTheme;
    }

    /**
     * findTheme(name) -- finds a theme with a given name if it is present in the collection.
     * @param name - name of the theme to be searched for.
     * @return The found theme
     */
    private Theme findTheme(String name) {
        for(Theme currTheme : themes) {
            if(currTheme.getName().equals(name.toLowerCase())) {
                return currTheme;
            }
        }
        return null;
    }

}
