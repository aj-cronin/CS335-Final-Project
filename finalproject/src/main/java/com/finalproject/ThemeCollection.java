package com.finalproject;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class ThemeCollection {

    private ArrayList<Theme> themes;
    private Theme selectedTheme;
    
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
        } catch (FileNotFoundException e) {
            System.out.format("%s not found, place it in the proper directory.\n", filename);
        }
    }

    public Theme getSelectedTheme() {
        return selectedTheme;
    }

    public void setSelectedTheme(String name) {
        Theme foundTheme = findTheme(name);
        if(foundTheme != null) selectedTheme = foundTheme;
    }

    private Theme findTheme(String name) {
        for(Theme currTheme : themes) {
            if(currTheme.getName().equals(name)) {
                return currTheme;
            }
        }
        return null;
    }

}
