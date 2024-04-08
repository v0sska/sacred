package com.example.demo1.controllers;

import com.example.demo1.NoteForm;
import com.example.demo1.service.NoteDBService;
import com.example.demo1.service.StyleService;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;

public class NoteButtons{

//    public void addButtons(List<String> notes) {
//
//        int columnCount = 6; // Кількість стовпців у сітці
//
//        int elementsPerColumn = 3; // Кількість елементів у кожній колонці
//
//        int rowCount = (notes.size() + elementsPerColumn - 1) / elementsPerColumn; // Кількість рядків у сітці
//
//        gridPane = new GridPane();
//        gridPane.setVgap(10); // Відстань між рядками
//        gridPane.setHgap(10); // Відстань між стовпцями
//
//
//        int index = 0;
//        for (int i = 0; i < rowCount; i++) {
//            for (int j = 0; j < columnCount; j++) {
//                if (index < notes.size()) {
//                    String noteName = notes.get(index);
//
//                    Button button = new Button(noteName);
//
//                    button.setStyle("-fx-background-color: #686f7a; -fx-border-color: white;");
//                    button.setTextFill(Color.WHITE);
//                    button.setUserData("dynamicButton"); // Встановлення userData для динамічних кнопок
//
//                    button.setOnMouseEntered(event -> styleService.transitionStart(button));
//                    button.setOnAction(event -> {
//                        NoteForm noteForm = new NoteForm();
//                        String name = button.getText();
//                        String path = service.getNotePathByName(name);
//                        noteForm.setPath(path);
//                        noteForm.setName(name);
//
//                        Stage stage = new Stage();
//
//                        try {
//                            noteForm.start(stage);
//                            ((Stage) writeNoteButton.getScene().getWindow()).close();
//                        } catch (Exception e) {
//                            throw new RuntimeException(e);
//                        }
//
//                        System.out.println(path);
//                    });
//
//                    button.setOnMouseExited(event -> styleService.transitionFinish(button));
//                    gridPane.add(button, j, i); // Додати кнопку до сітки
//                    index++;
//                } else {
//                    break; // Вийти з циклу, якщо досягнуто кількість записів
//                }
//            }
//        }
//
//        anchorRoot.setTopAnchor(gridPane, 65.0); // 50.0 - відстань від верхнього краю
//        anchorRoot.setLeftAnchor(gridPane, 10.0);
//
//        anchorRoot.getChildren().add(gridPane); // Додайте сітку до AnchorPane
//    }
//
//    public void showNormalRates(){
//
//        anchorRoot.getChildren().remove(gridPane);
//    }

//    public void showNormalRateNotes(MouseEvent mouseEvent) {
//        List<String> normalRateNotes = service.getNoteNameByNormalRate();
//
//        anchorRoot.getChildren().remove(gridPane);
//
//        addButtons(normalRateNotes);
//    }


}
