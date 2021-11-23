package com.example.demo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class SaloonItemController {

    private static final String CLIENT_SALOON_CENTER_SCREEN_NAME = "com/example/demo/client_saloon_center.fxml" ;

    @FXML
    private Button saloonButton;

    @FXML
    void onButtonClicked(ActionEvent event) throws IOException {
        Node sourceButton = (Node) event.getSource();
        InternalBackController.getInternalBackControllerInstance().onNextScreen(sourceButton);
        FXMLLoader node = new FXMLLoader(getClass().getClassLoader().getResource(CLIENT_SALOON_CENTER_SCREEN_NAME));
        Scene myScene =  sourceButton.getScene();
        BorderPane borderPane = (BorderPane) myScene.getRoot();
        borderPane.setCenter(node.load());
    }

}
