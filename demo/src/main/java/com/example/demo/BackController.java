package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BackController {

    private static BackController backController ;
    private String prevSceneName ;

    private FXMLLoader root ;
    private Stage stage ;
    private Scene scene ;

    private BackController() {
        prevSceneName = "com/example/demo/open_screen.fxml" ;
    }

    @FXML
    public void onBackClick(ActionEvent event) throws IOException {
        root = new FXMLLoader(getClass().getClassLoader().getResource(prevSceneName)) ;
        stage = (Stage)((Node)event.getSource()).getScene().getWindow() ;
        scene = new Scene(root.load()) ;
        stage.setScene(scene) ;
    }

    public static BackController getInstance() {
        if (backController == null) {
            backController = new BackController() ;
        }
        return backController ;
    }

    public void setPrevScene(String prevSceneName) {
        this.prevSceneName = prevSceneName ;
    }

    public String getPrevSceneName() {
        return this.prevSceneName ;
    }
}
