package com.example.demo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class BarberModifyCenter {

    @FXML private Button saveButton;
    @FXML private Button modifyCenterSchedule;

    private static final String HOME_BARBER_SCREEN_NAME = "com/example/demo/barber_home.fxml" ;
    private static final String BARBER_SCHEDULE_SCREEN_NAME = "com/example/demo/barber_schedule.fxml" ;

    @FXML
    public void onButtonClicked(ActionEvent event) throws IOException {
        Node sourceNode = (Node) event.getSource();
        String nextScreenName ;
        if(sourceNode == saveButton){
            nextScreenName = HOME_BARBER_SCREEN_NAME ;
        }
        else if( sourceNode == modifyCenterSchedule){
            nextScreenName = BARBER_SCHEDULE_SCREEN_NAME ;
        }
        else {
            nextScreenName = null ;
        }
        if (nextScreenName != null) {
            InternalBackController.getInternalBackControllerInstance().onNextScreen(sourceNode);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(nextScreenName));
            BorderPane myBorderPane = (BorderPane) sourceNode.getScene().getRoot();
            myBorderPane.setCenter(fxmlLoader.load());
        }
    }

    private void changeBorderPane(Scene scene, String string) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(string));
        BorderPane myBorderPane = (BorderPane) scene.getRoot();
        myBorderPane.setCenter(fxmlLoader.load());
    }

}
