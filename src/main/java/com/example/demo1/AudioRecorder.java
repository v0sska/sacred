package com.example.demo1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.sound.sampled.*;

public class AudioRecorder extends Application {

    private boolean isRecording = false;
    private TargetDataLine targetDataLine;

    @Override
    public void start(Stage primaryStage) {
        startRecording(); // Почати запис при відкритті форми

        Button stopButton = new Button("Stop Recording");
        stopButton.setOnAction(event -> stopRecording());

        VBox root = new VBox(stopButton);
        Scene scene = new Scene(root, 200, 100);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Audio Recorder");
        primaryStage.show();
    }

    private void startRecording() {
        try {
            AudioFormat audioFormat = new AudioFormat(44100, 16, 2, true, true);
            DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class, audioFormat);
            targetDataLine = (TargetDataLine) AudioSystem.getLine(dataLineInfo);
            targetDataLine.open(audioFormat);
            targetDataLine.start();
            isRecording = true;

            Thread recordingThread = new Thread(() -> {
                AudioInputStream audioInputStream = new AudioInputStream(targetDataLine);
                try {
                    AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, new java.io.File("/Users/qq/Desktop/c/recorded_audio1.wav"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            recordingThread.start();

        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    private void stopRecording() {
        if (isRecording) {
            targetDataLine.stop();
            targetDataLine.close();
            isRecording = false;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}