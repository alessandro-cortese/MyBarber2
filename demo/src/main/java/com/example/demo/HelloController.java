package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class HelloController {
    private FXMLLoader root;
    private Stage stage;
    private Scene scene;

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void switchToRegister(ActionEvent event) throws Exception{
        root = new FXMLLoader(getClass().getClassLoader().getResource("com/example/demo/register_screen.fxml")) ;
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root.load());
        stage.setScene(scene);
        stage.show();
    }
}