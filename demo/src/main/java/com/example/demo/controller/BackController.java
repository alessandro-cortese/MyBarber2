package com.example.demo.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class BackController {

    private static BackController backController ;
    private final ArrayList<String> prevSceneStack;

    private FXMLLoader root ;
    private Stage stage ;
    private Scene scene ;

    private BackController() {
        prevSceneStack = new ArrayList<>() ;
        prevSceneStack.add("com/example/demo/open_screen.fxml") ;
    }

    @FXML
    public void onBackClick(Node sourceNode) throws IOException {
        root = new FXMLLoader(getClass().getClassLoader().getResource(prevSceneStack.remove(prevSceneStack.size() - 1))) ;
        stage = (Stage)(sourceNode).getScene().getWindow() ;
        scene = new Scene(root.load()) ;
        stage.setScene(scene) ;
    }

    public static BackController getInstance() {
        if (backController == null) {
            backController = new BackController() ;
        }
        return backController ;
    }

    public void pushPrevScene(String prevSceneName) {
        this.prevSceneStack.add(prevSceneName) ;
    }
}
