package com.example.demo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.demo.controller.RegisterScreenController.CLIENT_MENU_SCREEN_NAME;

public class LoginScreenController {

    private FXMLLoader root;
    private Stage stage;
    private Scene scene;

    private final String THIS_SCENE_NAME = "com/example/demo/login_screen.fxml" ;
    private final String REGISTER_SCREEN_NAME = "com/example/demo/register_screen.fxml" ;

    @FXML Button loginButton ;
    @FXML Button registerButton ;


    @FXML
    public void onBackClicked(ActionEvent event) throws IOException {
        BackController.getInstance().onBackClick((Node)event.getSource());
    }


    @FXML
    public void onButtonClicked(ActionEvent event) throws  IOException {
        BackController.getInstance().pushPrevScene(THIS_SCENE_NAME);
        Node eventSource = (Node)event.getSource() ;
        String nextSceneName ;
        nextSceneName = switch (eventSource.getId()) {
            case "registerButton" ->  REGISTER_SCREEN_NAME;
            case "loginButton" -> CLIENT_MENU_SCREEN_NAME ;
            default -> THIS_SCENE_NAME ;
        } ;

        root = new FXMLLoader(getClass().getClassLoader().getResource(nextSceneName)) ;
        stage = (Stage)(eventSource).getScene().getWindow() ;
        scene = new Scene(root.load()) ;
        (new EnterAsUserTypeController()).enterAsUser(EnterAsUserTypeController.CLIENT_TYPE, scene);
        stage.setScene(scene) ;
    }

}
