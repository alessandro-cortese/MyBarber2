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

    public InternalBackController getInternalBackControllerInstance() {
        if (internalBackController == null) {
            internalBackController = new InternalBackController() ;
        }
        return internalBackController ;
    }

    public void emptyStack() {
        internalNodeStack.removeAll(internalNodeStack) ;
    }


    public void backToHome(ActionEvent event) {
        BorderPane sceneRoot = getBorderPane(event) ;
        Node homeNode = null ;
        while (!internalNodeStack.isEmpty()) {
            homeNode = internalNodeStack.remove(internalNodeStack.size() - 1) ;
        }
        if (homeNode != null) {
            sceneRoot.setCenter(homeNode);
        }
    }

    public void onBackClicked(ActionEvent event) {
        BorderPane sceneRoot = getBorderPane(event) ;
        Node nextNode = internalNodeStack.remove(internalNodeStack.size() - 1) ;
        sceneRoot.setCenter(nextNode);
    }

    public void onNextScreen(ActionEvent event) {
        BorderPane borderPane = getBorderPane(event) ;
        this.internalNodeStack.add(borderPane.getCenter()) ;
    }

    private BorderPane getBorderPane(ActionEvent event) {
        Node sourceNode = (Node) event.getSource() ;
        Scene nodeScene = sourceNode.getScene() ;
        return (BorderPane) nodeScene.getRoot();
    }
}
