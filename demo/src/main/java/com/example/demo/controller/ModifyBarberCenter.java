package com.example.demo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class ModifyBarberCenter {

    @FXML private Button saveButton;

    private final String HOME_BARBER_SCREEN_NAME = "com/example/demo/barber_home.fxml";

    @FXML
    public void onButtonClicked(ActionEvent event) throws IOException {
        Button sourceButton = (Button) event.getSource();
        FXMLLoader fxmlLoader;
        if(sourceButton == saveButton){
            this.changeBorderPane(sourceButton.getScene(), HOME_BARBER_SCREEN_NAME);
        }
    }

    private void changeBorderPane(Scene scene, String string) throws IOException {
        Scene localScene = scene;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(string));
        BorderPane myBorderPane = (BorderPane) localScene.getRoot();
        myBorderPane.setCenter(fxmlLoader.load());
    }

}
