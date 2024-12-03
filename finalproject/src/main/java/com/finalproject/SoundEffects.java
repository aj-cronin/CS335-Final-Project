/**
 * SoundEffects.java
 * 
 * This is a class that has a lot of fields and methods that are static because we know that every time
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

    public static void playBackgroundMusic() {
        backgroundMusic.setCycleCount(MediaPlayer.INDEFINITE);
        backgroundMusic.play();
    }

    public static void playMoveSound() {
        moveSound.play();
        moveSound.setOnEndOfMedia(moveSound::stop);
    }

    public static void playNewTileSound() {
        newTileSound.play();
        newTileSound.setOnEndOfMedia(newTileSound::stop);
    }

    public static void playCombineSound() {
        combineSound.play();
        newTileSound.setOnEndOfMedia(combineSound::stop);
    }
    
    public static void setVolume(double vol) {
        for(MediaPlayer sound : sounds) {
            sound.setVolume(vol);
        }
    }

    public static Integer getVolume() {
        return (int) (moveSound.getVolume() * 100);
    }
}
