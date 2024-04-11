//package com.example.demo1;//package com.example.demo1;//package com.example.demo1;
//////
//////import javafx.application.Application;
//////import javafx.event.ActionEvent;
//////import javafx.event.EventHandler;
//////import javafx.scene.Scene;
//////import javafx.scene.image.Image;
//////import javafx.scene.image.ImageView;
//////import javafx.scene.layout.StackPane;
//////import javafx.stage.Stage;
//////
//////public class Main extends Application {
//////
//////    @Override
//////    public void start(Stage primaryStage) {
//////        primaryStage.setTitle("ImageButton Example");
//////
//////        // Завантажуємо зображення для кнопки
//////        Image image = new Image("img/stop-button.png");
//////
//////        // Створюємо ImageView для зображення
//////        ImageView imageView = new ImageView(image);
//////
//////        // Створюємо кнопку і встановлюємо її графічний контент як ImageView
//////        javafx.scene.control.Button button = new javafx.scene.control.Button();
//////        button.setGraphic(imageView);
//////
//////        // Встановлюємо дію, яка виконується при натисканні кнопки
//////        button.setOnAction(new EventHandler<ActionEvent>() {
//////            @Override
//////            public void handle(ActionEvent event) {
//////                // Додайте код дії, яку ви хочете виконати при натисканні кнопки
//////                System.out.println("Button clicked!");
//////            }
//////        });
//////
//////        StackPane root = new StackPane();
//////        root.getChildren().add(button);
//////        primaryStage.setScene(new Scene(root, 300, 250));
//////        primaryStage.show();
//////    }
//////
//////    public static void main(String[] args) {
//////        launch(args);
//////    }
//////}
////import javafx.application.Application;
////import javafx.scene.Scene;
////import javafx.scene.control.Button;
////import javafx.scene.layout.StackPane;
////import javafx.stage.Stage;
////
////public class Main extends Application {
////
////    @Override
////    public void start(Stage primaryStage) {
////        primaryStage.setTitle("Button without Border Example");
////
////        // Створюємо кнопку
////        Button button = new Button("Click me");
////
////        // Встановлюємо стиль кнопки, щоб прибрати рамку
////        button.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
////
////        StackPane root = new StackPane();
////        root.getChildren().add(button);
////        primaryStage.setScene(new Scene(root, 300, 250));
////        primaryStage.show();
////    }
////
////    public static void main(String[] args) {
////        launch(args);
////    }
////}
//
//import javafx.application.Application;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.StackPane;
//import javafx.stage.Stage;
//
//public class Main extends Application {
//
//    @Override
//    public void start(Stage primaryStage) {
//        primaryStage.setTitle("ImageButton Example");
//
//        // Завантажуємо зображення для кнопки
//        Image image = new Image("img/stop-button.png");
//
//        // Створюємо ImageView для зображення
//        ImageView imageView = new ImageView(image);
//
//        // Створюємо кнопку і встановлюємо її графічний контент як ImageView
//        Button button = new Button();
//        button.setGraphic(imageView);
//
//        // Встановлюємо стиль кнопки, щоб прибрати рамку
//        button.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
//
//        // Встановлюємо дію, яка виконується при натисканні кнопки
//        button.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                // Додайте код дії, яку ви хочете виконати при натисканні кнопки
//                System.out.println("Button clicked!");
//            }
//        });
//
//        StackPane root = new StackPane();
//        root.getChildren().add(button);
//        primaryStage.setScene(new Scene(root, 300, 250));
//        primaryStage.show();
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}
package com.example.demo1;

import com.example.demo1.service.NoteDBService;

public class Main {
    public static void main(String[] args) {
        NoteDBService service = new NoteDBService();

        service.clearData();





    }

}
