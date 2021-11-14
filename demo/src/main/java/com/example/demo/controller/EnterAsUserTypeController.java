package com.example.demo.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class EnterAsUserTypeController {

    public static final int CLIENT_TYPE = 0 ;
    public static final int BARBER_TYPE = 1 ;

    public static final String BARBER_HOME_SCREEN = "com/example/demo/barber_home.fxml" ;
    public static final String CLIENT_HOME_SCREEN = "com/example/demo/client_home.fxml" ;

    public static final String CLIENT_SCREEN_CHANGER = "clientScreenChanger" ;
    public static final String BARBER_SCREEN_CHANGER = "barberScreenChanger" ;

    public void enterAsUser(int userType, Scene mainScene) throws IOException {
        VBox container ;
        String mainScreen ;
        if (userType == CLIENT_TYPE) {
            container = (VBox) mainScene.lookup("#" + CLIENT_SCREEN_CHANGER) ;
            mainScreen = CLIENT_HOME_SCREEN ;
        }
        else {
            container = (VBox) mainScene.lookup("#" + BARBER_SCREEN_CHANGER) ;
            mainScreen = BARBER_HOME_SCREEN ;
        }
        if (container != null) {
            FXMLLoader userHomeScreen = new FXMLLoader(getClass().getClassLoader().getResource(mainScreen)) ;
            container.getChildren().add(userHomeScreen.load()) ;
        }
    }
}
