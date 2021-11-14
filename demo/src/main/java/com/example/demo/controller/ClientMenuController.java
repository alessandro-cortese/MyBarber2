package com.example.demo.controller;

import com.example.demo.ClientAppointmentsList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ClientMenuController {

    private final String SEE_APPOINTMENTS_SCREEN_NAME = "com/example/demo/client_see_appointments.fxml" ;
    private final String CLIENT_HOME_SCREEN_NAME = "com/example/demo/client_home.fxml" ;
    private final String CLIENT_APPOINTMENT_ITEM = "com/example/demo/client_see_appointments_list_item.fxml" ;

    @FXML
    private VBox clientScreenChanger;

    @FXML
    public void onButtonClicked(ActionEvent event) throws IOException {
        Node sourceNode = (Node)event.getSource() ;
        if (sourceNode.getId().equals("logoutButton")) {
            BackController.getInstance().onBackClick(sourceNode);
        }
        else {
            clientScreenChanger.getChildren().removeAll(clientScreenChanger.getChildren());
            FXMLLoader seeAppointmentsLoader = new FXMLLoader(getClass().getClassLoader().getResource(CLIENT_HOME_SCREEN_NAME));
            clientScreenChanger.getChildren().add(seeAppointmentsLoader.load());
        }
    }

    @FXML
    public void onMenuItemSelected(ActionEvent event) throws IOException {
        MenuItem sourceItem = (MenuItem) event.getSource() ;
        if ("seeAppointmentsItem".equals(sourceItem.getId())) {
            clientScreenChanger.getChildren().removeAll(clientScreenChanger.getChildren());
            Parent seeAppointmentsNode = (new FXMLLoader(getClass().getClassLoader().getResource(SEE_APPOINTMENTS_SCREEN_NAME))).load();
            ListView appointmentsListView = (ListView) seeAppointmentsNode.lookup("#appointmentsListView");

            Node[] nodesList = new Node[10] ;
            for (int i = 0 ; i < 10 ; i++) {
                nodesList[i] = (new FXMLLoader(getClass().getClassLoader().getResource(CLIENT_APPOINTMENT_ITEM))).load() ;
            }
            ClientAppointmentsList clientAppointmentsList = new ClientAppointmentsList(nodesList);
            appointmentsListView.setItems(clientAppointmentsList);

            clientScreenChanger.getChildren().add(seeAppointmentsNode);
        }
    }
}
