package com.example.demo1.controllers;

import com.example.demo1.NoteForm;
import com.example.demo1.entity.Note;
import com.example.demo1.interfaces.INoteDBService;
import com.example.demo1.service.NoteDBService;
import com.example.demo1.service.StyleService;
import com.mysql.cj.protocol.x.XProtocolRow;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class WelcomeController {

    @FXML
   private Label writeNoteButton;

    @FXML
    private ImageView badRates;

    @FXML
    private ImageView folder;

    @FXML
    private ImageView normalRates;

    @FXML
    private ImageView goodRates;

    private StyleService  styleService = new StyleService();

    @FXML
   private Label goodRateLabel;

    @FXML
    private Label normalRateLabel;

    @FXML
    private Label badRateLabel;

    private String mainPath;

    @FXML
    private Button createButton;

   @FXML
   private AnchorPane anchorRoot;

   private GridPane gridPane;

  // private NoteDBService service = new NoteDBService();

    private INoteDBService service = new NoteDBService();

   @FXML
   private Line bottomLine;


    public void initialize(){
        long goodCount = service.getCountByGoodRate();
        long normalCount = service.getCountByNormalRate();
        long badCount = service.getCountByBadRate();

        goodRateLabel.setText(String.valueOf(goodCount));
        normalRateLabel.setText(String.valueOf(normalCount));
        badRateLabel.setText(String.valueOf(badCount));
    }

    @FXML
   public void openNoteForm() {
        nameForm();
    }


    @FXML
    public void chooseDirectory(MouseEvent mouseEvent) {
        // Створюємо об'єкт DirectoryChooser
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Виберіть папку");

        // Відображаємо діалогове вікно для вибору папки
        File selectedDirectory = directoryChooser.showDialog(((ImageView) mouseEvent.getSource()).getScene().getWindow());
        mainPath = selectedDirectory.getAbsolutePath();
        // Обробка вибраної папки
        if (selectedDirectory != null) {
            System.out.println("Вибрано папку: " + selectedDirectory.getAbsolutePath());
        } else {
            System.out.println("Папка не вибрана.");
        }
    }

    private void nameForm(){
        Stage primaryStage = new Stage();

        AnchorPane root = new AnchorPane();

        // Створюємо текстове поле для введення
        TextField textField = new TextField();
        textField.setPromptText("Enter a name of note");
        textField.setLayoutX(52);
        textField.setLayoutY(24);
        textField.setPrefWidth(197);
        textField.setPrefHeight(26);

        // Створюємо кнопку "Підтвердити"
        Button button = new Button("Confirm");
        button.setLayoutX(119);
        button.setLayoutY(58);
        button.setPrefWidth(62);
        button.setPrefHeight(26);
        button.setStyle("-fx-background-color: #686f7a; -fx-border-color: white;");
        button.setTextFill(Color.WHITE);

        // Обробник події для кнопки "Підтвердити"
        button.setOnAction(event -> {
            String name = textField.getText();

            NoteForm noteForm = new NoteForm();

            noteForm.setPath(mainPath); //Передача шляху до NoteForm
            noteForm.setName(name); // Передача імені до NoteForm

            Stage stage = new Stage();

            try {
                noteForm.start(stage);
                primaryStage.close();
                ((Stage) writeNoteButton.getScene().getWindow()).close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            // Тут можна виконати дії зі зчитаним текстом
            System.out.println("Введено ім'я: " + name);
        });

        // Задаємо колір AnchorPane
        root.setStyle("-fx-background-color: #686f7a;");

        // Додаємо елементи у кореневий контейнер
        root.getChildren().addAll(textField, button);

        // Створюємо сцену і встановлюємо її для вікна
        Scene scene = new Scene(root, 300, 100);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX Form");
        primaryStage.show();
    }

    public void addButtons(List<String> notes) {

        int columnCount = 6; // Кількість стовпців у сітці

        int elementsPerColumn = 3; // Кількість елементів у кожній колонці

        int rowCount = (notes.size() + elementsPerColumn - 1) / elementsPerColumn; // Кількість рядків у сітці

        gridPane = new GridPane();
        gridPane.setVgap(10); // Відстань між рядками
        gridPane.setHgap(10); // Відстань між стовпцями


        int index = 0;
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                if (index < notes.size()) {
                    String noteName = notes.get(index);

                    Button button = new Button(noteName);

                    button.setStyle("-fx-background-color: #686f7a; -fx-border-color: white;");
                    button.setTextFill(Color.WHITE);
                   // button.setUserData("dynamicButton"); // Встановлення userData для динамічних кнопок

                    button.setOnMouseEntered(event -> styleService.transitionStart(button));
                    button.setOnAction(event -> {
                        NoteForm noteForm = new NoteForm();
                        String name = button.getText();
                        String path = service.getNotePathByName(name);
                        noteForm.setPath(path);
                        noteForm.setName(name);

                        Stage stage = new Stage();

                        try {
                            noteForm.start(stage);
                            ((Stage) writeNoteButton.getScene().getWindow()).close();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }

                        System.out.println(path);
                    });

                    button.setOnMouseExited(event -> styleService.transitionFinish(button));
                    gridPane.add(button, j, i); // Додати кнопку до сітки
                    index++;
                } else {
                    break; // Вийти з циклу, якщо досягнуто кількість записів
                }
            }
        }

        anchorRoot.setTopAnchor(gridPane, 65.0); // 50.0 - відстань від верхнього краю
        anchorRoot.setLeftAnchor(gridPane, 10.0);

        anchorRoot.getChildren().add(gridPane); // Додайте сітку до AnchorPane
    }

    public void showBadRateNotes(MouseEvent mouseEvent) {
        List<String> badRateNotes = service.getNoteNameByBadRate();

        anchorRoot.getChildren().remove(gridPane);

        addButtons(badRateNotes);
    }

    public void showGoodRatesNotes(MouseEvent mouseEvent) {

        List<String> goodRateNotes = service.getNoteNameByGoodRate();

        anchorRoot.getChildren().remove(gridPane);

        addButtons(goodRateNotes);
    }

    public void showNormalRateNotes(MouseEvent mouseEvent) {
        List<String> normalRateNotes = service.getNoteNameByNormalRate();

        anchorRoot.getChildren().remove(gridPane);

        addButtons(normalRateNotes);
    }

    //Наступні методи це створення анімацій для кнопок
    public void goodRatesAnimationEnd(MouseEvent mouseEvent) {
        styleService.transitionFinish(goodRates);
    }

    public void goodRatesAnimationStart(MouseEvent mouseEvent) {
        styleService.transitionStart(goodRates);
    }

    public void normalRatesAnimationStart(MouseEvent mouseEvent) {
        styleService.transitionStart(normalRates);
    }

    public void normalRatesAnimationEnd(MouseEvent mouseEvent) {
        styleService.transitionFinish(normalRates);
    }

    public void badRatesAnimationEnd(MouseEvent mouseEvent) {
        styleService.transitionFinish(badRates);
    }

    public void badRatesAnimationStart(MouseEvent mouseEvent) {
        styleService.transitionStart(badRates);
    }

    public void transitionStart(MouseEvent mouseEvent) {
        styleService.transitionStart(createButton);
    }

    public void transitionFinish(MouseEvent mouseEvent) {
        styleService.transitionFinish(createButton);
    }

    public void folderAnimationStart(MouseEvent mouseEvent) {
        styleService.transitionStart(folder);
    }

    public void folderAnimationEnd(MouseEvent mouseEvent) {
        styleService.transitionFinish(folder);
    }
}
