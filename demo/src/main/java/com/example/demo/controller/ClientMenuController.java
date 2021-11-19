package com.example.demo.controller;

import com.example.demo.ObservableListNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class ClientMenuController {

    private final String SEE_APPOINTMENTS_SCREEN_NAME = "com/example/demo/client_see_appointments.fxml" ;
    private final String BUY_PRODUCT_SCREEN_NAME = "com/example/demo/buy_product.fxml";
    private final String CLIENT_HOME_SCREEN_NAME = "com/example/demo/client_home.fxml" ;
    private final String CLIENT_CART_SCREEN_NAME = "com/example/demo/client_cart.fxml";
    private final String USER_AREA_SCREEN_NAME = "com/example/demo/user_area.fxml" ;


    private final String TAKE_APPOINTMENT_SCREEN_NAME = "com/example/demo/take_appointment_client.fxml";
    private final String TAKE_SALOON_SCREEN_NAME ="com/example/demo/take_saloon.fxml";

    @FXML
    private MenuBar clientMenuBar ;

    @FXML private MenuItem clientCartItem ;
    @FXML private MenuItem userAreaItem ;
    @FXML private MenuItem seeAppointmentsItem ;
    @FXML private MenuItem buyProductItem ;
    @FXML private MenuItem takeAppointmentItem ;

    @FXML private Button logoutButton ;


    @FXML
    public void onButtonClicked(ActionEvent event) throws IOException {
        Node sourceNode = (Node)event.getSource() ;
        BorderPane clientBorderPane = (BorderPane) clientMenuBar.getScene().getRoot();
        if (sourceNode == logoutButton) {
            BackController.getInstance().onBackClick(sourceNode);
        }
        else {
            //Gestione Home
            FXMLLoader clientHomeScreen = new FXMLLoader(getClass().getClassLoader().getResource(CLIENT_HOME_SCREEN_NAME));
            clientBorderPane.setCenter(clientHomeScreen.load());
        }
    }

    @FXML
    public void onMenuItemSelected(ActionEvent event) throws IOException {
        MenuItem sourceItem = (MenuItem) event.getSource() ;
        BorderPane clientBorderPane = (BorderPane) clientMenuBar.getScene().getRoot();

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
        }
    }

    private void onLoadListItems(ListView listView, String itemResource) throws IOException {
        Node[] nodesList = new Node[10] ;
        for (int i = 0 ; i < 10 ; i++) {
            nodesList[i] = (new FXMLLoader(getClass().getClassLoader().getResource(itemResource))).load() ;
        }
        ObservableListNode clientAppointmentsList = new ObservableListNode(nodesList);
        listView.setItems(clientAppointmentsList);
    }

}
