package com.finalproject;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

        showTitle(root);
        //showWIP(root);
        showBoard(root);
        

        stage.show();
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

    private void showWIP(Group root) {
        Text WIP = new Text("Under Construction...");
        WIP.setX(275);
        WIP.setY(320);
        WIP.setTextAlignment(TextAlignment.CENTER);
        WIP.setFill(Color.web("756452"));
        WIP.setFont(Font.loadFont("file:finalproject/src/main/resources/fonts/ClearSans-Regular.ttf", 48));
        root.getChildren().add(WIP);
    }

    // creates an empty board of tiles
    private void showBoard(Group root) {
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

                // Add the tile to the GridPane
                board.add(tile, col, row);
            }
        }

        root.getChildren().add(board);
    }
    

    public void updateTiles(ArrayList<Tile> boardList){
        
        for(int row = 0; row < boardList.size(); row ++){
            Tile tmpTile = boardList.get(row);
            if(tmpTile != null){
                
            }
        }
    }
    

}