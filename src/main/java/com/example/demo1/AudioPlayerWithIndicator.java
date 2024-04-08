package com.example.demo1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioPlayerWithIndicator extends Application {

    private Line indicatorLine;
    private Button playButton;

    @Override
    public void start(Stage primaryStage) {
        AnchorPane root = new AnchorPane();
        root.setPrefSize(600, 400);

        Pane pane = new Pane();
        pane.setLayoutX(26);
        pane.setLayoutY(338);
        pane.setPrefHeight(48);
        pane.setPrefWidth(200);

        File audioFile = new File("шлях/до/вашого/аудіофайлу.wav");
        if (audioFile.exists()) {
            playButton = new Button("Відтворити");
            playButton.setLayoutX(80);
            playButton.setLayoutY(21);
            playButton.setOnAction(e -> {
                try {
                    playAudio(audioFile);
                    indicatorLine.setVisible(true);
                } catch (IOException | UnsupportedAudioFileException | LineUnavailableException ex) {
                    ex.printStackTrace();
                }
            });

            indicatorLine = new Line(-100, 14, 100, 14);
            indicatorLine.setVisible(false);

            pane.getChildren().addAll(playButton, indicatorLine);
        } else {
            System.out.println("Файл не існує");
        }

        root.getChildren().add(pane);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Аудіопрогравач з індикатором");
        primaryStage.show();
    }

    private void playAudio(File file) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
        AudioFormat format = audioInputStream.getFormat();
        DataLine.Info info = new DataLine.Info(Clip.class, format);
        Clip clip = (Clip) AudioSystem.getLine(info);
        clip.open(audioInputStream);
        clip.start();

        Thread indicatorThread = new Thread(() -> {
            try {
                while (clip.isOpen() && clip.getMicrosecondPosition() < clip.getMicrosecondLength()) {
                    double percentagePlayed = (double) clip.getMicrosecondPosition() / clip.getMicrosecondLength();
                    updateIndicator(percentagePlayed);
                    Thread.sleep(100);
                }
                updateIndicator(1.0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        indicatorThread.start();
    }

    private void updateIndicator(double percentage) {
        double width = indicatorLine.getParent().getLayoutBounds().getWidth();
        double x = width * percentage;
        indicatorLine.setEndX(x);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
