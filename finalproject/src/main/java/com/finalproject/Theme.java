/**
 * Authors: AJ Cronin, Aidan Tucker, Brooke Stetson, Nathan Osborne
 * File: Theme.java
 * Purpose: Represents a theme for the Game 2048. A Theme object holds a name, 
 * a starting and ending tile color,background color, secondary color, text color, 
 * and an optional image overlay for the background. For the colors between the 
 * starting and ending tiles, a gradient is created using math to increment rgb 
 * values accordingly.
 */

package com.finalproject;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.io.File;
import java.util.ArrayList;

public class Theme {

    String name;
    Color start;
    Color end;
    Color background;
    Color secondary;
    Color text;
    ImageView image;

    ArrayList<Color> tileColors;
    
    /**
     * @param themeInfo - String with important values for theme seperated by commas.
     * 
     * @pre themeInfo fits csv format.
     */
    public Theme(String themeInfo) {
        String[] elements = themeInfo.trim().split(",");
        // Extract info from themeInfo as long as it has proper length.
        if(elements.length == 6 || elements.length == 7) {
            name = elements[0];
            start = Color.web(elements[1]);
            end = Color.web(elements[2]);
            background = Color.web(elements[3]);
            secondary = Color.web(elements[4]);
            text = Color.web(elements[5]);
            tileColors = new ArrayList<Color>();
            makeGradient();
        }
        // Only try to set the overlay image if there is a 7th value in the list.
        if(elements.length == 7) {
            image = new ImageView(new Image(new File(elements[6]).toURI().toString()));
        }
    }

    
    /**
     * makeGradient() -- Adds colors to tileColor ArrayList based on the starting and ending
     * colors for the theme. This allows the user to only have to specify two colors when creating
     * a theme.
     */
    private void makeGradient() {
        // Get the RGB values of the starting and ending colors, and find out how
        // much needs to be incremented over 11 tiles to get to the ending color.
        double r1 = start.getRed() *255;
        double g1 = start.getGreen() *255;
        double b1 = start.getBlue() *255;

        double r2 = end.getRed() *255;
        double g2 = end.getGreen() *255;
        double b2 = end.getBlue() *255;

        double redInc = (r2-r1) / 11.0;
        double greenInc = (g2-g1) / 11.0;
        double blueInc = (b2-b1) / 11.0;

        // Add a color for each incremented color.
        for(int i = 0; i < 11; i++) {
            Color currColor = Color.rgb((int) (r1 + redInc * i), (int) (g1 + greenInc * i), (int) (b1 + blueInc * i));
            tileColors.add(currColor);
        }
    }
    
    /**
     * @param index - Index of color that is being returned.
     * @return a color from the gradient.
     */
    public Color getColor(int index) {
        return tileColors.get(index);
    }

    /**
     * @return - name of the theme.
     */
    public String getName() {
        return name;
    }

    /**
     * @return - name of the theme with an uppercase first letter.
     */
    public String getUppercaseName() {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    /**
     * @return - the color of the background
     */
    public Color getBackground() {
        return background;
    }

    /**
     * @return - the secondary color of the theme
     */
    public Color getSecondary() {
        return secondary;
    }

    /**
     * @return - the color of the text
     */
    public Color getText() {
        return text;
    }

    /**
     * @return - the overlay image for the background of the theme
     */
    public ImageView getImage(){
        return image;
    }

}
