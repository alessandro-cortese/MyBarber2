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

    @FXML MenuItem userAreaItem ;
    @FXML MenuItem addCenterItem;
    @FXML MenuItem manageProductsItem ;


    private static final String ADD_BARBER_SCREEN_NAME = "com/example/demo/add_center.fxml" ;
    private final String BARBER_HOME_SCREEN_NAME = "com/example/demo/barber_home.fxml" ;
    private static final String BARBER_ADD_PRODUCT_SCREEN_NAME = "com/example/demo/barber_add_product.fxml" ;

    private final String USER_AREA_SCREEN_NAME = "com/example/demo/user_area.fxml" ;



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
        Parent newCenterNode = switch (sourceItem.getId()) {
            case "addCenter" -> (new FXMLLoader(getClass().getClassLoader().getResource(ADD_BARBER_SCREEN_NAME))).load();
            case "userAreaItem" -> (new FXMLLoader(getClass().getClassLoader().getResource(USER_AREA_SCREEN_NAME))).load();
            case "manageProductsItem" -> (new FXMLLoader(getClass().getClassLoader().getResource(BARBER_ADD_PRODUCT_SCREEN_NAME))).load();
            default -> null;
        };

        barberBorderPane.setCenter(newCenterNode);

    }


}
