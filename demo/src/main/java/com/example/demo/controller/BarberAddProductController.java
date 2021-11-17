package com.example.demo.controller;

import PricePicker.PricePicker;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class BarberAddProductController {

    @FXML
    private TextField priceTextField;

    @FXML
    public void onPriceClicked(MouseEvent event) throws IOException {
        PricePicker pricePicker = new PricePicker(0, 0.0);
        /*
        if (priceTextField.getText().equals("")) {
            pricePicker = new PricePicker(0,0.0);
        }
        else {
            String startPriceString = priceTextField.getText() ;
            String[] priceParts = startPriceString.split(",") ;
            pricePicker = new PricePicker(Integer.getInteger(priceParts[0]), Double.parseDouble("0." + priceParts[1])) ;
        }*/

        priceTextField.setText(pricePicker.getPrice());
    }

}
