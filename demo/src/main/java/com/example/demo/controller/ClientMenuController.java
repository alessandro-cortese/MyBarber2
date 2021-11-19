package com.example.demo.controller;

import com.example.demo.ObservableListNode;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientMenuController {

    private final String SEE_APPOINTMENTS_SCREEN_NAME = "com/example/demo/client_see_appointments.fxml" ;
    private final String BUY_PRODUCT_SCREEN_NAME = "com/example/demo/buy_product.fxml";
    private final String CLIENT_HOME_SCREEN_NAME = "com/example/demo/client_home.fxml" ;
    private final String CLIENT_CART_SCREEN_NAME = "com/example/demo/client_cart.fxml";
    private final String USER_AREA_SCREEN_NAME = "com/example/demo/user_area.fxml" ;

    private final String CLIENT_APPOINTMENT_ITEM = "com/example/demo/client_see_appointments_list_item.fxml" ;
    private final String SALOON_ITEM = "com/example/demo/take_saloon_item.fxml";
    private final String BUY_PRODUCT_ITEM = "com/example/demo/buy_product_list_item.fxml";
    private final String CART_PRODUCT_ITEM = "com/example/demo/buy_product_list_item.fxml" ;

    private final String TAKE_APPOINTMENT_SCREEN_NAME = "com/example/demo/take_appointment_client.fxml";
    private final String TAKE_SALOON_SCREEN_NAME ="com/example/demo/take_saloon.fxml";

    @FXML
    private MenuBar clientMenuBar ;

    @FXML private MenuItem clientCartItem ;
    @FXML private MenuItem userAreaItem ;

    @FXML
    public void onButtonClicked(ActionEvent event) throws IOException {
        Node sourceNode = (Node)event.getSource() ;
        BorderPane clientBorderPane = (BorderPane) clientMenuBar.getScene().getRoot();
        if (sourceNode.getId().equals("logoutButton")) {
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
        ListView listView ;
        Parent newCenterNode = null ;
        switch (sourceItem.getId()) {
            case "seeAppointmentsItem":
                newCenterNode = (new FXMLLoader(getClass().getClassLoader().getResource(SEE_APPOINTMENTS_SCREEN_NAME))).load();
                listView = (ListView) newCenterNode.lookup("#appointmentsListView");
                onLoadListItems(listView, CLIENT_APPOINTMENT_ITEM);
                break;
            case "buyProductItem":
                newCenterNode = (new FXMLLoader(getClass().getClassLoader().getResource(BUY_PRODUCT_SCREEN_NAME))).load();
                listView = (ListView) newCenterNode.lookup("#buyProductListView");
                onLoadListItems(listView, BUY_PRODUCT_ITEM);
                break;
            case "takeAppointmentItem":
                newCenterNode = (new FXMLLoader(getClass().getClassLoader().getResource(TAKE_APPOINTMENT_SCREEN_NAME))).load();
                break;
        }

        else if (clientCartItem == sourceItem) {
            newCenterNode = (new FXMLLoader(getClass().getClassLoader().getResource(CLIENT_CART_SCREEN_NAME))).load();
            listView = (ListView) newCenterNode.lookup("#cartListView");
            onLoadListItems(listView, CART_PRODUCT_ITEM);
        }

        else if (userAreaItem == sourceItem) {
            newCenterNode = (new FXMLLoader(getClass().getClassLoader().getResource(USER_AREA_SCREEN_NAME))).load();
        }

        clientBorderPane.setCenter(newCenterNode);
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
