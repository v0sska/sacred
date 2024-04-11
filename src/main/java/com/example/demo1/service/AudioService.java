package com.example.demo1.service;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import javafx.scene.shape.Line;

public class AudioService {

    public void playAudio(File file, Line line) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
        AudioFormat format = audioInputStream.getFormat();
        DataLine.Info info = new DataLine.Info(Clip.class, format);
        Clip clip = (Clip) AudioSystem.getLine(info);
        clip.open(audioInputStream);
        clip.start();

        //Потік який контолює лінію,яка йде поки грає аудіо
        Thread indicatorThread = new Thread(() -> {
            try {
                while (clip.isOpen() && clip.getMicrosecondPosition() < clip.getMicrosecondLength()) {
                    double percentagePlayed = (double) clip.getMicrosecondPosition() / clip.getMicrosecondLength();
                    updateIndicator(percentagePlayed, line);
                    Thread.sleep(100);
                }
                updateIndicator(1.0, line);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        indicatorThread.start();
    }

    //Метод для оновлення позиції індикатора(лінії)
    private void updateIndicator(double percentage, Line line) {
        double width = line.getParent().getLayoutBounds().getWidth();
        double x = width * percentage;
        line.setEndX(x);
    }

}
