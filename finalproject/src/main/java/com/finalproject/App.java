package com.finalproject;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

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
        showWIP(root);
        

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
        WIP.setFont(Font.loadFont("file:FinalProject/src/main/resources/fonts/ClearSans-Regular.ttf", 48));
        root.getChildren().add(WIP);
    }

    

}