package com.example.demo1;
import java.io.File;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class JavaFX_Media extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception {
        // TODO Auto-generated method stub
        //Initialising path of the media file, replace this with your file path
        String path = "/Users/qq/Desktop/javascript курси/git-basics-materials/001_ВҐ•§•≠®•/Основы использования Git Lesson 01.mp4";

        //Instantiating Media class
        Media media = new Media(new File(path).toURI().toString());

        //Instantiating MediaPlayer class
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        //Instantiating MediaView class
        MediaView mediaView = new MediaView(mediaPlayer);

        //by setting this property to true, the Video will be played
        mediaPlayer.setAutoPlay(true);

        //setting group and scene
        Group root = new Group();
        root.getChildren().add(mediaView);
        Scene scene = new Scene(root,500,400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Playing video");
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

}