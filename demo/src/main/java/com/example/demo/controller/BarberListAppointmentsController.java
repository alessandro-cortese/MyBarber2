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

public class BarberListAppointmentsController implements Initializable {

    @FXML private ListView<Node> appointmentsListView;

    private static final String BARBER_APPOINTMENTS_LIST_ITEM = "com/example/demo/barber_appointments_item.fxml" ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Node[] nodes = new Node[5];
        for (int i = 0 ; i < nodes.length ; i++) {
            try {
                nodes[i] = (new FXMLLoader(getClass().getClassLoader().getResource(BARBER_APPOINTMENTS_LIST_ITEM))).load() ;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ObservableListNode barberCenterObservableListNode = new ObservableListNode(nodes);
        appointmentsListView.setItems(barberCenterObservableListNode);

    }

}
