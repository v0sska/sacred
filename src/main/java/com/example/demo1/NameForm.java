package com.example.demo1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NameForm extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(NameForm.class.getResource("nameForm.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 300, 100);
        stage.setTitle("Sacred");
        stage.setScene(scene);
        stage.show();
    }
}
