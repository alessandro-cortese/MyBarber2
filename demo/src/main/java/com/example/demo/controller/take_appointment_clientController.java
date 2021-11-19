package com.example.demo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class take_appointment_clientController {

    @FXML
    private Button saloonNextButton;

    @FXML
    private Tab saloonTab;

    @FXML
    private Button treatmentNextButton;

    @FXML
    private Tab treatmentTab;

    @FXML
    public void onButtonSaloonClicked(ActionEvent actionEvent) throws IOException {
        Button sourceButton = (Button) actionEvent.getSource();
        FXMLLoader node = new FXMLLoader(getClass().getClassLoader().getResource("com/example/demo/take_saloon.fxml"));
        Scene myScene = (Scene) sourceButton.getScene();
        BorderPane borderPane = (BorderPane) myScene.getRoot();
        borderPane.setCenter(node.load());

    }
}
