package com.example.demo1;

import com.example.demo1.controllers.WelcomeController;
import com.example.demo1.interfaces.INoteDBService;
import com.example.demo1.service.NoteDBService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.List;


public class WelcomeForm extends Application {

    private INoteDBService service = new NoteDBService();

    private List<String> notes = service.getNoteName();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(WelcomeForm.class.getResource("welcome.fxml"));
        Parent root = fxmlLoader.load();

        WelcomeController controller = fxmlLoader.getController();

        // Додаємо кнопки до GridPane через контролер
        controller.addButtons(notes);

        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Sacred");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}