package com.example.demo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class AddBarberCenterController {


    @FXML
    public void onButtonClicked(ActionEvent event) throws IOException {

        Button sourceEvent = (Button) event.getSource();
        FXMLLoader fxmlLoader;
        if(sourceEvent.getId().equals("continueButton")){
            Scene myScene = (Scene) sourceEvent.getScene() ;
            fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("com/example/demo/barber_schedule.fxml"));
            BorderPane myBorderPane = (BorderPane) myScene.getRoot();
            myBorderPane.setCenter(fxmlLoader.load());
        }

    }
}
