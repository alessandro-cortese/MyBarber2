package com.example.demo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class BarberMenuController {

    @FXML
    private MenuBar barberMenuBar ;

    @FXML private MenuItem userAreaItem ;
    @FXML private MenuItem addCenterItem;
    @FXML private MenuItem manageProductsItem ;
    @FXML private MenuItem barberCenterItem;
    @FXML private MenuItem addServiceItem;
    @FXML private MenuItem listAppointmentsItem;
    @FXML private MenuItem historyAppointmentsItem;


    @FXML private Button logoutButton ;
    @FXML private Button homeButton ;
    @FXML private Button backButton ;


    private static final String ADD_BARBER_SCREEN_NAME = "com/example/demo/barber_add_center.fxml" ;
    private static final String BARBER_HOME_SCREEN_NAME = "com/example/demo/barber_home.fxml" ;
    private static final String BARBER_CENTER_SCREEN_NAME = "com/example/demo/barber_centers.fxml" ;
    private static final String ADD_SERVICE_BARBER_SCREEN_NAME = "com/example/demo/barber_add_service.fxml" ;
    private static final String USER_AREA_SCREEN_NAME = "com/example/demo/user_area.fxml" ;
    private static final String BARBER_MANAGE_PRODUCT_SCREEN_NAME = "com/example/demo/barber_manage_products.fxml" ;
    private static final String BARBER_APPOINTMENTS_LIST_VIEW_SCREEN_NAME = "com/example/demo/barber_appointments.fxml" ;


    @FXML
    public void onButtonClicked(ActionEvent event) throws IOException {
        Node sourceNode = (Node) event.getSource() ;
        BorderPane clientBorderPane = (BorderPane) barberMenuBar.getScene().getRoot();
        if (sourceNode == logoutButton) {
            BackController.getInstance().onBackClick((Node) event.getSource());
            InternalBackController.getInternalBackControllerInstance().emptyStack();
        }
        else if (sourceNode == homeButton){
            //Gestione Home
            InternalBackController.getInternalBackControllerInstance().backToHome(homeButton);
        }
        else {
            //Gestione back
            InternalBackController.getInternalBackControllerInstance().onBackClicked(event);
        }
    }

    @FXML
    public void onMenuItemSelected(ActionEvent event) throws IOException {
        MenuItem sourceItem = (MenuItem) event.getSource() ;
        BorderPane barberBorderPane = (BorderPane) barberMenuBar.getScene().getRoot() ;
        String newCenterNodeResName ;
        InternalBackController.getInternalBackControllerInstance().onNextScreen(barberBorderPane);


        if (sourceItem == addCenterItem) {
            newCenterNodeResName = ADD_BARBER_SCREEN_NAME;
        }
        else if (sourceItem == userAreaItem) {
            newCenterNodeResName = USER_AREA_SCREEN_NAME;
        }
        else if (sourceItem == manageProductsItem) {
            newCenterNodeResName = BARBER_MANAGE_PRODUCT_SCREEN_NAME;
        }
        else if (sourceItem == barberCenterItem){
            newCenterNodeResName = BARBER_CENTER_SCREEN_NAME;
        }
        else if (sourceItem == addServiceItem){
            newCenterNodeResName = ADD_SERVICE_BARBER_SCREEN_NAME;
        }
        else if (sourceItem == listAppointmentsItem){
            newCenterNodeResName = BARBER_APPOINTMENTS_LIST_VIEW_SCREEN_NAME;
        }
        else {
            newCenterNodeResName = null ;
        }

        if (newCenterNodeResName != null) {
            Parent newCenterNode = new FXMLLoader(getClass().getClassLoader().getResource(newCenterNodeResName)).load() ;
            barberBorderPane.setCenter(newCenterNode);

            InternalBackController.getInternalBackControllerInstance().onMenuItemClicked();
        }


    }


}
