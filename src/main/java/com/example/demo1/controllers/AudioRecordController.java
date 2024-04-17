package com.example.demo1.controllers;

import com.example.demo1.interfaces.IStyleService;
import com.example.demo1.service.StyleService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javax.sound.sampled.*;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class AudioRecordController implements Initializable {

    private String path;

    @FXML
    private Label label;

    private boolean isRecording = false;

    @FXML
    private ImageView stopRecordButton;

    private IStyleService styleService = new StyleService();

    private TargetDataLine targetDataLine;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        record();
    }

    public void record() {

        String recordPath = getPath();

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
                    AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, new File(recordPath + "/sacred_audio.wav"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            recordingThread.start();

        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void stop(MouseEvent mouseEvent) {
        if (isRecording) {
            targetDataLine.stop();
            targetDataLine.close();
            isRecording = false;
           // stopTimer();
            label.setText("Stopped");
            System.out.println("Stop recording");


        }
    }

    public void stopRecordAnimationStart(MouseEvent mouseEvent) {
        styleService.transitionStart(stopRecordButton);
    }

    public void stopRecordAnimationEnd(MouseEvent mouseEvent) {
        styleService.transitionFinish(stopRecordButton);
    }
}

