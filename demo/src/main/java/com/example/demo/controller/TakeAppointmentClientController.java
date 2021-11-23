package com.example.demo.controller;

import com.example.demo.ObservableListNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class TakeAppointmentClientController {

    final static String APPOINTMENT_SALOON_ITEM = "com/example/demo/take_saloon_item.fxml";

    ListView listView;

    @FXML
    private Button saloonNextButton;

    @FXML
    private Tab saloonTab;

    @FXML
    private Button treatmentNextButton;

    @FXML
    private Tab treatmentTab;

    @FXML
    public void onButtonSaloonClicked(ActionEvent actionEvent) throws IOException {
        Button sourceButton = (Button) actionEvent.getSource();
        Parent newCenterNode = (new FXMLLoader(getClass().getClassLoader().getResource("com/example/demo/take_saloon.fxml"))).load();
        // FXMLLoader node = new FXMLLoader(getClass().getClassLoader().getResource("com/example/demo/take_saloon.fxml"));
        listView = (ListView) newCenterNode.lookup("#saloonListView");
        onLoadListItems(listView, APPOINTMENT_SALOON_ITEM);
        Scene myScene = (Scene) sourceButton.getScene();
        BorderPane borderPane = (BorderPane) myScene.getRoot();
        borderPane.setCenter(newCenterNode);

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
