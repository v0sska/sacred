package com.example.demo1.service;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class StyleService {
    public void transitionStart(Button button) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), button);
        scaleTransition.setToX(1.2);
        scaleTransition.setToY(1.2);
        scaleTransition.play();
    }

    public void transitionFinish(Button button) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100),button);
        scaleTransition.setToX(1);
        scaleTransition.setToY(1);
        scaleTransition.play();
    }

    public void transitionFinish(ImageView view) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100),view);
        scaleTransition.setToX(1);
        scaleTransition.setToY(1);
        scaleTransition.play();
    }

    public void transitionStart(ImageView view) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), view);
        scaleTransition.setToX(1.2);
        scaleTransition.setToY(1.2);
        scaleTransition.play();
    }



    public void fadeInLabel(Label label) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), label);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
    }

}
