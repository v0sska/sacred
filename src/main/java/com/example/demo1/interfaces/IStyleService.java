package com.example.demo1.interfaces;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public interface IStyleService {

    void transitionStart(Button button);

    void transitionFinish(Button button);

    void transitionFinish(ImageView view);

    void transitionStart(ImageView view);

    void fadeInLabel(Label label);

}
