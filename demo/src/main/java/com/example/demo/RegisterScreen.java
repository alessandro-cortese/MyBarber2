package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterScreen {

    @FXML
    public void onBackPressed(ActionEvent event) throws IOException {
            FXMLLoader nextScene = new FXMLLoader(getClass().getClassLoader().getResource("com/example/demo/open_screen.fxml")) ;
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(nextScene.load());
            stage.setScene(scene);
            stage.show();
    }
}
