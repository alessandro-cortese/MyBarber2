package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader root = new FXMLLoader(getClass().getClassLoader().getResource("com/example/demo/open_screen.fxml")) ;
        Scene scene = new Scene(root.load());
        Image icon = new Image(new File("images/AppLogo_icon.png").toURI().toString());
        stage.getIcons().add(icon);

        stage.setResizable(false);

        stage.setTitle("MyBarber");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}