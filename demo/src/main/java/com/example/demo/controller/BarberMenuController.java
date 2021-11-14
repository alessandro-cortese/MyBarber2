package com.example.demo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;

import java.io.IOException;

public class BarberMenuController {

    @FXML
    public void onButtonClicked(ActionEvent event) throws IOException {
        Node sourceNode = (Node) event.getSource() ;
        if (sourceNode.getId().equals("logoutButton")) {
            BackController.getInstance().onBackClick((Node) event.getSource());
        }
    }
}
