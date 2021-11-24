package com.example.demo.controller;

import com.example.demo.ObservableListNode;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BuyProductController implements Initializable {

    @FXML private ListView buyProductListView ;

    private static final String LIST_ITEM_RES = "com/example/demo/MyBarber_1/ListItem/client_buy_product_list_item.fxml";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Node[] nodesList = new Node[10] ;
        for (int i = 0 ; i < 10 ; i++) {
            try {
                nodesList[i] = (new FXMLLoader(getClass().getClassLoader().getResource(LIST_ITEM_RES))).load() ;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ObservableListNode clientAppointmentsList = new ObservableListNode(nodesList);
        buyProductListView.setItems(clientAppointmentsList);
    }
}
