package com.example.demo1;

import javafx.animation.FillTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class BB extends Application {

    @Override
    public void start(Stage primaryStage) {
        Button button = new Button("Click me");

        // Встановлення початкового та кінцевого кольорів
        Color startColor = Color.web("#336699");
        Color endColor = Color.web("#6699CC");

        // Створення перехіду зміни кольору
        FillTransition fillTransition = new FillTransition(Duration.millis(200), button.getShape(), startColor, endColor);

        // Встановлення обробників подій для перехіду
        button.setOnMouseEntered(event -> fillTransition.playFromStart());
        button.setOnMouseExited(event -> fillTransition.playFrom(Duration.ONE));

        StackPane root = new StackPane();
        root.getChildren().add(button);
        primaryStage.setScene(new Scene(root, 200, 100));
        primaryStage.setTitle("Hover and Transition Example");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
