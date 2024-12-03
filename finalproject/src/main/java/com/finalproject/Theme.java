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

    public Theme(String themeInfo) {
        String[] elements = themeInfo.trim().split(",");
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
        if(elements.length == 7) {
            image = new ImageView(new Image(new File(elements[6]).toURI().toString()));
        }
    }

    private void makeGradient() {
        double r1 = start.getRed() *255;
        double g1 = start.getGreen() *255;
        double b1 = start.getBlue() *255;

        double r2 = end.getRed() *255;
        double g2 = end.getGreen() *255;
        double b2 = end.getBlue() *255;

        double redInc = (r2-r1) / 11.0;
        double greenInc = (g2-g1) / 11.0;
        double blueInc = (b2-b1) / 11.0;

        for(int i = 0; i < 11; i++) {
            Color currColor = Color.rgb((int) (r1 + redInc * i), (int) (g1 + greenInc * i), (int) (b1 + blueInc * i));
            tileColors.add(currColor);
        }
    }

    public Color getColor(int index) {
        return tileColors.get(index);
    }

    public String getName() {
        return name;
    }

    public String getUppercaseName() {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    public Color getBackground() {
        return background;
    }

    public Color getSecondary() {
        return secondary;
    }

    public Color getText() {
        return text;
    }

    public ImageView getImage(){
        return image;
    }

}
