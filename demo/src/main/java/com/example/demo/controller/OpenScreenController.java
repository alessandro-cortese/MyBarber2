package com.example.demo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class OpenScreenController {

    private FXMLLoader root;
    private Stage stage;
    private Scene scene;

    @FXML
    public void onStartClick(ActionEvent event) throws IOException {
        //Aggiunta sul BackStack
        Node sourceNode = (Node) event.getSource() ;
        BackController backController = BackController.getInstance() ;
        backController.pushPrevScene(sourceNode.getScene());

        //Cambio Scena
        root = new FXMLLoader(getClass().getClassLoader().getResource("com/example/demo/login_screen.fxml")) ;
        stage = (Stage)(sourceNode.getScene().getWindow()) ;
        scene = new Scene(root.load()) ;
        stage.setScene(scene) ;
    }
}