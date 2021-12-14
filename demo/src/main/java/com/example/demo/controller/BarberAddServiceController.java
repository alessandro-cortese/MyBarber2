package com.example.demo.controller;

import pricepicker.PricePicker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class BarberAddServiceController {

    @FXML private Button saveButton;
    @FXML private TextField priceTextField;


    @FXML
    public void onButtonClicked(ActionEvent event) {
        Node sourceNode = (Node) event.getSource();
        if(sourceNode == saveButton){
            InternalBackController.getInternalBackControllerInstance().backToHome(sourceNode);
        }

    }


    @FXML
    public void onPricePicked(MouseEvent event) throws IOException {
        TextField sourceTextField = (TextField) event.getSource();
        if(sourceTextField == priceTextField){
            PricePicker pricePicker = new PricePicker(0, 0.0);
            priceTextField.setText(pricePicker.getPrice());
        }
    }

}
