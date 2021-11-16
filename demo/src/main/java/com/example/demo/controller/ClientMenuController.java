package com.example.demo.controller;

import com.example.demo.ClientAppointmentsList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class ClientMenuController {

    private final String SEE_APPOINTMENTS_SCREEN_NAME = "com/example/demo/client_see_appointments.fxml" ;
    private final String BUY_PRODUCT_SCREEN_NAME = "com/example/demo/buy_product.fxml";
    private final String CLIENT_HOME_SCREEN_NAME = "com/example/demo/client_home.fxml" ;
    private final String CLIENT_CART_SCREEN_NAME = "com/example/demo/client_cart.fxml" ;

    private final String CLIENT_APPOINTMENT_ITEM = "com/example/demo/client_see_appointments_list_item.fxml" ;
    private final String BUY_PRODUCT_ITEM = "com/example/demo/buy_product_list_item.fxml";
    private final String CART_PRODUCT_ITEM = "com/example/demo/buy_product_list_item.fxml" ;

    @FXML
    private MenuBar clientMenuBar ;

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
        if ("seeAppointmentsItem".equals(sourceItem.getId())) {
            newCenterNode = (new FXMLLoader(getClass().getClassLoader().getResource(SEE_APPOINTMENTS_SCREEN_NAME))).load();
            listView = (ListView) newCenterNode.lookup("#appointmentsListView");
            onLoadListItems(listView, CLIENT_APPOINTMENT_ITEM);
        }

        else if ("buyProductItem".equals(sourceItem.getId())) {
            newCenterNode = (new FXMLLoader(getClass().getClassLoader().getResource(BUY_PRODUCT_SCREEN_NAME))).load();
            listView = (ListView) newCenterNode.lookup("#buyProductListView");
            onLoadListItems(listView, BUY_PRODUCT_ITEM);
        }

        else if ("clientCartItem".equals(sourceItem.getId())) {
            newCenterNode = (new FXMLLoader(getClass().getClassLoader().getResource(CLIENT_CART_SCREEN_NAME))).load();
            listView = (ListView) newCenterNode.lookup("#cartListView");
            onLoadListItems(listView, CART_PRODUCT_ITEM);
        }

        clientBorderPane.setCenter(newCenterNode);
    }

    private void onLoadListItems(ListView listView, String itemResource) throws IOException {
        Node[] nodesList = new Node[10] ;
        for (int i = 0 ; i < 10 ; i++) {
            nodesList[i] = (new FXMLLoader(getClass().getClassLoader().getResource(itemResource))).load() ;
        }
        ClientAppointmentsList clientAppointmentsList = new ClientAppointmentsList(nodesList);
        listView.setItems(clientAppointmentsList);
    }
}
