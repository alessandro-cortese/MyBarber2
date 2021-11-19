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

public class ClientSeeAppointmentsController implements Initializable {

    @FXML private ListView<Node> appointmentsListView ;

    private String LIST_ITEM_RES = "com/example/demo/client_see_appointments_list_item.fxml" ;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*
            Questo metodo viene eseguito in automatico DOPO chi cambi @FXML sono stati iniettati.
            Di conseguenza in questo metodo è possibile inserire gli elementi della ListView essendo sicuri che
            essa sia non-null.

            In questo modo non è possibile caricare la lista dal controlore esterno, ma direttamente dal controllore
            della schrmata in cui è presente la ListView, anzichè dal precedente utilizzando il Lookup.


            NOTA. Ora dice che il codice è duplicato semplicemente perché la logica è Dummy: quando aggiungeremo la logica
            il warning di ripetizione del codice dovrebbe sparire
         */
        Node[] nodesList = new Node[10] ;
        for (int i = 0 ; i < 10 ; i++) {
            try {
                nodesList[i] = (new FXMLLoader(getClass().getClassLoader().getResource(LIST_ITEM_RES))).load() ;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ObservableListNode clientAppointmentsList = new ObservableListNode(nodesList);
        appointmentsListView.setItems(clientAppointmentsList);
    }
}
