package com.example.demo;

import javafx.collections.ObservableListBase;
import javafx.scene.Node;

public class ObservableListNode extends ObservableListBase<Node> {

    private Node[] list ;
    public ObservableListNode(Node...arrayParam) {
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
