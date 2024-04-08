package com.example.demo1;

import com.example.demo1.controllers.AudioRecordController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RecordAudioForm extends Application {

    private String path;

    private AudioRecordController controller;

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(RecordAudioForm.class.getResource("audioRecordForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 357, 66);
        stage.setTitle("Recording audio");
        stage.setScene(scene);

        // Отримуємо контролер з FXML і передаємо його класу, який його очікує
        controller = fxmlLoader.getController();
        controller.setPath(path);
        controller.record(); // Викликаємо метод record() контролера

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
