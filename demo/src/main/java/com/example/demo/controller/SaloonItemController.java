package com.example.demo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class SaloonItemController {

    @FXML
    private Button saloonButton;

    @FXML
    void onButtonClicked(ActionEvent event) throws IOException {
        Button sourceButton = (Button) event.getSource();
        FXMLLoader node = new FXMLLoader(getClass().getClassLoader().getResource("com/example/demo/client_saloon_center.fxml"));
        Scene myScene = (Scene) sourceButton.getScene();
        BorderPane borderPane = (BorderPane) myScene.getRoot();
        borderPane.setCenter(node.load());

    }

}
