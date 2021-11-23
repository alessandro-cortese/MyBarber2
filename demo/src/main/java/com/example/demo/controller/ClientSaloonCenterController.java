package com.example.demo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class ClientSaloonCenterController {

    private static final String CLIENT_BOOKED_SCREEN_NAME = "com/example/demo/client_booked.fxml" ;

    @FXML
    private Button bookedButton;


    @FXML
    void onButtonBookClicked(ActionEvent event) throws IOException {
        Node sourceNode = (Node) event.getSource();
        InternalBackController.getInternalBackControllerInstance().onNextScreen(sourceNode);
        FXMLLoader node = new FXMLLoader(getClass().getClassLoader().getResource(CLIENT_BOOKED_SCREEN_NAME));
        Scene myScene = sourceNode.getScene();
        BorderPane borderPane = (BorderPane) myScene.getRoot();
        borderPane.setCenter(node.load());
    }
}
