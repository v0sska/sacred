package com.example.demo1;

import com.example.demo1.controllers.NoteController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NoteForm extends Application {

   private String path;

   private String name;

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(NoteForm.class.getResource("noteForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 440);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        NoteController noteController = fxmlLoader.getController();
        noteController.setPath(path);
        noteController.setName(name);
        noteController.openNote(path, name);
        noteController.viewAudio();
        noteController.setDrop(name);


    }
}

