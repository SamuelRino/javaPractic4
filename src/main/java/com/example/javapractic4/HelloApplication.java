package com.example.javapractic4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 410, 510);
        stage.setTitle("Практическая 4");
        stage.setScene(scene);
        stage.setMinWidth(400);  // Минимальная ширина
        stage.setMinHeight(500); // Минимальная высота
        stage.show();
    }
}
