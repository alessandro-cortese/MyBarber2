package com.example.demo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.demo.controller.EnterAsUserTypeController.BARBER_TYPE;
import static com.example.demo.controller.EnterAsUserTypeController.CLIENT_TYPE;

public class RegisterScreenController {

    private FXMLLoader root;
    private Stage stage;
    private Scene scene;

    private String THIS_SCENE_NAME = "com/example/demo/register_screen.fxml" ;

    public static final String CLIENT_MENU_SCREEN_NAME = "com/example/demo/client_menu.fxml" ;
    public static final String BARBER_MENU_SCREEN_NAME = "com/example/demo/barber_menu.fxml" ;

    @FXML
    private Button registerButton;

    @FXML
    private ToggleGroup userTypeRadioGroup;

    @FXML
    private RadioButton clientTypeRadioButton;

    @FXML
    private RadioButton barberTypeRadioButton;


    @FXML
    public void onBackClicked(ActionEvent event) throws IOException {
        BackController.getInstance().onBackClick((Node)event.getSource());
    }


    @FXML
    public void onButtonClicked(ActionEvent event) throws  IOException {
        Node sourceNode = (Node)event.getSource() ;
        if (sourceNode.getId().equals("registerButton")) {
            onRegisterButtonClicked(sourceNode) ;
        }
    }

    private void onRegisterButtonClicked(Node sourceNode) throws IOException {
        BackController.getInstance().pushPrevScene(THIS_SCENE_NAME);

        int userType = clientTypeRadioButton.isSelected() ? CLIENT_TYPE : BARBER_TYPE ;

        String nextSceneName = clientTypeRadioButton.isSelected() ? CLIENT_MENU_SCREEN_NAME : BARBER_MENU_SCREEN_NAME ;

        root = new FXMLLoader(getClass().getClassLoader().getResource(nextSceneName)) ;
        stage = (Stage)(sourceNode).getScene().getWindow() ;
        scene = new Scene(root.load()) ;
        (new EnterAsUserTypeController()).enterAsUser(userType, scene);
        stage.setScene(scene) ;
    }

}
