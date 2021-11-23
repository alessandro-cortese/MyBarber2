package com.example.demo.controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;

public class InternalBackController {

    private ArrayList<Node> internalNodeStack ;

    private static InternalBackController internalBackController ;

    private InternalBackController() {
        this.internalNodeStack = new ArrayList<Node>() ;
    }

    public static InternalBackController getInternalBackControllerInstance() {
        if (internalBackController == null) {
            internalBackController = new InternalBackController() ;
        }
        return internalBackController ;
    }

    public void emptyStack() {
        internalNodeStack.removeAll(internalNodeStack) ;
    }


    public void backToHome(Node sourceNode) {

        BorderPane sceneRoot = getBorderPane(sourceNode) ;
        Node homeNode = null ;
        while (!internalNodeStack.isEmpty()) {
            homeNode = internalNodeStack.remove(internalNodeStack.size() - 1) ;
        }
        if (homeNode != null) {
            sceneRoot.setCenter(homeNode);
        }
    }

    public void onBackClicked(ActionEvent event) {
        Node sourceNode = (Node) event.getSource() ;
        BorderPane sceneRoot = getBorderPane(sourceNode) ;
        if (!internalNodeStack.isEmpty()) {
            Node nextNode = internalNodeStack.remove(internalNodeStack.size() - 1);
            sceneRoot.setCenter(nextNode);
        }
    }

    public void onNextScreen(Node sourceNode) {
        this.internalNodeStack.add(getBorderPane(sourceNode).getCenter()) ;
    }

    public void onMenuItemClicked() {
        Node homeNode = null ;
        while (!internalNodeStack.isEmpty()) {
            homeNode = internalNodeStack.remove(internalNodeStack.size() - 1) ;
        }
        if (homeNode != null) {
            internalNodeStack.add(homeNode);
        }
    }

    private BorderPane getBorderPane(Node sourceNode) {
        Scene nodeScene = sourceNode.getScene() ;
        return (BorderPane) nodeScene.getRoot();
    }
}
