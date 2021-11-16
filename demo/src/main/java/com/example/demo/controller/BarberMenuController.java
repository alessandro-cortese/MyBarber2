package com.example.demo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class BarberMenuController {

    @FXML
    private MenuBar barberMenuBar ;

    private static final String ADD_BARBER_SCREEN_NAME = "com/example/demo/add_center.fxml" ;
    private final String BARBER_HOME_SCREEN_NAME = "com/example/demo/barber_home.fxml" ;

    @FXML
    public void onButtonClicked(ActionEvent event) throws IOException {
        Node sourceNode = (Node) event.getSource() ;
        BorderPane clientBorderPane = (BorderPane) barberMenuBar.getScene().getRoot();
        if (sourceNode.getId().equals("logoutButton")) {
            BackController.getInstance().onBackClick((Node) event.getSource());
        }else{
            FXMLLoader clientHomeScreen = new FXMLLoader(getClass().getClassLoader().getResource(BARBER_HOME_SCREEN_NAME));
            clientBorderPane.setCenter(clientHomeScreen.load());
        }
    }

    @FXML
    public void onMenuItemSelected(ActionEvent event) throws IOException {
        MenuItem sourceItem = (MenuItem) event.getSource() ;
        BorderPane barberBorderPane = (BorderPane) barberMenuBar.getScene().getRoot() ;
        Parent newCenterNode = null ;
        if("addCenter".equals(sourceItem.getId())) {
            newCenterNode = (new FXMLLoader(getClass().getClassLoader().getResource(ADD_BARBER_SCREEN_NAME))).load();
        }

        barberBorderPane.setCenter(newCenterNode);

    }

}
