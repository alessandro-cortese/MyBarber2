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

public class ClientMenuController {

    private final String SEE_APPOINTMENTS_SCREEN_NAME = "com/example/demo/MyBarber_1/Client/client_see_appointments.fxml";
    private final String BUY_PRODUCT_SCREEN_NAME = "com/example/demo/MyBarber_1/Client/client_buy_product.fxml";
    private final String CLIENT_HOME_SCREEN_NAME = "com/example/demo/MyBarber_1/Client/client_home.fxml";
    private final String CLIENT_CART_SCREEN_NAME = "com/example/demo/MyBarber_1/Client/client_cart.fxml";
    private final String USER_AREA_SCREEN_NAME = "com/example/demo/MyBarber_1/General/user_area.fxml";


    private final String TAKE_APPOINTMENT_SCREEN_NAME = "com/example/demo/MyBarber_1/Client/client_take_appointment.fxml";
    private final String TAKE_SALOON_SCREEN_NAME = "com/example/demo/MyBarber_1/Client/client_take_saloon.fxml";

    @FXML
    private MenuBar clientMenuBar ;

    @FXML private MenuItem clientCartItem ;
    @FXML private MenuItem userAreaItem ;
    @FXML private MenuItem seeAppointmentsItem ;
    @FXML private MenuItem buyProductItem ;
    @FXML private MenuItem takeAppointmentItem ;

    @FXML private Button logoutButton ;
    @FXML private Button homeButton ;


    @FXML
    public void onButtonClicked(ActionEvent event) throws IOException {
        Node sourceNode = (Node)event.getSource() ;
        BorderPane clientBorderPane = (BorderPane) clientMenuBar.getScene().getRoot();
        if (sourceNode == logoutButton) {
            InternalBackController.getInternalBackControllerInstance().emptyStack();
            BackController.getInstance().onBackClick(sourceNode);
        }
        else if (sourceNode == homeButton){
            //Gestione Home
            InternalBackController.getInternalBackControllerInstance().backToHome(homeButton);
        }
        else {
            //Gestione Back
            InternalBackController.getInternalBackControllerInstance().onBackClicked(event);
        }
    }

    @FXML
    public void onMenuItemSelected(ActionEvent event) throws IOException {
        MenuItem sourceItem = (MenuItem) event.getSource() ;
        BorderPane clientBorderPane = (BorderPane) clientMenuBar.getScene().getRoot();
        InternalBackController.getInternalBackControllerInstance().onNextScreen(clientBorderPane);
        String newCenterNodeResName ;
        //Notare il confronto tra oggetti piuttosto che tra stringhe
        if (sourceItem == seeAppointmentsItem) {
            newCenterNodeResName = SEE_APPOINTMENTS_SCREEN_NAME ;
        }
        else if (sourceItem == buyProductItem) {
            newCenterNodeResName = BUY_PRODUCT_SCREEN_NAME;
        }
        else if (sourceItem == takeAppointmentItem) {
            newCenterNodeResName = TAKE_APPOINTMENT_SCREEN_NAME ;
        }
        else if (sourceItem == clientCartItem) {
            newCenterNodeResName = CLIENT_CART_SCREEN_NAME;
        }
        else if (sourceItem == userAreaItem) {
            newCenterNodeResName = USER_AREA_SCREEN_NAME;
        }
        else {
            newCenterNodeResName = null ;
        }

        if (newCenterNodeResName != null) {
            Parent newCenterNode = new FXMLLoader(getClass().getClassLoader().getResource(newCenterNodeResName)).load() ;
            clientBorderPane.setCenter(newCenterNode);

            //Svuoto stack lasciando solo la home sulla cima
            InternalBackController.getInternalBackControllerInstance().onMenuItemClicked();
        }
    }


}
