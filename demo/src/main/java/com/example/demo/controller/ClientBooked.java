package com.example.demo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class ClientBooked {

    @FXML
    private Button bookedButton;

    @FXML
    void onButtonClicked(ActionEvent event) {
        Node sourceButton = (Node) event.getSource();
        InternalBackController.getInternalBackControllerInstance().backToHome(sourceButton);
    }

}
