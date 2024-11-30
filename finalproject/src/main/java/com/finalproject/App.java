package com.finalproject;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.*;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private int start = 25;
    // can change to make board size dynamic
    private int boardX = start + 4;
    private int boardY = start + 4;
    private GridPane board = new GridPane();
    // Key: X-coordinate and Y-coordinate seperated by a comma
    private HashMap<String, StackPane> gridMap = new HashMap<String, StackPane>();

    public static void main(String[] args) {
        launch();
    }
    
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("2048");
        Group root = new Group();

        scene = new Scene(root, 980, 640);
        scene.setFill(Color.web("FAF8F0"));
        stage.setScene(scene);

        Leaderboard myLeaderboard = new Leaderboard("finalproject/src/main/resources/data/leaderboard.csv");

        SoundEffects.playBackgroundMusic();
        showEverything(root, myLeaderboard);
        
        stage.show();
    }

    private void showEverything(Group root, Leaderboard lb) {
        showTitle(root);
        showBoard(root);
        showLeaderboardButton(root, lb);
        showSoundButton(root);
        showVolumeControl(root);
    }

    private void showVolumeControl(Group root) {
        // Easy Values for Quick Changes to the whole group.
        int XOFFSET = 50;
        int YOFFSET = -10;
        
        // Adds border behind volume control.
        Rectangle border = new Rectangle(160, 50);
        border.setFill(Color.web("756452"));
        border.setX(750+XOFFSET);
        border.setY(25+YOFFSET);
        border.setArcWidth(15);
        border.setArcHeight(15);

        // Creates text for volume number that updates based on slider.
        Text volumeNumber = new Text("Volume: 100");
        volumeNumber.setLayoutX(765+XOFFSET);
        volumeNumber.setLayoutY(47+YOFFSET);
        volumeNumber.setFill(Color.WHITE);
        volumeNumber.setFont(Font.loadFont("file:FinalProject/src/main/resources/fonts/ClearSans-Regular.ttf", 15));

        // Creates slider that can adjust the volume.
        Slider slider = new Slider(0.0, 1.0, 1.0);
        slider.setLayoutX(760+XOFFSET);
        slider.setLayoutY(52+YOFFSET);
        slider.setOnMouseDragged((e) -> {
            SoundEffects.setVolume(slider.getValue());
            volumeNumber.setText("Volume: " + SoundEffects.getVolume().toString());
        });

        // Add them to root.
        root.getChildren().add(border);
        root.getChildren().add(slider);
        root.getChildren().add(volumeNumber);
    }


    private void showSoundButton(Group root) {
        Button soundButton = new Button("Play Sound");
        soundButton.setLayoutX(850);
        soundButton.setLayoutY(300);
        soundButton.setOnAction((e) -> {
           SoundEffects.playMoveSound(); 
        });

        root.getChildren().add(soundButton);
    }

    private void showTitle(Group root) {
        Text title = new Text("2048");
        title.setX(430);
        title.setY(60);
        title.setTextAlignment(TextAlignment.CENTER);
        title.setFill(Color.web("756452"));
        title.setFont(Font.loadFont("file:FinalProject/src/main/resources/fonts/ClearSans-Bold.ttf", 48));
        root.getChildren().add(title);
    }

    private void showLeaderboardButton(Group root, Leaderboard lb) {
        Button lbButton = new Button("Leaderboard");
        lbButton.setLayoutX(100);
        lbButton.setOnAction((e) -> {
           displayLeaderboard(root, lb); 
        });

        root.getChildren().add(lbButton);
    }

    private void displayLeaderboard(Group root, Leaderboard lb) {
        String lbString = lb.toString();
        Text lbText = new Text(lbString);

        lbText.setFill(Color.web("756452"));
        lbText.setFont(Font.loadFont("file:FinalProject/src/main/resources/fonts/ClearSans-Regular.ttf", 20));

        lbText.setX(400);
        lbText.setY(200);

        Rectangle coverBoard = new Rectangle(550, 500);
        coverBoard.setFill(Color.web("FAF8F0"));
        coverBoard.setOpacity(0.8);
        coverBoard.setX(200);
        coverBoard.setY(100);

        Button exitButton = new Button("Close");

        exitButton.setLayoutX(100);
        exitButton.setLayoutY(45);
        exitButton.setOnAction((e) -> {
            root.getChildren().clear();
            showEverything(root, lb);
        });

        root.getChildren().add(coverBoard);
        root.getChildren().add(lbText);
        root.getChildren().add(exitButton);

    }

    // creates an empty board of tiles and hashes each StackPane used as a tile to the gridMap
    private void showBoard(Group root) {
        // Add border behind tiles
        Rectangle border = new Rectangle(450, 450);
        border.setX(252);
        border.setY(127);
        border.setFill(Color.web("9B8A7C"));
        border.setArcHeight(25);
        border.setArcWidth(25);

        // creating a grid to emulate the board and centering it
        board.setAlignment(Pos.CENTER);
        board.setHgap(5);
        board.setVgap(5); 
        board.setPadding(new Insets(20));

        // fill in 
        double tileSize = 100;
        
        for (int row = start; row < boardX; row++) {
            for (int col = start + 25; col < boardY + 25; col++) {
                StackPane tile = new StackPane();
                tile.setMinSize(tileSize, tileSize);
                tile.setPrefSize(tileSize, tileSize);
                // can change color later
                tile.setStyle("-fx-background-color: #000000;");
                String keyStr = (row - start) + "," + (col - start - 25);
                gridMap.put(keyStr, tile);
                // Add the tile to the GridPane
                board.add(tile, col, row);
            }
        }

        root.getChildren().add(border);
        root.getChildren().add(board);
    }
    

    // IDEA: use a hashmap to store the board locations of each "tile" space to improve efficiency instead of n^2
    // Takes in the current boardList in which the tiles for the game are stored
    public void updateTiles(ArrayList<ArrayList<Tile>> boardList){
        // iterate through the boardList of the tiles
        for(int row = 0; row < boardList.size(); row ++){
            for(int col = 0; col < boardList.get(row).size(); col ++){
                Tile tmpTile = boardList.get(row).get(col);
                // find the corresponding space on the gridpane
                for(Node node: board.getChildren()){
                    // if the row and column correlate to the boardList item
                    if((board.getRowIndex(node) != null) || (board.getColumnIndex(node) != null)){
                        if(((board.getRowIndex(node) - start) == row) && ((board.getColumnIndex(node) - start - 25) == col)){
                            // once the corresponding tile is found, set the style color
                            if(tmpTile == null){
                                node.setStyle("-fx-background-color: #000000;");
                            }
                            else{
                                // set the color according to tile.getcolor()
                                // just changed to white for now
                                node.setStyle("-fx-background-color: #FFFFFF;");
                                // set the label on the tile according to the tile.getValue()
                            
                            }
                        }
                    }
                }
            }
        }
    }
    
    

}