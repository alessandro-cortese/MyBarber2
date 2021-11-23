package com.example.demo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class BarberAddCenterController {

    @FXML Button backButton ;
    @FXML Button continueButton ;

    @FXML
    public void onButtonClicked(ActionEvent event) throws IOException {

        Node sourceNode = (Node) event.getSource();
        FXMLLoader fxmlLoader;
        if(sourceNode == continueButton){
            InternalBackController.getInternalBackControllerInstance().onNextScreen(sourceNode);
            Scene myScene = sourceNode.getScene() ;
            fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("com/example/demo/barber_schedule.fxml"));
            BorderPane myBorderPane = (BorderPane) myScene.getRoot();
            myBorderPane.setCenter(fxmlLoader.load());


        }



    }
}
