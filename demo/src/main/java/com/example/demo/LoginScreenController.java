package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginScreenController {

    private FXMLLoader root;
    private Stage stage;
    private Scene scene;


    @FXML
    public void onBackClicked(ActionEvent event) throws IOException {
        BackController.getInstance().onBackClick(event);
    }

    @FXML
    public void onRegisterButtonClicked(ActionEvent event) throws IOException {
        BackController.getInstance().pushPrevScene("com/example/demo/login_screen.fxml");
        root = new FXMLLoader(getClass().getClassLoader().getResource("com/example/demo/register_screen.fxml")) ;
        stage = (Stage)((Node)event.getSource()).getScene().getWindow() ;
        scene = new Scene(root.load()) ;
        stage.setScene(scene) ;
    }
}
