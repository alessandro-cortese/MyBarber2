package com.example.demo.controller;

import com.example.demo.ObservableListNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BarberCenterController implements Initializable {

    @FXML private ListView<Node> barberCenterListView;

    @FXML private Button addCenterButton;
    @FXML private Button modifyButton;
    @FXML private Button removeCenterButton;

    private static final String BARBER_CENTER_LIST_ITEM = "com/example/demo/MyBarber_1/Client/center_list_item.fxml";

    private static final String ADD_BARBER_CENTER_SCREEN_NAME = "com/example/demo/MyBarber_1/Barber/barber_add_center.fxml";
    private static final String BARBER_MODIFY_SCREEN_NAME = "com/example/demo/MyBarber_1/Barber/barber_modify_center.fxml";


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Node[] nodes = new Node[5];
        for (int i = 0 ; i < nodes.length ; i++) {
            try {
                nodes[i] = (new FXMLLoader(getClass().getClassLoader().getResource(BARBER_CENTER_LIST_ITEM))).load() ;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ObservableListNode barberCenterObservableListNode = new ObservableListNode(nodes);
        barberCenterListView.setItems(barberCenterObservableListNode);

    }

    @FXML
    public void onButtonClicked(ActionEvent event) throws IOException {
        Button sourceButton = (Button) event.getSource();


        String nextScreenName ;

        if (sourceButton == addCenterButton){
            nextScreenName = ADD_BARBER_CENTER_SCREEN_NAME ;
        }
        else if(sourceButton == modifyButton) {
            nextScreenName = BARBER_MODIFY_SCREEN_NAME ;
        }
        else {
            nextScreenName = null ;
        }

        if (nextScreenName != null) {
            InternalBackController.getInternalBackControllerInstance().onNextScreen(sourceButton);

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(nextScreenName));
            BorderPane myBorderPane = (BorderPane) (sourceButton.getScene()).getRoot();
            myBorderPane.setCenter(fxmlLoader.load());
        }

    }


}
