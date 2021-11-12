package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterScreenController {

    private FXMLLoader root;
    private Stage stage;
    private Scene scene;


    @FXML
    public void onBackClicked(ActionEvent event) throws IOException {
        BackController.getInstance().onBackClick(event);
    }
}
