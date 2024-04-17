package com.example.demo1.controllers;

import com.example.demo1.HelloApplication;
import com.example.demo1.entity.Rate;
import com.example.demo1.RecordAudioForm;
import com.example.demo1.entity.Note;
import com.example.demo1.interfaces.INoteDBService;
import com.example.demo1.interfaces.IReader;
import com.example.demo1.interfaces.IStyleService;
import com.example.demo1.service.AudioService;
import com.example.demo1.service.NoteDBService;
import com.example.demo1.service.Reader;
import com.example.demo1.service.StyleService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import javax.sound.sampled.*;
import java.io.*;

public class NoteController {

    @FXML
    private TextArea textArea;

    @FXML
    private ImageView imageDrop;


    @FXML
    private ImageView badRates;

    @FXML
    private ImageView microphone;

    @FXML
    private ImageView normalRates;

    @FXML
    private ImageView goodRates;

    @FXML
    private ImageView arrow;

    private Note noteEntity = new Note();

    @FXML
    private Label saveNoteLabel;

    private IStyleService styleService = new StyleService();

    @FXML
    private AnchorPane anchorPane;

    private Line indicatorLine;

    private RecordAudioForm audioForm = new RecordAudioForm();

    private IReader reader = new Reader();

    private INoteDBService service = new NoteDBService();

    private String name;
    private String path;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void initialize() {
        // Встановлення коліру фону для TextArea
        textArea.setStyle("-fx-control-inner-background: #686f7a;");
        textArea.setWrapText(true);
        saveNoteLabel.setOpacity(0.0);

    }


    public void writeButton(ActionEvent actionEvent) throws IOException {

        String note = textArea.getText();

        String mainPath = getPath();

        String notesMainPath = reader.createMainDirectory(mainPath); //Метод для створення до нотатки папки sacred для простішого знаходження

        String readFile = Reader.readConfigMemoryFile(); //Метод для зчитування шляху з файлу

        String noteName = getName();

        String notePath = "";

        if (readFile != null && !readFile.isEmpty()) {
            notesMainPath = readFile; // Призначення основної папки,якщо існує документ з шляхом
            notePath = reader.createNoteDirectory(notesMainPath, name);//Метод,що додає до імені запису папку для простішої індексації
        } else {
            try {
                reader.saveConfigMemoryFile(notesMainPath);// метод для збереження в памʼяті основного шляху до папки
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println(mainPath);

        //Перевірка чи ця нотатка відкривалась з головного меню чи створювалась
        if (service.noteExists(path)) {
            notePath = reader.createNoteDirectory(path, name);
            reader.writeAndSave(note, name, notePath);
            styleService.fadeInLabel(saveNoteLabel);
        } else {
            reader.writeAndSave(note, noteName, notePath);
            audioForm.setPath(notePath);

            noteEntity.setName(noteName);
            noteEntity.setPath(notesMainPath);

            service.saveNote(noteEntity); //збереження шляху і назви до бази даних
            styleService.fadeInLabel(saveNoteLabel);
        }
    }

    public void setDrop(String name) {
        imageDrop.setImage(new Image(service.getImagePathByName(name)));
    }

    //Метод для прослуховування аудіо
    public void viewAudio() {

        File audioFile = new File(path + "/" + name + "/sacred_audio.wav"); // аудіо файл
        if (audioFile.exists()) { //Перевірка чи існує аудіо файл і якщо він існує то тоді показується блок з аудіо
            Pane pane = new Pane();

            pane.setLayoutX(35);
            pane.setLayoutY(260);
            pane.setPrefHeight(48);
            pane.setPrefWidth(200);

           // ImageView playButton = new ImageView(new Image("src/main/resources/img/play-button.png"));

            Button playButton = new Button("Play audio");

            playButton.setLayoutX(55);
            playButton.setLayoutY(21);
            playButton.setStyle("-fx-background-color: #686f7a; -fx-border-color: white;");
            playButton.setTextFill(Color.WHITE);


            playButton.setOnMouseEntered(event -> styleService.transitionStart(playButton));

            playButton.setOnAction(e -> {
                try {
                    AudioService audioService = new AudioService();
                    audioService.playAudio(audioFile, indicatorLine);
                    indicatorLine.setVisible(true);
                } catch (IOException | UnsupportedAudioFileException | LineUnavailableException ex) {
                    ex.printStackTrace();
                }
            });

            playButton.setOnMouseExited(event -> styleService.transitionFinish(playButton));

            indicatorLine = new Line(-100, 14, 100, 14);
            indicatorLine.setStroke(Color.WHITE);
            indicatorLine.setVisible(false);


            pane.getChildren().addAll(playButton, indicatorLine);
            anchorPane.getChildren().add(pane);
        } else {
            System.out.println("Файл не існує");
        }


    }

    //Метод для відкриття нотатки
    public void openNote(String path, String name) {
        reader.openNote(path, name, textArea);
    }

    //Метод який відкриває RecordAudioForm
    public void recordAudio(MouseEvent mouseEvent) throws Exception {
        Stage stage = new Stage();
        audioForm.start(stage);
    }


    //Методи для оцінювання нотаток
    public void badRateClick(MouseEvent mouseEvent) {
        noteEntity.setRate(Rate.BAD.name());
    }

    public void normalRateClick(MouseEvent mouseEvent) {
        noteEntity.setRate(Rate.NORMAL.name());
    }

    public void goodRateClick(MouseEvent mouseEvent) {
        noteEntity.setRate(Rate.GOOD.name());
    }

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

    public void arrowAnimationStart(MouseEvent mouseEvent) {
        styleService.transitionStart(arrow);
    }

    public void backToMainForm(MouseEvent mouseEvent) throws IOException {
        HelloApplication application = new HelloApplication();

        Stage stage = new Stage();

        application.start(stage);

        ((Stage) arrow.getScene().getWindow()).close();
    }

    public void arrowAnimationEnd(MouseEvent mouseEvent) {
        styleService.transitionFinish(arrow);
    }

    public void micAnimationStart(MouseEvent mouseEvent) {
        styleService.transitionStart(microphone);
    }

    public void micAnimationEnd(MouseEvent mouseEvent) {
        styleService.transitionFinish(microphone);
    }

    public void imageDrop(DragEvent dragEvent) {
        Dragboard dragboard = dragEvent.getDragboard();
        if (dragboard.hasImage() || dragboard.hasFiles()) {
            try {
                // Отримуємо перший файл з перетягування
                File imageFile = dragboard.getFiles().get(0);
                // Отримуємо повний шлях до файлу
                String imageDropPath = imageFile.toURI().toURL().toExternalForm();

                imageDrop.setImage(new Image(imageDropPath));
                noteEntity.setImagePath(imageDropPath);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        dragEvent.consume();
    }

    public void imageDropped(DragEvent dragEvent) {
        Dragboard dragboard = dragEvent.getDragboard();

        if (dragboard.hasImage() || dragboard.hasFiles()) {
            dragEvent.acceptTransferModes(TransferMode.COPY);
        }

        dragEvent.consume();
    }

    public void setTextLimit() {
        textArea.textProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue.length() > 1130) {
                textArea.setText(oldValue);
            }
        });
    }

}
