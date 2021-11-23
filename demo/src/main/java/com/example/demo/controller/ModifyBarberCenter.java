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
    @FXML private Button modifyCenterSchedule;

    private final static String HOME_BARBER_SCREEN_NAME = "com/example/demo/barber_home.fxml" ;
    private final static String BARBER_SCHEDULE_SCREEN_NAME = "com/example/demo/barber_schedule.fxml" ;

    @FXML
    public void onButtonClicked(ActionEvent event) throws IOException {
        Button sourceButton = (Button) event.getSource();
        if(sourceButton == saveButton){
            this.changeBorderPane(sourceButton.getScene(), HOME_BARBER_SCREEN_NAME);
        }
        else if( sourceButton == modifyCenterSchedule){
            this.changeBorderPane(sourceButton.getScene(), BARBER_SCHEDULE_SCREEN_NAME);
        }
    }

    private void changeBorderPane(Scene scene, String string) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(string));
        BorderPane myBorderPane = (BorderPane) scene.getRoot();
        myBorderPane.setCenter(fxmlLoader.load());
    }

}
