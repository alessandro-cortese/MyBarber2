package com.example.demo;

import javafx.collections.ObservableListBase;
import javafx.scene.Node;
import javafx.scene.control.ListView;

public class ClientAppointmentsList extends ObservableListBase<Node> {

    private Node[] list ;
    public ClientAppointmentsList(Node...arrayParam) {
        this.list = arrayParam ;
    }

    @Override
    public Node get(int index) {
        return list[index];
    }

    @Override
    public int size() {
        return list.length;
    }
}
