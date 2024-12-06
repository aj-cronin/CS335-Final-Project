/**
 * Authors: Aidan Tucker, AJ Cronin, Brooke Stetson, Nathan Osborne
 * File: SoundEffects.java
 * Purpose: This is a class that has a lot of fields and methods that are static because we know that every time
 * we want to use sound effects, they will be the same. Every instance of SoundEffects would be the same
 * so it makes the most sense to just have things be called statically.
 */
package com.finalproject;

import javafx.scene.media.*;
import java.io.File;
import java.util.ArrayList;

public class SoundEffects {
    // Music to be constantly playing throughout the game.
    private static MediaPlayer backgroundMusic = new MediaPlayer(new Media(new File("finalproject/src/main/resources/sounds/backgroundMusic.wav").toURI().toString()));

    // Sound effects that will be played based on actions in the game.
    private static MediaPlayer moveSound = new MediaPlayer(new Media(new File("finalproject/src/main/resources/sounds/woosh.wav").toURI().toString()));
    private static MediaPlayer newTileSound = new MediaPlayer(new Media(new File("finalproject/src/main/resources/sounds/pop.wav").toURI().toString()));
    private static MediaPlayer combineSound = new MediaPlayer(new Media(new File("finalproject/src/main/resources/sounds/combine.wav").toURI().toString()));
    
    // Collection of sounds for when updating volume.
    private static ArrayList<MediaPlayer> sounds = new ArrayList<MediaPlayer>();

    static {
        sounds.add(backgroundMusic);
        sounds.add(moveSound);
        sounds.add(newTileSound);
        sounds.add(combineSound);
    }

    
    /**
     * playBackgroundMusic() -- Starts the background music loop to play during the game.
     */
    public static void playBackgroundMusic() {
        backgroundMusic.setCycleCount(MediaPlayer.INDEFINITE);
        backgroundMusic.play();
    }

    /**
     * playMoveSound() -- Plays the sound for when you move a tile in the game.
     */
    public static void playMoveSound() {
        moveSound.play();
        moveSound.setOnEndOfMedia(moveSound::stop);
    }

    /**
     * playNewTileSound() -- Plays the sound for when a new tile appears on the board in the game.
     */
    public static void playNewTileSound() {
        newTileSound.play();
        newTileSound.setOnEndOfMedia(newTileSound::stop);
    }

    /**
     * playCombineSound() -- Plays the sound for when two tiles combine on the board.
     */
    public static void playCombineSound() {
        combineSound.play();
        combineSound.setOnEndOfMedia(combineSound::stop);
    }
    
    /**
     * setVolume(val) -- Sets the volume of the sound in the game to a specified value.
     * @param vol - the volume the user sets the sound to.
     */
    public static void setVolume(double vol) {
        for(MediaPlayer sound : sounds) {
            sound.setVolume(vol);
        }
    }

    /**
     * getVolume() -- Gets the volume the sound is set at as an integer.
     * @return the integer value of the volume multiplied by 100.
     */
    public static Integer getVolume() {
        return (int) (moveSound.getVolume() * 100);
    }
}
