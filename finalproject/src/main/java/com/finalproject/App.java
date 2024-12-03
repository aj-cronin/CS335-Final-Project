package com.finalproject;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.*;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.event.EventHandler;

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
    private ThemeCollection myThemes;
    private Theme selectedTheme;
    
    // the controller for the App
    private GameController controller;

    public static void main(String[] args) {
        launch();
    }
    
    @Override
    public void start(Stage stage) throws IOException {
        // initializing the controller
        controller = new GameController();
        controller.start();

        stage.setTitle("2048");
        Group root = new Group();

        Leaderboard myLeaderboard = new Leaderboard("finalproject/src/main/resources/data/leaderboard.csv");
        myThemes = new ThemeCollection("finalproject/src/main/resources/data/themes.csv");
        selectedTheme = myThemes.getSelectedTheme();
        controller.setBoardTheme(selectedTheme);

        scene = new Scene(root, 980, 640);
        scene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event){
                if (!controller.isOver()){
                    if (event.getCode().getName().equals("Up")){
                        controller.move(Enums.DIRECTION.UP);
                        updateTiles(controller.getBoardList());
                    } else if (event.getCode().getName().equals("Down")){
                        controller.move(Enums.DIRECTION.DOWN);
                        updateTiles(controller.getBoardList());
                    } else if (event.getCode().getName().equals("Left")){
                        controller.move(Enums.DIRECTION.LEFT);
                        updateTiles(controller.getBoardList());
                    } else if (event.getCode().getName().equals("Right")){
                        controller.move(Enums.DIRECTION.RIGHT);
                        updateTiles(controller.getBoardList());
                    }
                }
            }
        });
        stage.setScene(scene);

        SoundEffects.playBackgroundMusic();

        showEverything(root, myLeaderboard);
        updateTiles(controller.getBoardList());

        stage.show();
    }

    private void showEverything(Group root, Leaderboard lb) {
        scene.setFill(selectedTheme.getBackground());
        showTitle(root);
        showBoard(root);
        showLeaderboardButton(root, lb);
        showThemePicker(root, lb);
        showVolumeControl(root);
        showTiles(root);
    }

    private void showThemePicker(Group root, Leaderboard lb) {
        ObservableList<String> options = FXCollections.observableArrayList();
        for(Theme theme: myThemes.getThemes()) {
            options.add((theme.getUppercaseName()));
        }
        ComboBox<String> themeBox = new ComboBox<String>(options);
        themeBox.setLayoutX(10);
        themeBox.setLayoutY(10);
        themeBox.setPromptText(selectedTheme.getUppercaseName());

        themeBox.setOnAction((e) -> {
            if(themeBox.getValue() != null) {
                myThemes.setSelectedTheme("" + themeBox.getValue());
                selectedTheme = myThemes.getSelectedTheme();
                controller.setBoardTheme(selectedTheme);
                root.getChildren().clear();
                board.getChildren().clear();
                showEverything(root, lb);
                updateTiles(controller.getBoardList());
            }
        });

        root.getChildren().add(themeBox);
    }

    private void showTiles(Group root) {
        int yPos = 100;
        for(int i = 0; i < 11; i++) {
            Rectangle rect = new Rectangle(50, 30);
            rect.setFill(selectedTheme.getColor(i));
            rect.setX(800);
            rect.setY(yPos);
            yPos += 40;
            root.getChildren().add(rect);
        }
    }

    private void showVolumeControl(Group root) {
        // Easy Values for Quick Changes to the whole group.
        int XOFFSET = 50;
        int YOFFSET = -10;
        
        // Adds border behind volume control.
        Rectangle border = new Rectangle(160, 50);
        border.setFill(selectedTheme.getSecondary());
        border.setX(750+XOFFSET);
        border.setY(25+YOFFSET);
        border.setArcWidth(15);
        border.setArcHeight(15);

        // Creates text for volume number that updates based on slider.
        Text volumeNumber = new Text("Volume: 100");
        volumeNumber.setLayoutX(765+XOFFSET);
        volumeNumber.setLayoutY(47+YOFFSET);
        volumeNumber.setFill(selectedTheme.getText());
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

    private void showTitle(Group root) {
        Text title = new Text("2048");
        title.setX(430);
        title.setY(60);
        title.setTextAlignment(TextAlignment.CENTER);
        title.setFill(selectedTheme.getSecondary());
        title.setFont(Font.loadFont("file:FinalProject/src/main/resources/fonts/ClearSans-Bold.ttf", 48));
        root.getChildren().add(title);
    }

    private void showLeaderboardButton(Group root, Leaderboard lb) {
        Button lbButton = new Button("Leaderboard");
        lbButton.setLayoutX(120);
        lbButton.setLayoutY(10);
        lbButton.setOnAction((e) -> {
           displayLeaderboard(root, lb); 
        });

        root.getChildren().add(lbButton);
    }

    private void displayLeaderboard(Group root, Leaderboard lb) {
        String lbString = lb.toString();
        Text lbText = new Text(lbString);

        lbText.setFill(selectedTheme.getSecondary());
        lbText.setFont(Font.loadFont("file:FinalProject/src/main/resources/fonts/ClearSans-Regular.ttf", 20));

        lbText.setX(400);
        lbText.setY(200);

        Rectangle coverBoard = new Rectangle(550, 500);
        coverBoard.setFill(selectedTheme.getBackground());
        coverBoard.setOpacity(0.8);
        coverBoard.setX(200);
        coverBoard.setY(100);

        Button exitButton = new Button("Close");

        exitButton.setLayoutX(120);
        exitButton.setLayoutY(45);
        exitButton.setOnAction((e) -> {
            root.getChildren().clear();
            board.getChildren().clear();
            showEverything(root, lb);
            updateTiles(controller.getBoardList());
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
        border.setFill(selectedTheme.getSecondary());
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
                // set the default color to black, indicating no tile is currently there
                tile.setStyle("-fx-background-color: #FFFFFF7F;");
                // Add the tile to the GridPane
                board.add(tile, col, row);
            }
        }

        root.getChildren().add(border);
        root.getChildren().add(board);
    }
    

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
                                node.setStyle("-fx-background-color: #FFFFFF7F;");

                                Text textLabel = new Text("");
                                StackPane stackPane = (StackPane) node;

                                for (Object element : new ArrayList<Node>(stackPane.getChildren())){
                                    if (element.getClass() == textLabel.getClass()){
                                        stackPane.getChildren().remove(element);
                                    }
                                }
                            }
                            else{
                                // set the color of the current tile on the board based on the theme and value
                                setTileColor(node, tmpTile);

                                // set the label on the tile according to the tile value
                                // creating the label with the value
                                Text textLabel = new Text(String.valueOf(tmpTile.getValue()));
                                // adding the label to the current "tile" node on the board (GridPane)
                                StackPane stackPane = (StackPane) node;
                                for (Object element : new ArrayList<Node>(stackPane.getChildren())){
                                    if (element.getClass() == textLabel.getClass()){
                                        stackPane.getChildren().remove(element);
                                    }
                                }
                                stackPane.getChildren().add(textLabel);
                                // centering the label in the "tile"
                                StackPane.setAlignment(textLabel, Pos.CENTER);
                            }
                        }
                    }
                }
            }
        }
    }

    // Takes a node from the GridPane representing a Tile
    // Returns
    private void setTileColor(Node node, Tile tmpTile){
        Color tileColor = tmpTile.getColor();
        node.setStyle(String.format("-fx-background-color: #%s;", tileColor.toString().substring(2,8)));
    }
    
    

}